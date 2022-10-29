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
@Table(name = "rooms")
public class Room {
    @Id
    @SequenceGenerator(name = "room_id_seq_gen", sequenceName = "room_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_seq_gen")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RoomConfiguration configuration;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RoomDescription description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "room")
    private Order order;
}
