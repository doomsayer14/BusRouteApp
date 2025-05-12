package org.example.busroutesapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.busroutesapp.entity.Bus;
import org.example.busroutesapp.entity.BusRoute;
import org.example.busroutesapp.entity.Seat;
import org.example.busroutesapp.exception.BusRouteNotFoundException;
import org.example.busroutesapp.repositoty.BusRouteRepository;
import org.example.busroutesapp.service.BusRouteService;
import org.example.busroutesapp.service.BusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Service
public class BusRouteServiceImpl implements BusRouteService {

    private final BusRouteRepository busRouteRepository;

    private final BusService busService;

    //Inner fields
    private final BufferedReader reader;

    public BusRouteServiceImpl(BusRouteRepository busRouteRepository, BusService busService) {
        this.busRouteRepository = busRouteRepository;
        this.busService = busService;

        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Transactional
    @Override
    public void createRoute() throws IOException {
        BusRoute busRoute = new BusRoute();

        System.out.println("Write a direction for the bus route:");
        busRoute.setDirection(reader.readLine());

        System.out.println("Write an amount of seats for the bus:");
        int seatsAmount = Integer.parseInt(reader.readLine());
        if (seatsAmount <= 0) {
            throw new IllegalArgumentException("Seats amount must be greater than 0");
        }
        Bus bus = new Bus();
        for (int i = 0; i < seatsAmount; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i + 1);
            bus.getSeatList().add(seat);
        }

        //case when we have not even an amount of seats
        if (seatsAmount % 2 != 0) {
            System.out.println("Write seats number for service seats for the bus:");
            String inputServiceSeats = reader.readLine();
            String[] parts = inputServiceSeats.split(" ");
            for (String part : parts) {
                try {
                    int serviceSeatNumber = Integer.parseInt(part);
                    Seat serviceSeat = Seat.builder()
                            .isServiceSeat(true)
                            .seatNumber(serviceSeatNumber)
                            .build();
                    bus.getSeatList().set(serviceSeat.getSeatNumber() - 1, serviceSeat);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + part);
                }
            }
        }
        busRoute.setBus(bus);
        busService.saveBus(bus);
        busRouteRepository.save(busRoute);
    }

    //Isolation level is arguable here, for example ChatGPT advices serializable level
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void bookSeat() throws IOException {
        BusRoute busRoute = getBusRoute();

        log.info("Write your name:");
        String passengerName = reader.readLine();

        while (true) {
            Bus bus = busRoute.getBus();
            List<Seat> seatList = bus.getSeatList();
            log.info("Write a number of the seat you want to book:");
            int seatNumber = Integer.parseInt(reader.readLine());
            if (seatNumber <= 0 || seatNumber > seatList.size()) {
                throw new IllegalArgumentException("Seat number must be greater than 0 or and" +
                        " less than " + seatList.size());
            }
            Seat seat = seatList.get(seatNumber - 1);
            if (Boolean.FALSE.equals(seat.getIsBooked()) ||
                    Boolean.FALSE.equals(seat.getIsServiceSeat())) {
                seatList.set(seatNumber - 1,
                        Seat.builder()
                                .isBooked(true)
                                .passengerName(passengerName).build());
                busService.saveBus(bus);
                return;
            }
            log.info("The seat is already been booked or is a service seat!");
        }
    }

    @Transactional
    @Override
    public void cancelBooking() throws IOException {
        BusRoute busRoute = getBusRoute();

        log.info("Write a number of the seat you want to cancel booking:");
        int seatNumber = Integer.parseInt(reader.readLine());

        Bus bus = busRoute.getBus();
        List<Seat> seatList = bus.getSeatList();
        seatList.get(seatNumber - 1).setIsBooked(true);

        busService.saveBus(bus);
    }

    @Override
    public void showAvailableSeats() throws IOException {
        BusRoute busRoute = getBusRoute();
        List<Seat> seatList = busRoute.getBus().getSeatList();

        //just drawing menu like
        //1 2  3 4
        //5 6  7 8
        //9 10  11 12
        //13 14  16 17
        //18 19  20 21
        for (int i = 0; i < seatList.size(); i++) {
            if (Boolean.FALSE.equals(seatList.get(i).getIsBooked()) ||
                    Boolean.FALSE.equals(seatList.get(i).getIsServiceSeat())) {
                if (i < 10) {
                    log.info("   ");
                } else {
                    log.info("  ");
                }
            } else {
                log.info(seatList.get(i) + " ");
            }
            if (seatList.get(i).getSeatNumber() % 2 == 0) {
                log.info(" ");
            }
            if (seatList.get(i).getSeatNumber() % 4 == 0) {
                log.info(System.lineSeparator());
            }

        }

    }

    /**
     * Asks for a bus route and returns it.
     *
     * @return bus route.
     * @throws IOException in case of wrong input
     */
    private BusRoute getBusRoute() throws IOException {
        log.info("Write an id of the bus route:");
        return busRouteRepository.findById(reader.readLine())
                .orElseThrow(() -> new BusRouteNotFoundException
                        ("A bus route cannot be found for the provided id"));
    }

}
