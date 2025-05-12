package org.example.busroutesapp.service;

import org.example.busroutesapp.entity.Bus;

public interface BusService {
    /**
     * Saves the specified bus into DB.
     *
     * @param bus bus to be saved.
     * @return saved bus.
     */
    Bus saveBus(Bus bus);
}
