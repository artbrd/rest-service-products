package ru.restserviceproducts.exception;

public class DataNotFoundException extends Exception {
    public DataNotFoundException() {

    }

    public DataNotFoundException(String msg) {
        super(msg);
    }
}
