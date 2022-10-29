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
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "increment")
    private Long id;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RoomConfiguration configuration;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RoomDescription description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "room")
    private Order order;
}
