package com.fastroof.lab6_spring.repository;

import com.fastroof.lab6_spring.entity.RoomConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomConfigurationRepository extends CrudRepository<RoomConfiguration, Long> {
    Iterable<RoomConfiguration> findAllByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price);
    @Query(value = "SELECT u FROM RoomConfiguration u WHERE (:area is null OR u.area = :area)" +
            " AND (:bedroomCount is null OR u.bedroomCount = :bedroomCount)" +
            " AND (:price is null OR u.price = :price)")
    Page<RoomConfiguration> findAllByAreaAndBedroomCountAndPriceWithPagination(Pageable pageable,
                                                                               @Param("area") Double area,
                                                                               @Param("bedroomCount") Integer bedroomCount,
                                                                               @Param("price") Integer price);
}
