package org.example.busroutesapp.exception;

public class BusRouteNotFoundException extends RuntimeException {
    public BusRouteNotFoundException(String message) {
        super(message);
    }
}
