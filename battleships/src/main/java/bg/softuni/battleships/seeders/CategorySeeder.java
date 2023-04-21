package bg.softuni.battleships.seeders;

import bg.softuni.battleships.domain.constants.enums.ShipTypes;
import bg.softuni.battleships.domain.entities.Category;
import bg.softuni.battleships.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0){
            List<Category> categories = Arrays.stream(ShipTypes.values())
                    .map(Category::new)
                    .collect(Collectors.toList());

            this.categoryRepository.saveAll(categories);


//           ------> n+1 queries problem -> vmesto edna zaqvka puska nqkolko otdelni (1000 => performance problem) v
//
//            Arrays.stream(ShipTypes.values())
//                    .map(Category::new)
//                    .forEach(c -> this.categoryRepository.save(c));

        }
    }
}
