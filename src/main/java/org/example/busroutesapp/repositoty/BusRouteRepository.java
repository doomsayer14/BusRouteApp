package org.example.busroutesapp.repositoty;

import org.example.busroutesapp.entity.BusRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRouteRepository extends CrudRepository<BusRoute, String> {
}
