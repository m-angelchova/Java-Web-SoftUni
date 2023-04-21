package bg.softuni.battleships.services;

import bg.softuni.battleships.domain.DTOs.CreateShipDto;
import bg.softuni.battleships.domain.DTOs.ShipDTO;
import bg.softuni.battleships.domain.entities.Category;
import bg.softuni.battleships.domain.entities.Ship;
import bg.softuni.battleships.repositories.CategoryRepository;
import bg.softuni.battleships.repositories.ShipRepository;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public ShipService(ShipRepository shipRepository,
                       CategoryRepository categoryRepository,
                       LoggedUser loggedUser, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(CreateShipDto createShipDto) {
        Optional<Ship> byName = this.shipRepository.findByName(createShipDto.getName());

        if (byName.isPresent()){
            return false;
        }

        Ship ship = new Ship();

//        ShipTypes type = switch (createShipDto.getCategory()){
//            case 0 -> ShipTypes.BATTLE;
//            case 1 -> ShipTypes.CARGO;
//            case 2 -> ShipTypes.PATROL;
//            default -> ShipTypes.BATTLE;
//        };

        Category category = this.categoryRepository.getReferenceById((long) createShipDto.getCategory());

        ship.setName(createShipDto.getName());
        ship.setPower(createShipDto.getPower());
        ship.setHealth(createShipDto.getHealth());
        ship.setCreated(createShipDto.getCreated());
        ship.setCategory(category);
        ship.setUser(this.userRepository.findById(this.loggedUser.getId()).get());


        this.shipRepository.save(ship);
        return true;
    }

    public List<ShipDTO> getOwnedShips(Long id) {
         return this.shipRepository.findAllByUserId(id)
                .stream().map(ShipDTO::new)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getNotOwnedBy(Long id) {
        return this.shipRepository.findAllByUserIdNot(id)
                .stream().map(ShipDTO::new)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getAllSorted() {
        return this.shipRepository.findAllByOrderByHealthAscNameDescPower()
                .stream().map(ShipDTO::new)
                .collect(Collectors.toList());
    }
}
