package com.example.cardsapp.cardsapp.service.cardsservices;

import com.example.cardsapp.cardsapp.mappers.CardRequest;
import com.example.cardsapp.cardsapp.mappers.PaginatedCardResponse;
import com.example.cardsapp.cardsapp.mappers.UpdateRequest;
import com.example.cardsapp.cardsapp.model.CardsModel;
import com.example.cardsapp.cardsapp.model.UserModel;
import com.example.cardsapp.cardsapp.repos.CardsRepository;
import com.example.cardsapp.cardsapp.repos.UserRepository;
import com.example.cardsapp.cardsapp.utils.Constants.CardStatus;
import com.example.cardsapp.cardsapp.utils.Constants.Constants;
import com.example.cardsapp.cardsapp.utils.Constants.UserRole;
import com.example.cardsapp.cardsapp.utils.exeptions.ForbidenExeption;
import com.example.cardsapp.cardsapp.utils.exeptions.ResourceNotFoundExeption;
import com.example.cardsapp.cardsapp.utils.exeptions.SystemMalfunctionExemption;
import com.example.cardsapp.cardsapp.utils.utilities.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CardService implements ICardService {

    //Declare the repo's
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardsRepository cardsRepository;

    @Autowired
    public CardService(UserRepository userRepository, CardsRepository cardsRepository) {
        this.userRepository = userRepository;
        this.cardsRepository = cardsRepository;
    }

    @Override
    public boolean doProcessCard(CardRequest request,String emailAddress) {

        try {

            CardsModel model = new CardsModel();

            Optional.ofNullable(request.getColor()).ifPresent(model::setColor);
            Optional.ofNullable(request.getDescription()).ifPresent(model::setDescription);
            model.setEmailAddress(emailAddress);
            model.setName(request.getName());
            model.setStatus(CardStatus.TODO);
            model.setCreatedOn(Utilities.getCurrentDateTimeForZone());
            model.setUpdatedOn(Utilities.getCurrentDateTimeForZone());

            cardsRepository.save(model);

            return true;

        } catch (Exception e) {
            log.error("Exception occured @ doProcessCard", e);
            throw new SystemMalfunctionExemption(Constants.EXCEPTION_MESSAGE + "ECA001");
        }


    }

    @Override
    public PaginatedCardResponse getAllCards(String emailAddress, UserRole role,Pageable pageable) {


        //check if user is admin
        if (!role.equals(UserRole.ADMIN)) {
            throw new ForbidenExeption(Constants.FORBIDDEN_MESSAGE);
        }

        var cards= cardsRepository.findAll(pageable);

        return PaginatedCardResponse.builder()
                .cardsList(cards.getContent())
                .numberOfItems(cards.getTotalElements()).numberOfPages(cards.getTotalPages())
                .build();


    }

    @Override
    public PaginatedCardResponse getAllCardsByEmail(String emailAddress, Pageable pageable) {

        try {
            var cards= cardsRepository.getCardByEmailAddress(emailAddress,pageable);

            return PaginatedCardResponse.builder()
                    .cardsList(cards.getContent())
                    .numberOfItems(cards.getTotalElements()).numberOfPages(cards.getTotalPages())
                    .build();

        } catch (Exception e) {
            log.error("Error occurred @ getAllCardsByEmail", e);
            throw new SystemMalfunctionExemption(Constants.EXCEPTION_MESSAGE + "ECA007m");
        }

    }

    @Override
    public CardsModel getCard(Long cardId, String emailAddress, UserRole role) {

        CardsModel data = cardsRepository.getCardById(cardId).orElseThrow(() ->
                new ResourceNotFoundExeption(Constants.RESOURCE_NOT_FOUND_MESSAGE_CARD + cardId));


        if (!data.getEmailAddress().equals(emailAddress) && !role.equals(UserRole.ADMIN)) {
            throw new ForbidenExeption(Constants.FORBIDDEN_MESSAGE);
        }

        return data;


    }

    @Override
    public boolean deleteCard(Long cardId, String emailAddress, UserRole role) {


        CardsModel data = cardsRepository.getCardById(cardId).orElseThrow(() ->
                new ResourceNotFoundExeption(Constants.RESOURCE_NOT_FOUND_MESSAGE_CARD + cardId));


        if (!data.getEmailAddress().equals(emailAddress) && !role.equals(UserRole.ADMIN)) {
            throw new ForbidenExeption(Constants.FORBIDDEN_MESSAGE);
        }

        cardsRepository.delete(data);

        return true;


    }

    @Override
    public UserModel validateUser(String emailAddress) {

            //get the user details
            UserModel data = userRepository.findUserByEmailAddress(emailAddress).orElseThrow(() ->
                    new ForbidenExeption(Constants.FORBIDDEN_MESSAGE));

            //return user data
            return data;
    }

    @Override
    public CardsModel doUpdateCard(UpdateRequest request, Long cardId, String emailAddress, UserRole role) {


            CardsModel model = cardsRepository.getCardById(cardId).orElseThrow(() ->
                    new ResourceNotFoundExeption(Constants.RESOURCE_NOT_FOUND_MESSAGE_CARD + cardId));


            if (!model.getEmailAddress().equals(emailAddress) && !role.equals(UserRole.ADMIN)) {
                throw new ForbidenExeption(Constants.FORBIDDEN_MESSAGE);
            }

            Optional.ofNullable(request.getColor()).ifPresent(model::setColor);
            Optional.ofNullable(request.getDescription()).ifPresent(model::setDescription);
            Optional.ofNullable(request.getName()).ifPresent(model::setName);
            Optional.ofNullable(request.getCardstatus()).ifPresent(model::setStatus);
            model.setUpdatedOn(Utilities.getCurrentDateTimeForZone());

            return cardsRepository.save(model);


    }


}
