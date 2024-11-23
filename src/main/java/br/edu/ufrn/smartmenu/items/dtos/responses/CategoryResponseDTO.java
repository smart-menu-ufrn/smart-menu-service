package br.edu.ufrn.smartmenu.items.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.items.models.Category;

@JsonInclude(Include.NON_NULL)
public class CategoryResponseDTO {
    
    private Long id;
    private String name;

    public CategoryResponseDTO() {}

    public CategoryResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryResponseDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
