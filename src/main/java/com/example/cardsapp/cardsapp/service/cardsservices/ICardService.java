package com.example.cardsapp.cardsapp.service.cardsservices;

import com.example.cardsapp.cardsapp.mappers.CardRequest;
import com.example.cardsapp.cardsapp.mappers.PaginatedCardResponse;
import com.example.cardsapp.cardsapp.mappers.UpdateRequest;
import com.example.cardsapp.cardsapp.model.CardsModel;
import com.example.cardsapp.cardsapp.model.UserModel;
import com.example.cardsapp.cardsapp.utils.Constants.UserRole;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ICardService {
    public boolean doProcessCard(CardRequest request,String emailAddress);

    public PaginatedCardResponse getAllCards(String emailAddress, UserRole role,Pageable pageable);

    public PaginatedCardResponse getAllCardsByEmail(String emailAddress, Pageable pageable);

    public CardsModel getCard(Long cardId, String emailAddress, UserRole role);

    public boolean deleteCard(Long cardId, String emailAddress, UserRole role);

    public UserModel validateUser(String emailAddress);

    public CardsModel doUpdateCard(UpdateRequest request, Long cardId, String emailAddress, UserRole role);



}
