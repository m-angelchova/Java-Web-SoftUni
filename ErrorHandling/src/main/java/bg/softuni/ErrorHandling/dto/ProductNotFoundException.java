package bg.softuni.ErrorHandling.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product was not found.")
public class ProductNotFoundException extends RuntimeException{
    private Long id;

    public ProductNotFoundException (Long id){
        super("Prouduct with id " + id + " not found!");

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ProductNotFoundException setId(Long id) {
        this.id = id;
        return this;
    }
}
