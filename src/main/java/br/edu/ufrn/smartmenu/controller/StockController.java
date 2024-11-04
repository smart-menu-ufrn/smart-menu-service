package br.edu.ufrn.smartmenu.controller;

import java.util.List;

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

import br.edu.ufrn.smartmenu.model.Stock;
import br.edu.ufrn.smartmenu.service.StockService;
import br.edu.ufrn.smartmenu.service.MenuItemService;


@RestController
@RequestMapping("/api/item/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public List<Stock> getAllStock(){
   
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id){
   
        return stockService.getStockById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Stock createStock(@RequestBody Stock stockDetails){
    
        return stockService.createStock(stockDetails);
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stockDetails){

        try{
            Stock updatedStock = stockService.updateStock(id, stockDetails);
            return ResponseEntity.ok(updatedStock);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id){

        try{
            stockService.deleteStock(id);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }


}
