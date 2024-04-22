package com.example.cardsapp.cardsapp.mappers;

import com.example.cardsapp.cardsapp.model.CardsModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedCardResponse {
    private List<CardsModel> cardsList;
    private Long numberOfItems;
    private int numberOfPages;
}
