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
@Table(name = "orders")
@org.hibernate.annotations.NamedQuery(name = "Order.getAll", query = "FROM Order")
public class Order {
    @Id
    @SequenceGenerator(name = "order_id_seq_gen", sequenceName = "order_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq_gen")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Room room;

    @Column(name = "date_start_contract", nullable = false)
    private Date dateStartContract;

    @Column(name = "date_end_contract")
    private Date dateEndContract;

    @Column(name = "price", nullable = false)
    private Double price;
}
