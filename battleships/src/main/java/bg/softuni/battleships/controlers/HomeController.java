package bg.softuni.battleships.controlers;

import bg.softuni.battleships.domain.DTOs.ShipDTO;
import bg.softuni.battleships.domain.DTOs.StartBattleDTO;
import bg.softuni.battleships.services.AuthService;
import bg.softuni.battleships.services.ShipService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final AuthService authService;

    @Autowired
    public HomeController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO innitStartBattleDTO(){
        return new StartBattleDTO();
    }

    @GetMapping("/")
    public String loggedOut(){
        if (this.authService.isLoggedIn()){
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedIn(Model model){

        if (!this.authService.isLoggedIn()){
            return "redirect:/";
        }

        Long loggedId = this.authService.getLoggedUserId();

        List<ShipDTO> ownedShips = this.shipService.getOwnedShips(loggedId);
        List<ShipDTO> enemyShips = this.shipService.getNotOwnedBy(loggedId);
        List<ShipDTO> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("ownedShips", ownedShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }
}
