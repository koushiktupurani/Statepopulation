package com.DataConv.Statepop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Districtpop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = " Enter valid District name")
    private String distname;
    @NotNull(message = " Enter valid Population")
    private long population;

}
