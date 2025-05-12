package org.example.busroutesapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.busroutesapp.entity.Bus;
import org.example.busroutesapp.repositoty.BusRepository;
import org.example.busroutesapp.service.BusService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public Bus saveBus(Bus bus) {
        log.info("Saving bus: {}", bus);
        return busRepository.save(bus);
    }
}
