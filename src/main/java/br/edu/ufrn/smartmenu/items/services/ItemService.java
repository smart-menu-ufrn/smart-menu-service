package br.edu.ufrn.smartmenu.items.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.items.dtos.requests.ItemCreateRequestDTO;
import br.edu.ufrn.smartmenu.items.dtos.requests.ItemUpdateRequestDTO;
import br.edu.ufrn.smartmenu.items.dtos.responses.ItemResponseDTO;
import br.edu.ufrn.smartmenu.items.models.Category;
import br.edu.ufrn.smartmenu.items.models.Item;
import br.edu.ufrn.smartmenu.items.repositories.CategoryRepository;
import br.edu.ufrn.smartmenu.items.repositories.ItemRepository;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ItemResponseDTO> getAllItems() {
        List<Item> itemList = itemRepository.findAll();

        List<ItemResponseDTO> itemResponseDTOList = itemList
            .stream()
            .map(ItemResponseDTO::new)
            .collect(Collectors.toList());

        return itemResponseDTOList;
    }

    public ItemResponseDTO getItemById(
        Long id
    ) throws NoSuchElementException {
        Item item = itemRepository.findById(id).get();

        ItemResponseDTO itemResponseDTO = new ItemResponseDTO(item);

        return itemResponseDTO;
    }

    public ItemResponseDTO createItem(
        ItemCreateRequestDTO itemCreateRequestDTO
    ) throws NoSuchElementException {
        Item item = itemCreateRequestDTO.toEntity();
        
        Category category = categoryRepository.findById(itemCreateRequestDTO.getCategoryId()).get();

        item.setCategory(category);

        item = itemRepository.save(item);

        ItemResponseDTO itemResponseDTO = new ItemResponseDTO(item);

        return itemResponseDTO;
    }

    public ItemResponseDTO updateItem(
        Long id,
        ItemUpdateRequestDTO itemUpdateRequestDTO
    ) throws NoSuchElementException {
        Optional<Category> category = Optional.empty();

        if (itemUpdateRequestDTO.getCategoryId() != null) {
            category = categoryRepository.findById(itemUpdateRequestDTO.getCategoryId());
        }

        Item item = itemRepository.findById(id).get();

        Item updatedItem = itemUpdateRequestDTO.updateEntity(item, category);

        itemRepository.save(updatedItem);

        ItemResponseDTO itemResponseDTO = new ItemResponseDTO(updatedItem);

        return itemResponseDTO;
    }

    public void deleteItem(
        Long id
    ) throws NoSuchElementException {
        Item item = itemRepository.findById(id).get();

        itemRepository.delete(item);
    }

}
