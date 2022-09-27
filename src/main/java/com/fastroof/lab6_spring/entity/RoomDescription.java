package com.fastroof.lab6_spring.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDescription {
    private Long id;
    @NotNull
    @Size(min = 1)
    private String description;
    @NotNull
    @Size(min = 1)
    private String address;
    private Date creationDate;
}
