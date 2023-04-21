package bg.softuni.ErrorHandling.web;

import bg.softuni.ErrorHandling.dto.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController {


    @GetMapping("/products/{id}") //404 error
    public String getProductById(@PathVariable("id") Long productId){
        throw new ProductNotFoundException(productId);

        //usually you make the thr in the Service class, not here.
        // here you make everything normally - you try to fetch a productDTO in the model
        // exception is coded in the service, so no product found -> exception
    }

    @GetMapping("/products") //example of 500
    public String getProducts(){
        throw new NullPointerException();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView onProductNotFound(ProductNotFoundException pnfe){
        ModelAndView modelAndView = new ModelAndView("product-not-found");
        modelAndView.addObject("productId", pnfe.getId());
        return modelAndView;
    }

}
