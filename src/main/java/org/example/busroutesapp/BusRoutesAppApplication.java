package org.example.busroutesapp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.busroutesapp.service.BusRouteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Data
@SpringBootApplication
public class BusRoutesAppApplication implements CommandLineRunner {

    //Beans
    private final BusRouteService busRouteService;

    //Inner fields
    private final BufferedReader reader;

    private String option;

    public BusRoutesAppApplication(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;

        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        SpringApplication.run(BusRoutesAppApplication.class, args);
    }

    /**
     * Main method which will be run like main menu
     */
    @Override
    public void run(String... args) {
        while (true) {
            System.out.println(
                    "Choose one option (type only numbers):" + System.lineSeparator() +
                            "1. Create route." + System.lineSeparator() +
                            "2. Book a seat." + System.lineSeparator() +
                            "3. Cancel booking." + System.lineSeparator() +
                            "4. Show available seats." + System.lineSeparator() +
                            "5. Close the program.");
            try {
                setOption(reader.readLine());
                //exit case
                if (getOption().equals("5")) {
                    return;
                }
                doOption();
            } catch (IOException e) {
                log.error("Wrong input!");
            } catch (IllegalArgumentException e) {
                log.error("Please, type right option.");
            }
        }
    }

    /**
     * Depend on user's choice the program will do appropriate task
     */
    private void doOption() throws IOException {
        switch (getOption()) {
            case "1":
                busRouteService.createRoute();
                break;
            case "2":
                busRouteService.bookSeat();
                break;
            case "3":
                busRouteService.cancelBooking();
                break;
            case "4":
                busRouteService.showAvailableSeats();
                break;
            default:
                //default covers all cases when 1 > option || 5 < option
                throw new IllegalArgumentException();
        }
    }
}
