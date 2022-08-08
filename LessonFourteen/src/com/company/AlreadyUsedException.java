package com.company;

public class AlreadyUsedException extends Exception {

    public AlreadyUsedException() {
        super("Неверная ячейка!");
    }
}
