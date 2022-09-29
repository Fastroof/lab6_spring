package com.fastroof.lab6_spring.repository;

import com.fastroof.lab6_spring.entity.RoomConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomConfigurationRepository extends CrudRepository<RoomConfiguration, Long> {
    Iterable<RoomConfiguration> findAllByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price);
}
