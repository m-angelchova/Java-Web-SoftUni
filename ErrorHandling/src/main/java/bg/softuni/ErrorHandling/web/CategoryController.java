package bg.softuni.ErrorHandling.web;

import bg.softuni.ErrorHandling.dto.ObjectNotFoundEx;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {

    @GetMapping("/categories/{id}") //404 error
    public String getProductById(@PathVariable("id") Long id) {
        throw new ObjectNotFoundEx(id, "Category");
    }
}
