package org.example.busroutesapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates bus management.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bus")
    private List<Seat> seatList = new ArrayList<>();

    //There are several ways we can choose here to declare a relation, but since
    //we have demonstration app let's say that bus can have many routes but route can have
    //only one bus
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bus")
    private List<BusRoute> busRoutes = new ArrayList<>();
}