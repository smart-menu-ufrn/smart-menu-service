package br.edu.ufrn.smartmenu.items.controllers;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.items.dtos.requests.CategoryCreateRequestDTO;
import br.edu.ufrn.smartmenu.items.dtos.requests.CategoryUpdateRequestDTO;
import br.edu.ufrn.smartmenu.items.dtos.responses.CategoryResponseDTO;
import br.edu.ufrn.smartmenu.items.services.CategoryService;

@RestController
@RequestMapping("/items/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> responseDTOList = categoryService.getAllCategories();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(
        @RequestBody CategoryCreateRequestDTO requestDTO
    ) {
        CategoryResponseDTO responseDTO = categoryService.createCategory(requestDTO);

        URI location = URI.create("/items/categories/" + responseDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(
        @PathVariable Long id
    ) {
        try {
            CategoryResponseDTO responseDTO = categoryService.getCategoryById(id);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
        @PathVariable Long id,
        @RequestBody CategoryUpdateRequestDTO requestDTO
    ) {
        try {
            CategoryResponseDTO responseDTO = categoryService.updateCategory(
                id,
                requestDTO
            );

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
