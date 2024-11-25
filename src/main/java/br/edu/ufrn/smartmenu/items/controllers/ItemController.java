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
        List<ItemResponseDTO> responseDTOList = itemService.getAllItems();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(
        @RequestBody ItemCreateRequestDTO requestDTO
    ) {
        try {
            ItemResponseDTO responseDTO = itemService.createItem(requestDTO);

            URI location = URI.create("/items/" + responseDTO.getId());

            return ResponseEntity.status(HttpStatus.CREATED).location(location).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(
        @PathVariable Long id
    ) {
        try {
            ItemResponseDTO responseDTO = itemService.getItemById(id);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(
        @PathVariable Long id,
        @RequestBody ItemUpdateRequestDTO requestDTO
    ) {
        try {
            ItemResponseDTO responseDTO = itemService.updateItem(
                id,
                requestDTO
            );

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
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
