package br.edu.ufrn.smartmenu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufrn.smartmenu.dto.MenuItemDTO;
import br.edu.ufrn.smartmenu.model.Category;
import br.edu.ufrn.smartmenu.model.MenuItem;
import br.edu.ufrn.smartmenu.service.CategoryService;
import br.edu.ufrn.smartmenu.service.MenuItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(menuItemDTO.getCategoryId());
        Category category = categoryOptional.orElseThrow(() -> new RuntimeException("Category not found"));
        
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDTO.getName());
        menuItem.setDescription(menuItemDTO.getDescription());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setCategory(category);

        return menuItemService.createMenuItem(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(
        @PathVariable Long id, @RequestBody MenuItemDTO menuItemDTO) {

        Optional<Category> categoryOptional = categoryService.getCategoryById(menuItemDTO.getCategoryId());
        Category category = categoryOptional.orElseThrow(() -> new RuntimeException("Category not found"));

        MenuItem menuItemDetails = new MenuItem();
        menuItemDetails.setName(menuItemDTO.getName());
        menuItemDetails.setDescription(menuItemDTO.getDescription());
        menuItemDetails.setPrice(menuItemDTO.getPrice());
        menuItemDetails.setCategory(category);

        try {
            MenuItem updatedItem = menuItemService.updateMenuItem(id, menuItemDetails);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        try {
            menuItemService.deleteMenuItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
