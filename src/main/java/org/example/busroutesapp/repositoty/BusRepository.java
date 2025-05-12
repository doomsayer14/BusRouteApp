package org.example.busroutesapp.repositoty;

import org.example.busroutesapp.entity.Bus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends CrudRepository<Bus, Long> {
}
