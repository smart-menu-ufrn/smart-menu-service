package br.edu.ufrn.smartmenu.stocks.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.items.dtos.responses.ItemResponseDTO;
import br.edu.ufrn.smartmenu.stocks.models.Stock;

@JsonInclude(Include.NON_NULL)
public class StockResponseDTO {

    private ItemResponseDTO item;
    private Integer quantity;

    public StockResponseDTO() {}

    public StockResponseDTO(
        ItemResponseDTO item,
        Integer quantity
    ) {
        this.item = item;
        this.quantity = quantity;
    }

    public StockResponseDTO(
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
