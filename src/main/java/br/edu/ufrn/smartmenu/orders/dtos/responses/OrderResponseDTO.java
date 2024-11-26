package br.edu.ufrn.smartmenu.orders.dtos.responses;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.orders.models.Order;

@JsonInclude(Include.NON_NULL)
public class OrderResponseDTO {
    
    private Long id;
    private List<OrderedItemResponseDTO> orderedItems;
    private Double price;

    public OrderResponseDTO() {};

    public OrderResponseDTO(
        Long id,
        List<OrderedItemResponseDTO> orderedItems,
        Double price
    ) {
        this.id = id;
        this.orderedItems = orderedItems;
        this.price = price;
    }

    public OrderResponseDTO(
        Order order
    ) {
        this.id = order.getId();
        this.orderedItems = order.getOrderedItems()
            .stream()
            .map(OrderedItemResponseDTO::new)
            .collect(Collectors.toList());
        this.price = order.getPrice();
    }

    public Long getId() {
        return id;
    }

    public List<OrderedItemResponseDTO> getOrderedItems() {
        return orderedItems;
    }

    public Double getPrice() {
        return price;
    }

}
