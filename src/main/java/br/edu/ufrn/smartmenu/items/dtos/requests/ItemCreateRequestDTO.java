package br.edu.ufrn.smartmenu.items.dtos.requests;

import br.edu.ufrn.smartmenu.items.models.Item;

public class ItemCreateRequestDTO {
    
    private String name;
    private String description;
    private Double price;
    private Long categoryId;

    public ItemCreateRequestDTO() {}

    public ItemCreateRequestDTO(
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

    public Item toEntity() {
        return new Item(this.getName(), this.getDescription(), this.getPrice());
    }

}
