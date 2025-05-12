CREATE SCHEMA IF NOT EXISTS bus_routes_app;

-- Create Bus table
CREATE TABLE bus_routes_app.bus
(
    id BIGSERIAL PRIMARY KEY
);

-- Create BusRoute table
CREATE TABLE bus_routes_app.bus_route
(
    id        uuid PRIMARY KEY,
    direction TEXT,
    bus_id    BIGINT,
    FOREIGN KEY (bus_id) REFERENCES bus (id)
);

-- Create Seat table
CREATE TABLE bus_routes_app.seat
(
    id              BIGSERIAL PRIMARY KEY,
    seat_number     INT,
    is_booked       BOOLEAN DEFAULT FALSE,
    is_service_seat BOOLEAN DEFAULT FALSE,
    passenger_name  TEXT,
    bus_id          BIGINT,
    FOREIGN KEY (bus_id) REFERENCES bus (id)
);