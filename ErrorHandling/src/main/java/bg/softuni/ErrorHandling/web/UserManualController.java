package bg.softuni.ErrorHandling.web;

import bg.softuni.ErrorHandling.dto.ObjectNotFoundEx;
import bg.softuni.ErrorHandling.dto.ProductNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserManualController {


    @GetMapping("/manuals/{id}") //404 error
    public String getProductById(@PathVariable("id") Long id) {
        throw new ObjectNotFoundEx(id, "User manual");
    }
}
