package br.edu.ufrn.smartmenu.controller;

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

import br.edu.ufrn.smartmenu.model.OrderedItem;
import br.edu.ufrn.smartmenu.model.MenuItem;

import br.edu.ufrn.smartmenu.service.OrderedItemService;
import br.edu.ufrn.smartmenu.service.MenuItemService;

import br.edu.ufrn.smartmenu.dto.OrderedItemDTO;


@RestController
@RequestMapping("/api/item/order")
public class OrderedItemController {
    @Autowired
    private OrderedItemService orderedItemService;

    @Autowired
    private MenuItemService menuItemService;

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
    
        Optional<MenuItem> menuItemOptional = menuItemService.getMenuItemById(orderedItemDetails.getMenuItemId());
        MenuItem menuItem = menuItemOptional.orElseThrow(() -> new RuntimeException("Order Item not found"));

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setMenuItem(menuItem);
        orderedItem.setQuantity(orderedItemDetails.getQuantity());


        return orderedItemService.createOrderedItem(orderedItem);
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderedItem> updateOrderedItem(@PathVariable Long id, @RequestBody OrderedItemDTO orderedItemDetails){

        Optional<MenuItem> menuItemOptional = menuItemService.getMenuItemById(orderedItemDetails.getMenuItemId());
        MenuItem menuItem = menuItemOptional.orElseThrow(() -> new RuntimeException("Order Item not found"));

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setMenuItem(menuItem);
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
