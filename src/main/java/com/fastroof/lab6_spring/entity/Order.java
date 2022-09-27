package com.fastroof.lab6_spring.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Long id;
    private User user;
    private Room room;
    private Date dateStartContract;
    private Date dateEndContract;
    private Double price;
}
