package br.edu.ufrn.smartmenu.stocks.controllers;

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

import br.edu.ufrn.smartmenu.stocks.dtos.requests.StockTransactionRequestDTO;
import br.edu.ufrn.smartmenu.stocks.dtos.responses.StockResponseDTO;
import br.edu.ufrn.smartmenu.stocks.exceptions.InsufficientItemsInStockException;
import br.edu.ufrn.smartmenu.stocks.services.StockService;

@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins = "http://localhost:3000")
public class StockController {
    
    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> getAllStocks() {
        List<StockResponseDTO> responseDTOList = stockService.getAllStocks();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<StockResponseDTO> getStockByItemId(
        @PathVariable Long itemId
    ) {
        try {
            StockResponseDTO responseDTO = stockService.getStockByItemId(itemId);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{itemId}/increase")
    public ResponseEntity<StockResponseDTO> increase(
        @PathVariable Long itemId,
        @RequestBody StockTransactionRequestDTO requestDTO
    ) {
        try {
            StockResponseDTO responseDTO = stockService.increase(
                itemId,
                requestDTO
            );

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{itemId}/decrease")
    public ResponseEntity<StockResponseDTO> decrease(
        @PathVariable Long itemId,
        @RequestBody StockTransactionRequestDTO requestDTO
    ) {
        try {
            StockResponseDTO responseDTO = stockService.decrease(
                itemId,
                requestDTO
            );

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InsufficientItemsInStockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
