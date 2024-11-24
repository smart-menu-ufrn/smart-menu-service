package br.edu.ufrn.smartmenu.stock.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.items.dtos.responses.ItemResponseDTO;
import br.edu.ufrn.smartmenu.stock.models.Stock;

@JsonInclude(Include.NON_NULL)
public class StockTransactionResponseDTO {

    private ItemResponseDTO item;
    private Integer quantity;

    public StockTransactionResponseDTO() {}

    public StockTransactionResponseDTO(
        ItemResponseDTO item,
        Integer quantity
    ) {
        this.item = item;
        this.quantity = quantity;
    }

    public StockTransactionResponseDTO(
        Stock stock
    ) {
        this.item = new ItemResponseDTO(stock.getItem());
        this.quantity = stock.getQuantity();
    }

    public ItemResponseDTO getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
