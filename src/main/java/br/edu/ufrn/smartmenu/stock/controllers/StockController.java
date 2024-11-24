package br.edu.ufrn.smartmenu.stock.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.stock.dtos.requests.StockTransactionRequestDTO;
import br.edu.ufrn.smartmenu.stock.dtos.responses.StockTransactionResponseDTO;
import br.edu.ufrn.smartmenu.stock.exceptions.InsufficientItemsInStockException;
import br.edu.ufrn.smartmenu.stock.services.StockService;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost:3000")
public class StockController {
    
    @Autowired
    private StockService stockService;

    @PostMapping("/increase")
    public ResponseEntity<StockTransactionResponseDTO> increase(
        @RequestBody StockTransactionRequestDTO requestDTO
    ) {
        try {
            StockTransactionResponseDTO responseDTO = stockService.increase(requestDTO);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/decrease")
    public ResponseEntity<StockTransactionResponseDTO> decrease(
        @RequestBody StockTransactionRequestDTO requestDTO
    ) {
        try {
            StockTransactionResponseDTO responseDTO = stockService.decrease(requestDTO);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InsufficientItemsInStockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
