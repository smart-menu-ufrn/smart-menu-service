package br.edu.ufrn.smartmenu.items.dtos.requests;

import br.edu.ufrn.smartmenu.items.models.Category;

public class CategoryUpdateRequestDTO {
    
    private String name;

    public CategoryUpdateRequestDTO() {}

    public CategoryUpdateRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Category updateEntity(Category category) {

        if (this.name != null && !this.name.equals(category.getName())) {
            category.setName(this.name);
        }

        return category;
    }

}
