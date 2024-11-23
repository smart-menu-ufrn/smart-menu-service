package br.edu.ufrn.smartmenu.items.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.items.models.Item;

@JsonInclude(Include.NON_NULL)
public class ItemResponseDTO {
    
    private Long id;
    private String name;
    private String description;
    private Double price;
    private CategoryResponseDTO category;

    public ItemResponseDTO() {}

    public ItemResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        CategoryResponseDTO category
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public ItemResponseDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.category = new CategoryResponseDTO(item.getCategory());
    }

    public Long getId() {
        return id;
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

    public CategoryResponseDTO getCategory() {
        return category;
    }

}
