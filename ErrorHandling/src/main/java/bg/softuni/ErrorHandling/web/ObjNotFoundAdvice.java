package bg.softuni.ErrorHandling.web;

import bg.softuni.ErrorHandling.dto.ObjectNotFoundEx;
import bg.softuni.ErrorHandling.dto.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjNotFoundAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundEx.class)
    public ModelAndView onProductNotFound(ObjectNotFoundEx onfe){
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("id", onfe.getId());
        modelAndView.addObject("type", onfe.getType());
        return modelAndView;
    }
}
