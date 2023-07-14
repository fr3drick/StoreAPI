package com.fred.store.exceptions;

public class OrderAlreadyLinkedException extends Exception{
    public OrderAlreadyLinkedException(String message) {
        super(message);
    }

}
