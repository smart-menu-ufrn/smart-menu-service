package br.edu.ufrn.smartmenu.stocks.exceptions;

public class InsufficientItemsInStockException extends Exception {

    public InsufficientItemsInStockException() {}

    public InsufficientItemsInStockException(String message) {
        super(message);
    }

}
