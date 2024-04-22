package com.example.cardsapp.cardsapp.controller;

import com.example.cardsapp.cardsapp.mappers.CardRequest;
import com.example.cardsapp.cardsapp.mappers.UpdateRequest;
import com.example.cardsapp.cardsapp.model.CardsModel;
import com.example.cardsapp.cardsapp.service.cardsservices.CardService;
import com.example.cardsapp.cardsapp.service.cardsservices.ICardService;
import com.example.cardsapp.cardsapp.utils.Constants.UserRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Api(tags = "CardsController", description = "Card Management Controller")
@RequestMapping("/api/v1/cards")
public class CardsController {

    @Autowired
    ICardService cardService;

    @PostMapping
    @ApiOperation("Create a Card End Point")
    public ResponseEntity<?> createCard(@Valid @RequestBody CardRequest request) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        return new ResponseEntity<>(cardService.doProcessCard(request,emailAddress), HttpStatus.CREATED);

    }

    @GetMapping
    @ApiOperation("Fetch all Cards End Point")
    public ResponseEntity<?> getAllCards(Pageable pageable) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        //validate user
        var userData = cardService.validateUser(emailAddress);

        //process request and return
        return ResponseEntity.ok(cardService.getAllCards(emailAddress, userData.getRole(),pageable));

    }

    @GetMapping("{cardId}")
    @ApiOperation("Fetch a Card by card Id End Point")
    public ResponseEntity<CardsModel> getCard(@PathVariable Long cardId) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        var userData = cardService.validateUser(emailAddress);

        var resp = cardService.getCard(cardId, emailAddress, userData.getRole());

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/member")
    @ApiOperation("Fetch all Cards for a member")
    public ResponseEntity<?> getCardsByEmail(Pageable pageable) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        cardService.validateUser(emailAddress);

        var resp = cardService.getAllCardsByEmail(emailAddress,pageable);

        return ResponseEntity.ok(resp);
    }



    @DeleteMapping("{cardId}")
    @ApiOperation("Delete a Card by card id")
    public ResponseEntity<?> deleteCard(@PathVariable Long cardId) {

        //get the username/email
        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        //get user details including role
        var userData = cardService.validateUser(emailAddress);

        var resp = cardService.deleteCard(cardId, emailAddress, userData.getRole());

        return ResponseEntity.ok(resp);

    }

    @PutMapping("{cardId}")
    @ApiOperation("Edit a Card by card id")
    public ResponseEntity<CardsModel> updateCard(@PathVariable Long cardId, @RequestBody UpdateRequest request) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        var userData = cardService.validateUser(emailAddress);

        var resp = cardService.doUpdateCard(request, cardId, emailAddress, userData.getRole());

        return ResponseEntity.ok(resp);
    }
}
