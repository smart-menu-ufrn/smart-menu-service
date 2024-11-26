package br.edu.ufrn.smartmenu.items.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ufrn.smartmenu.items.models.Item;
import br.edu.ufrn.smartmenu.items.models.Category;
import br.edu.ufrn.smartmenu.items.repositories.ItemRepository;
import br.edu.ufrn.smartmenu.items.repositories.CategoryRepository;

@Component
public class ItemsLoader implements CommandLineRunner{

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemsLoader(ItemRepository repository, CategoryRepository category) {
        this.itemRepository = repository;
        this.categoryRepository = category;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {

            Category c1 = new Category("Drinks");
            Category c2 = new Category("Entradas");

            categoryRepository.saveAll(List.of(c1, c2));



            Item i1 = new Item("Coca-Cola", "Refrigerante em lata", 3.0, c1);
            Item i2 = new Item("Wiskey", "Wiskey fortissimo", 10.0, c1);
            Item i3 = new Item("Vinho", "Vinho fino", 19.0, c1);

            itemRepository.saveAll(List.of(i1, i2, i3));
        }
    }

}
