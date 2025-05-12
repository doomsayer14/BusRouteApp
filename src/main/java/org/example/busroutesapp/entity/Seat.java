package org.example.busroutesapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Encapsulates bus management. With this class it is easy to add some additional
 * service seats, vip seats, driver seat etc.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer seatNumber;

    @Column
    private Boolean isBooked;

    @Column
    private Boolean isServiceSeat;

    @Column
    private String passengerName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bus bus;
}
