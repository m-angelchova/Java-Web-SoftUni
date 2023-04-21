package bg.softuni.battleships.controlers;

import bg.softuni.battleships.domain.DTOs.CreateShipDto;
import bg.softuni.battleships.services.AuthService;
import bg.softuni.battleships.services.ShipService;
import bg.softuni.battleships.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    private final ShipService shipService;
    private final AuthService authService;


    @Autowired
    public ShipController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }

    @ModelAttribute("createShipDto")
    public CreateShipDto innitCreateShipDto(){
        return new CreateShipDto();
    }


    @GetMapping("/ships/add")
    public String addShip(){

        if (!this.authService.isLoggedIn()){
            return "redirect:/";
        }

        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String addShip(@Valid CreateShipDto createShipDto,
                          RedirectAttributes redirectAttributes,
                          BindingResult bindingResult){

        if (!this.authService.isLoggedIn()){
            return "redirect:/";
        }


        if (bindingResult.hasErrors() || !this.shipService.create(createShipDto)){
            redirectAttributes.addFlashAttribute("createShipDto", createShipDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createShipDto", bindingResult);
            return "redirect:/ships/add";
        }
        return "redirect:/home";
    }
}
