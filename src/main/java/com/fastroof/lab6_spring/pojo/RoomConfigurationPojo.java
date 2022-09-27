package com.fastroof.lab6_spring.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RoomConfigurationPojo {
    @NotNull
    @Min(1)
    private Double area;
    @NotNull
    @Min(1)
    private Integer bedroomCount;
    @NotNull
    @Min(1)
    private Integer price;
}
