package com.example.cardsapp.cardsapp.mappers;

import com.example.cardsapp.cardsapp.utils.Constants.CardStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UpdateRequest {
    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Color should be 6 alphanumeric characters prefixed with a #.")
    String color;
    CardStatus cardstatus;
    String description;
    String name;

}
