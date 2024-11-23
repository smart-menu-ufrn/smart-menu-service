package br.edu.ufrn.smartmenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.model.OrderedItem;

import br.edu.ufrn.smartmenu.repository.OrderedItemRepository;

import br.edu.ufrn.smartmenu.service.StockService;
import br.edu.ufrn.smartmenu.service.MenuItemService;



import java.util.List;
import java.util.Optional;

@Service
public class OrderedItemService {

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public List<OrderedItem> getAllOrderedItem() {
        return orderedItemRepository.findAll();
    }

    public Optional<OrderedItem> getOrderedItemById(Long id) {
        return orderedItemRepository.findById(id);
    }

    public OrderedItem createOrderedItem(OrderedItem orderedItem) {
        // todas vez que eu criar uma ordem, devo diminuir
        // quantidade de stock - quantidade de stock

        // devo pegar o stock pelo menuItem que ele tem

        // MenuItemService mis = new MenuItemService();
        // StockService os = new StockService();

        // MenuItem menuItem = mis.getMenuItemById(orderedItem.getMenuItem().getId());

        // Stock stock = menuItem.get



        return orderedItemRepository.save(orderedItem);
    }

    public OrderedItem updateOrderedItem(Long id, OrderedItem orderedItemDetails) {
        OrderedItem orderedItem = orderedItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Item not found"));


        orderedItem.setQuantity(orderedItemDetails.getQuantity());
        orderedItem.setMenuItem(orderedItemDetails.getMenuItem());

        return orderedItemRepository.save(orderedItem);
    }

    public void deleteOrderedItem(Long id) {
        OrderedItem orderedItem = orderedItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Item not found"));


        orderedItemRepository.delete(orderedItem);
    }
}
