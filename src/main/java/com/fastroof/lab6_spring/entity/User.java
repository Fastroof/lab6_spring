package com.fastroof.lab6_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fastroof.lab6_spring.enums.Provider;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @JsonIgnore
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private String fullName;
    @JsonIgnore
    private Provider provider;
}
