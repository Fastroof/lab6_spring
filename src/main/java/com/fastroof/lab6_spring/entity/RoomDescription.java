package com.fastroof.lab6_spring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "room_descriptions")
public class RoomDescription {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "room_id")
    private Room room;
}
