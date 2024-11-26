package br.edu.ufrn.smartmenu.orders.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.orders.models.OrderedItem;
import br.edu.ufrn.smartmenu.items.models.Item;

import br.edu.ufrn.smartmenu.orders.services.OrderedItemService;
import br.edu.ufrn.smartmenu.items.services.ItemService;

import br.edu.ufrn.smartmenu.orders.dtos.OrderedItemDTO;
import br.edu.ufrn.smartmenu.items.dtos.responses.ItemResponseDTO;


@RestController
@RequestMapping("/api/item/order")
public class OrderedItemController {
    @Autowired
    private OrderedItemService orderedItemService;

    @Autowired
    private ItemService menuItemService;

    @GetMapping
    public List<OrderedItem> getAllOrderedItem(){
   
        return orderedItemService.getAllOrderedItem();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderedItem> getOrderedItemById(@PathVariable Long id){
   
        return orderedItemService.getOrderedItemById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderedItem createOrderedItem(@RequestBody OrderedItemDTO orderedItemDetails){
        
        ItemResponseDTO itemdto = menuItemService.getItemById(orderedItemDetails.getMenuItemId());

        Item item = new Item();
        item.setId(itemdto.getId());
        item.setName(itemdto.getName());

        
        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setMenuItem(item);
        orderedItem.setQuantity(orderedItemDetails.getQuantity());


        return orderedItemService.createOrderedItem(orderedItem);
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderedItem> updateOrderedItem(@PathVariable Long id, @RequestBody OrderedItemDTO orderedItemDetails){

        ItemResponseDTO itemdto = menuItemService.getItemById(orderedItemDetails.getMenuItemId());

        Item item = new Item();
        item.setId(itemdto.getId());
        item.setName(itemdto.getName());

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setMenuItem(item);
        orderedItem.setQuantity(orderedItemDetails.getQuantity());


        try{
            OrderedItem updatedOrderedItem = orderedItemService.updateOrderedItem(id, orderedItem);
            return ResponseEntity.ok(updatedOrderedItem);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderedItem(@PathVariable Long id){

        try{
            orderedItemService.deleteOrderedItem(id);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }


}