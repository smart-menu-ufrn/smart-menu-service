package br.edu.ufrn.smartmenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.model.Stock;
import br.edu.ufrn.smartmenu.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository StockRepository;

    public List<Stock> getAllStocks() {
        return StockRepository.findAll();
    }

    public Optional<Stock> getStockById(Long id) {
        return StockRepository.findById(id);
    }

    public Stock createStock(Stock stock) {
        return StockRepository.save(stock);
    }

    public Stock updateStock(Long id, Stock stockDetails) {
        Stock stock = StockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("stock not found"));


        stock.setQuantity(stockDetails.getQuantity());
        stock.setMenuItem(stockDetails.getMenuItem());

        return StockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        Stock stock = StockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("stock not found"));


        StockRepository.delete(stock);
    }
}
