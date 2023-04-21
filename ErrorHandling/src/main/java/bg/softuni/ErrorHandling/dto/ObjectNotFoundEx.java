package bg.softuni.ErrorHandling.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object was not found.")
public class ObjectNotFoundEx  extends RuntimeException{
    private Long id;
    private String type;

    public ObjectNotFoundEx (Long id, String type){
        super("Object with id " + id + " and type " + type + " not found!");

        this.id = id;
        this.type = type;
    }


    //samo getters needed

    public Long getId() {
        return id;
    }

    public ObjectNotFoundEx setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public ObjectNotFoundEx setType(String type) {
        this.type = type;
        return this;
    }
}
