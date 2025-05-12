package org.example.busroutesapp.service;

import java.io.IOException;

/**
 * Business logic layer for {@link org.example.busroutesapp.entity.BusRoute}
 */
public interface BusRouteService {

    /**
     * Asks direction and amount of seats for the bus route. I case of not even an amount of
     * passengers reserves service seats, then saves.
     *
     * @throws IllegalArgumentException if provided seat amount is less or equal 0.
     */
    void createRoute() throws IOException;

    /**
     * Ask for a name and a seat that passenger wants to book.
     *
     * @throws IllegalArgumentException if provided seat number is less than 0 or more than
     *                                  seat amount.
     */
    void bookSeat() throws IOException;

    /**
     * Asks for seat number and cancels booking.
     *
     * @throws IllegalArgumentException if provided seat number is less than 0 or more than
     *                                  seat amount.
     */
    void cancelBooking() throws IOException;

    /**
     * Shows available seats.
     */
    void showAvailableSeats() throws IOException;
}
