package com.example.cardsapp.cardsapp.mappers;

import com.example.cardsapp.cardsapp.utils.Constants.CardStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


public class CardRequest {

    @NotEmpty(message = "Card name is required.")
    String name;
    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Color should be 6 alphanumeric characters prefixed with a #.")
    private String color;
    CardStatus cardstatus;
    @Size(min=5)
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CardStatus getCardstatus() {
        return cardstatus;
    }

    public void setCardstatus(CardStatus cardstatus) {
        this.cardstatus = cardstatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
