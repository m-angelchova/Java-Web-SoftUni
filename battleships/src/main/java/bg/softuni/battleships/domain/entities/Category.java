package bg.softuni.battleships.domain.entities;

import bg.softuni.battleships.domain.constants.enums.ShipTypes;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.ORDINAL)
    @Column(unique = true, nullable = false)
    private ShipTypes name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {
    }

    public Category(ShipTypes name) {
        this.name = name;
    }

    public ShipTypes getName() {
        return name;
    }

    public void setName(ShipTypes name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
