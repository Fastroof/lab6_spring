package com.fastroof.lab6_spring.repository;

import com.fastroof.lab6_spring.entity.RoomDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDescriptionRepository extends CrudRepository<RoomDescription, Long> {
}
