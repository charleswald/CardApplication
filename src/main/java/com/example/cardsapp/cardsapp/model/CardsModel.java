package com.example.cardsapp.cardsapp.model;

import com.example.cardsapp.cardsapp.utils.Constants.CardStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cards")
public class CardsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String color;
    private String description;
    private CardStatus status;
    private String emailAddress;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
