package br.edu.ufrn.smartmenu.stocks.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.items.models.Item;
import br.edu.ufrn.smartmenu.items.repositories.ItemRepository;
import br.edu.ufrn.smartmenu.stocks.dtos.requests.StockTransactionRequestDTO;
import br.edu.ufrn.smartmenu.stocks.dtos.responses.StockResponseDTO;
import br.edu.ufrn.smartmenu.stocks.exceptions.InsufficientItemsInStockException;
import br.edu.ufrn.smartmenu.stocks.models.Stock;
import br.edu.ufrn.smartmenu.stocks.repositories.StockRepository;

@Service
public class StockService {
    
    @Autowired
    StockRepository stockRepository;

    @Autowired
    ItemRepository itemRepository;

    public List<StockResponseDTO> getAllStocks() {
        List<Stock> stockList = stockRepository.findAll();

        List<StockResponseDTO> responseDTOList = stockList
            .stream()
            .map(StockResponseDTO::new)
            .collect(Collectors.toList());

        return responseDTOList;
    }

    public StockResponseDTO getStockByItemId(
        Long itemId
    ) {
        Item item = itemRepository.findById(itemId).get();

        Stock stock = stockRepository.findByItem(item).get();

        StockResponseDTO responseDTO = new StockResponseDTO(stock);

        return responseDTO;
    }

    public StockResponseDTO increase(
        Long itemId,
        StockTransactionRequestDTO requestDTO
    ) {
        Item item = itemRepository.findById(itemId).get();

        Stock stock = stockRepository.findByItem(item).get();

        stock.increase(requestDTO.getValue());

        stockRepository.save(stock);

        StockResponseDTO responseDTO = new StockResponseDTO(stock);

        return responseDTO;
    }

    public StockResponseDTO decrease(
        Long itemId,
        StockTransactionRequestDTO requestDTO
    ) throws InsufficientItemsInStockException {
        Item item = itemRepository.findById(itemId).get();

        Stock stock = stockRepository.findByItem(item).get();

        stock.decrease(requestDTO.getValue());

        stockRepository.save(stock);

        StockResponseDTO responseDTO = new StockResponseDTO(stock);

        return responseDTO;
    }

}
