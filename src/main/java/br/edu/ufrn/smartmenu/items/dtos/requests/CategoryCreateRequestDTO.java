package br.edu.ufrn.smartmenu.items.dtos.requests;

import br.edu.ufrn.smartmenu.items.models.Category;

public class CategoryCreateRequestDTO {
    
    private String name;

    public CategoryCreateRequestDTO() {}

    public CategoryCreateRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Category toEntity() {
        return new Category(this.getName());
    }

}
