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

import br.edu.ufrn.smartmenu.items.dtos.requests.ItemCreateRequestDTO;
import br.edu.ufrn.smartmenu.items.dtos.requests.ItemUpdateRequestDTO;
import br.edu.ufrn.smartmenu.items.dtos.responses.ItemResponseDTO;
import br.edu.ufrn.smartmenu.items.services.ItemService;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        List<ItemResponseDTO> itemResponseDTOList = itemService.getAllItems();

        return ResponseEntity.status(HttpStatus.OK).body(itemResponseDTOList);
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(
        @RequestBody ItemCreateRequestDTO itemCreateRequestDTO
    ) {
        try {
            ItemResponseDTO itemResponseDTO = itemService.createItem(itemCreateRequestDTO);

            URI location = URI.create("/items/" + itemResponseDTO.getId());

            return ResponseEntity.status(HttpStatus.CREATED).location(location).body(itemResponseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(
        @PathVariable Long id
    ) {
        try {
            ItemResponseDTO itemResponseDTO = itemService.getItemById(id);

            return ResponseEntity.status(HttpStatus.OK).body(itemResponseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(
        @PathVariable Long id,
        @RequestBody ItemUpdateRequestDTO itemUpdateRequestDTO
    ) {
        try {
            ItemResponseDTO itemResponseDTO = itemService.updateItem(
                id,
                itemUpdateRequestDTO
            );

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemResponseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
