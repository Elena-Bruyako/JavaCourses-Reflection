package com.bruyako.model;

import com.bruyako.annotations.CustomDateFormat;
import com.bruyako.annotations.JsonValue;

import java.time.LocalDate;

/**
 * Created by brunyatko on 14.09.15.
 */
public class Human{

    private String firstName;
    private String lastName;
    @JsonValue(name = "fun")
    private String hobby;
    @CustomDateFormat(format = "dd-MM-yyyy")
    private LocalDate birthDate;

    public Human(String firstName, String lastName, String hobby, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobby = hobby;
        this.birthDate = birthDate;
    }

}
