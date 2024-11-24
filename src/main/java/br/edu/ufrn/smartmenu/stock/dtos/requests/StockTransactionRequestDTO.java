package br.edu.ufrn.smartmenu.stock.dtos.requests;

public class StockTransactionRequestDTO {
    
    private Long itemId;

    private Integer value;

    public StockTransactionRequestDTO() {}

    public StockTransactionRequestDTO(Long itemId, Integer value) {
        this.itemId = itemId;
        this.value = value;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getValue() {
        return value;
    }

}
