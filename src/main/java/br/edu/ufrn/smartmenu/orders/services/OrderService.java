package br.edu.ufrn.smartmenu.orders.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.items.models.Item;
import br.edu.ufrn.smartmenu.items.repositories.ItemRepository;
import br.edu.ufrn.smartmenu.orders.dtos.requests.OrderedItemCreateRequestDTO;
import br.edu.ufrn.smartmenu.orders.dtos.responses.OrderResponseDTO;
import br.edu.ufrn.smartmenu.orders.models.Order;
import br.edu.ufrn.smartmenu.orders.models.OrderedItem;
import br.edu.ufrn.smartmenu.orders.repositories.OrderRepository;
import br.edu.ufrn.smartmenu.stocks.exceptions.InsufficientItemsInStockException;
import br.edu.ufrn.smartmenu.stocks.models.Stock;
import br.edu.ufrn.smartmenu.stocks.repositories.StockRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StockRepository stockRepository;

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();

        List<OrderResponseDTO> responseDTOList = orderList
            .stream()
            .map(OrderResponseDTO::new)
            .collect(Collectors.toList());

        return responseDTOList;
    }

    public OrderResponseDTO getOrderById(
        Long id
    ) throws NoSuchElementException {
        Order order = orderRepository.findById(id).get();

        OrderResponseDTO responseDTO = new OrderResponseDTO(order);

        return responseDTO;
    }

    public OrderResponseDTO createOrder(
        List<OrderedItemCreateRequestDTO> orderCreateRequestDTOList
    ) throws NoSuchElementException, InsufficientItemsInStockException {
        List<OrderedItem> orderedItemList = new ArrayList<>();

        List<Stock> decreasedStockList = new ArrayList<>();

        for (OrderedItemCreateRequestDTO requestDTO : orderCreateRequestDTOList) {
            Item item = itemRepository.findById(requestDTO.getItemId()).get();

            Stock stock = stockRepository.findByItem(item).get();

            stock.decrease(requestDTO.getQuantity());

            decreasedStockList.add(stock);

            OrderedItem orderedItem = new OrderedItem(
                item,
                requestDTO.getQuantity()
            );

            orderedItemList.add(orderedItem);
        }

        Order order = new Order(orderedItemList);

        order = orderRepository.save(order);

        stockRepository.saveAll(decreasedStockList);

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order);
    
        return orderResponseDTO;
    }

}
