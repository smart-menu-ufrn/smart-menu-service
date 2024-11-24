package br.edu.ufrn.smartmenu.users.exceptions;

public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException() {}

    public IncorrectPasswordException(String message) {
        super(message);
    }

}
