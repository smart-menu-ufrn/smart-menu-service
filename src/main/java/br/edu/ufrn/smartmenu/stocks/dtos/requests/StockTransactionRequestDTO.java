package br.edu.ufrn.smartmenu.stocks.dtos.requests;

public class StockTransactionRequestDTO {

    private Integer value;

    public StockTransactionRequestDTO() {}

    public StockTransactionRequestDTO(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
