package br.edu.ufrn.smartmenu.orders.controllers;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.orders.dtos.requests.OrderedItemCreateRequestDTO;
import br.edu.ufrn.smartmenu.orders.dtos.responses.OrderResponseDTO;
import br.edu.ufrn.smartmenu.orders.services.OrderService;
import br.edu.ufrn.smartmenu.stocks.exceptions.InsufficientItemsInStockException;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> responseDTOList = orderService.getAllOrders();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
        @RequestBody List<OrderedItemCreateRequestDTO> requestDTOList
    ) {
        try {
            OrderResponseDTO responseDTO = orderService.createOrder(requestDTOList);

            URI location = URI.create("/orders/" + responseDTO.getId());

            return ResponseEntity.status(HttpStatus.CREATED).location(location).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InsufficientItemsInStockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(
        @PathVariable Long id
    ) {
        try {
            OrderResponseDTO responseDTO = orderService.getOrderById(id);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
