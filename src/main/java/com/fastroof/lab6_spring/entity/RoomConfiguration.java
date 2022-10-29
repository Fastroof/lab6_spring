package com.fastroof.lab6_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "room_configurations")
public class RoomConfiguration {
    @Id
    @Column(name = "room_id")
    private Long id;

    @Column(name = "area", nullable = false)
    private Double area;

    @Column(name = "bedroom_count", nullable = false)
    private Integer bedroomCount;

    @Column(name = "price", nullable = false)
    private Integer price;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "room_id")
    private Room room;
}
