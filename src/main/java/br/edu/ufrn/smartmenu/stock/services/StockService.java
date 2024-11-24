package br.edu.ufrn.smartmenu.stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.items.models.Item;
import br.edu.ufrn.smartmenu.items.repositories.ItemRepository;
import br.edu.ufrn.smartmenu.stock.dtos.requests.StockTransactionRequestDTO;
import br.edu.ufrn.smartmenu.stock.dtos.responses.StockTransactionResponseDTO;
import br.edu.ufrn.smartmenu.stock.exceptions.InsufficientItemsInStockException;
import br.edu.ufrn.smartmenu.stock.models.Stock;
import br.edu.ufrn.smartmenu.stock.repositories.StockRepository;

@Service
public class StockService {
    
    @Autowired
    StockRepository stockRepository;

    @Autowired
    ItemRepository itemRepository;

    public StockTransactionResponseDTO increase(
        StockTransactionRequestDTO requestDTO
    ) {
        Item item = itemRepository.findById(
            requestDTO.getItemId()
        ).get();

        Stock stock = stockRepository.findByItem(item).get();

        stock.increase(requestDTO.getValue());

        stockRepository.save(stock);

        StockTransactionResponseDTO responseDTO = new StockTransactionResponseDTO(stock);

        return responseDTO;
    }

    public StockTransactionResponseDTO decrease(
        StockTransactionRequestDTO requestDTO
    ) throws InsufficientItemsInStockException {
        Item item = itemRepository.findById(
            requestDTO.getItemId()
        ).get();

        Stock stock = stockRepository.findByItem(item).get();

        stock.decrease(requestDTO.getValue());

        stockRepository.save(stock);

        StockTransactionResponseDTO responseDTO = new StockTransactionResponseDTO(stock);

        return responseDTO;
    }

}
