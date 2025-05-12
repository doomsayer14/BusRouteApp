package org.example.busroutesapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Root entity for this app, includes information about bus route.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String direction;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bus bus;

}