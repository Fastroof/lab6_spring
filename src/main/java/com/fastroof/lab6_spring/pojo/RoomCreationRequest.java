package com.fastroof.lab6_spring.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RoomCreationRequest {
    @NotNull
    @Valid
    private RoomConfigurationPojo configuration;
    @NotNull
    @Valid
    private RoomDescriptionPojo description;
}
