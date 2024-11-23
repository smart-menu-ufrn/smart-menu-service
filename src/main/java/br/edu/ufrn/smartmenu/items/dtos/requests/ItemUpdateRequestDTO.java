package br.edu.ufrn.smartmenu.items.dtos.requests;

import java.util.Optional;

import br.edu.ufrn.smartmenu.items.models.Category;
import br.edu.ufrn.smartmenu.items.models.Item;

public class ItemUpdateRequestDTO {
    
    private String name;
    private String description;
    private Double price;
    private Long categoryId;

    public ItemUpdateRequestDTO() {}

    public ItemUpdateRequestDTO(
        String name,
        String description,
        Double price,
        Long categoryId
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Item updateEntity(Item item, Optional<Category> category) {
               
        if (
            this.name != null
            && !this.name.equals(item.getName())
        ) {
            item.setName(this.name);
        }

        if (
            this.description != null
            && !this.description.equals(item.getDescription())
        ) {
            item.setDescription(this.description);
        }

        if (
            this.price != null
            && !this.price.equals(item.getPrice())
        ) {
            item.setPrice(this.price);
        }

        if (
            this.categoryId != null
            && category.isPresent()
            && !this.categoryId.equals(category.get().getId())
        ) {
            item.setCategory(category.get());
        }

        return item;
    }

}
