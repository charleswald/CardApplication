package com.example.cardsapp.cardsapp.controller;

import com.example.cardsapp.cardsapp.mappers.CardRequest;
import com.example.cardsapp.cardsapp.mappers.UpdateRequest;
import com.example.cardsapp.cardsapp.model.CardsModel;
import com.example.cardsapp.cardsapp.service.cardsservices.CardService;
import com.example.cardsapp.cardsapp.service.cardsservices.ICardService;
import com.example.cardsapp.cardsapp.utils.Constants.UserRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


//    @ApiOperation(value = "Find user by ID", notes = "Provide an ID to look up specific user from the database", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping
    @ApiOperation(value = "Create a Card ", notes = "Creates a card for a specific user")
    public ResponseEntity<?> createCard(@Valid @RequestBody CardRequest request) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        return new ResponseEntity<>(cardService.doProcessCard(request,emailAddress), HttpStatus.CREATED);

    }

    @GetMapping
    @ApiOperation(value = "Fetch all Cards for a specific user", notes = "Fetch all cards for users who have admin rights")
    public ResponseEntity<?> getAllCards(Pageable pageable) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        //validate user
        var userData = cardService.validateUser(emailAddress);

        //process request and return
        return ResponseEntity.ok(cardService.getAllCards(emailAddress, userData.getRole(),pageable));

    }

    @GetMapping("{cardId}")
    @ApiOperation(value = "Fetch a Card by card Id", notes = "Fetch a specific card using the card id. User should have the rights to pull a specific card ")
    public ResponseEntity<CardsModel> getCard(@PathVariable Long cardId) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        var userData = cardService.validateUser(emailAddress);

        var resp = cardService.getCard(cardId, emailAddress, userData.getRole());

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/member")
    @ApiOperation(value = "Fetch all Cards associated with a member", notes = "Fetch all cards associated with a specific member ")
    public ResponseEntity<?> getCardsByEmail(Pageable pageable) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        cardService.validateUser(emailAddress);

        var resp = cardService.getAllCardsByEmail(emailAddress,pageable);

        return ResponseEntity.ok(resp);
    }



    @DeleteMapping("{cardId}")
    @ApiOperation(value = "Delete a Card using the card id", notes = "Delete a card associated with a specific card id. User must have rights to perform this action ")
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
    @ApiOperation(value = "Edit a Card associated with a specific card id", notes = "Modify a card associated with a specific card id. User must have rights to perform this action ")
    public ResponseEntity<CardsModel> updateCard(@PathVariable Long cardId, @RequestBody UpdateRequest request) {

        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();

        String emailAddress = authToken.getPrincipal().toString();

        var userData = cardService.validateUser(emailAddress);

        var resp = cardService.doUpdateCard(request, cardId, emailAddress, userData.getRole());

        return ResponseEntity.ok(resp);
    }
}
