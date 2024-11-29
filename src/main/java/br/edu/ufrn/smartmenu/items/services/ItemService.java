package br.edu.ufrn.smartmenu.items.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import br.edu.ufrn.smartmenu.llm.exceptions.EmptyLLMResponse;
import br.edu.ufrn.smartmenu.llm.exceptions.PromptException;
import br.edu.ufrn.smartmenu.llm.service.LLMService;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LLMService llm;

    public List<ItemResponseDTO> getAllItems() {
        List<Item> itemList = itemRepository.findAll();

        List<ItemResponseDTO> itemResponseDTOList = itemList
            .stream()
            .map(ItemResponseDTO::new)
            .collect(Collectors.toList());

        return itemResponseDTOList;
    }

    public List<ItemResponseDTO> getSortedItems() throws PromptException, EmptyLLMResponse {
        List<Item> itemList = itemRepository.findAll();

        String reorderedList = "";

        try {
            reorderedList = llm.processPrompt(itemList).getResponse();
        }  catch (PromptException e) {
            throw e;
        } catch (EmptyLLMResponse e) {
            throw e;
        }
        if (reorderedList != "") {
            List<Long> integerList = Arrays.stream(reorderedList.replaceAll("[\\[\\]]", "").split(","))
                                              .map(String::trim)
                                              .map(Long::parseLong)
                                              .collect(Collectors.toList());
            Map<Long, Integer> ordemMap = new HashMap<>();
            for (int i = 0; i < integerList.size(); i++) {
                ordemMap.put(integerList.get(i), i);
            }

            itemList.sort(Comparator.comparingLong(obj -> ordemMap.get(obj.getId())));
        }

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
