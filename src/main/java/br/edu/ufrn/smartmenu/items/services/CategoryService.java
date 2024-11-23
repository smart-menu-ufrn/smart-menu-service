package br.edu.ufrn.smartmenu.items.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.items.dtos.requests.CategoryCreateRequestDTO;
import br.edu.ufrn.smartmenu.items.dtos.requests.CategoryUpdateRequestDTO;
import br.edu.ufrn.smartmenu.items.dtos.responses.CategoryResponseDTO;
import br.edu.ufrn.smartmenu.items.models.Category;
import br.edu.ufrn.smartmenu.items.repositories.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        List<CategoryResponseDTO> categoryResponseDTOList = categoryList
            .stream()
            .map(CategoryResponseDTO::new)
            .collect(Collectors.toList());

        return categoryResponseDTOList;
    }

    public CategoryResponseDTO getCategoryById(
        Long id
    ) throws NoSuchElementException {
        Category category = categoryRepository.findById(id).get();

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(category);
        
        return categoryResponseDTO;
    }

    public CategoryResponseDTO createCategory(
        CategoryCreateRequestDTO categoryCreateRequestDTO
    ) {
        Category category = categoryCreateRequestDTO.toEntity();

        category = categoryRepository.save(category);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(category);
        
        return categoryResponseDTO;
    }

    public CategoryResponseDTO updateCategory(
        Long id,
        CategoryUpdateRequestDTO categoryUpdateRequestDTO
    ) throws NoSuchElementException {
        Category category = categoryRepository.findById(id).get();

        Category updatedCategory = categoryUpdateRequestDTO.updateEntity(category);

        categoryRepository.save(updatedCategory);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(updatedCategory);

        return categoryResponseDTO;
    }

    public void deleteCategory(
        Long id
    ) throws NoSuchElementException {
        Category category = categoryRepository.findById(id).get();
        
        categoryRepository.delete(category);
    }

}
