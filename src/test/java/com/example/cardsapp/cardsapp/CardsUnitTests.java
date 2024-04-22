package com.example.cardsapp.cardsapp;

import com.example.cardsapp.cardsapp.mappers.CardRequest;
import com.example.cardsapp.cardsapp.model.CardsModel;
import com.example.cardsapp.cardsapp.model.UserModel;
import com.example.cardsapp.cardsapp.repos.CardsRepository;
import com.example.cardsapp.cardsapp.repos.UserRepository;
import com.example.cardsapp.cardsapp.service.cardsservices.CardService;
import com.example.cardsapp.cardsapp.service.cardsservices.ICardService;
import com.example.cardsapp.cardsapp.utils.Constants.CardStatus;
import com.example.cardsapp.cardsapp.utils.Constants.UserRole;
import com.example.cardsapp.cardsapp.utils.exeptions.ResourceNotFoundExeption;
import com.example.cardsapp.cardsapp.utils.utilities.Utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith({MockitoExtension.class})
public class CardsUnitTests {

    @Mock
    UserRepository userRepository;

    @Mock
    CardsRepository cardsRepository;

    @Mock
    private ICardService cardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userRepository = mock(UserRepository.class);
        cardsRepository = mock(CardsRepository.class);
        cardService = new CardService(userRepository, cardsRepository);
    }

    @Test
    public void testValidateUser() {

        UserModel user = new UserModel();

        user.setId(1);
        user.setActive(true);
        user.setRole(UserRole.ADMIN);
        user.setEncryptedPassword("test");
        user.setPassword("test123");
        user.setEmailAddress("test@gmail.com");
        user.setFirstName("maina");
        user.setLastName("test");

        String emailAddress = "test@gmail.com";

        Mockito.when(userRepository.findUserByEmailAddress(emailAddress)).thenReturn(Optional.of(user));

        assertEquals(cardService.validateUser(emailAddress), (user));

    }


    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    public void testDoProcessCard() {

        CardsModel card = new CardsModel();
        card.setStatus(CardStatus.TODO);
        card.setName("TestCard");
        card.setId(1);
        card.setDescription("test");
        card.setColor("#1234565");
        card.setEmailAddress("test@gmail.com");
        card.setCreatedOn(Utilities.getCurrentDateTimeForZone());
        card.setUpdatedOn(Utilities.getCurrentDateTimeForZone());

        CardRequest cr = new CardRequest();
        cr.setCardstatus(CardStatus.TODO);
        cr.setColor("#1234565");
        cr.setDescription("test card");
        cr.setName("test");

        String emailAddress = "test@gmail.com";

        Mockito.when(cardsRepository.save(card)).thenReturn(card);

        assertEquals(cardService.doProcessCard(cr, emailAddress), true);

    }

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    public void testGetCardResourceNotFound() {

        CardsModel card = new CardsModel();
        card.setStatus(CardStatus.TODO);
        card.setName("TestCard");
        card.setId(1);
        card.setDescription("test");
        card.setColor("#1234565");
        card.setEmailAddress("test@gmail.com");
        card.setCreatedOn(Utilities.getCurrentDateTimeForZone());
        card.setUpdatedOn(Utilities.getCurrentDateTimeForZone());

        CardRequest cr = new CardRequest();
        cr.setCardstatus(CardStatus.TODO);
        cr.setColor("#1234565");
        cr.setDescription("test card");
        cr.setName("test");

        String emailAddress = "test@gmail.com";

        Mockito.when(cardsRepository.getCardById(2)).thenReturn(Optional.of(card));

        Exception exception = assertThrows(ResourceNotFoundExeption.class, () -> {
            cardService.getCard(1L, emailAddress,UserRole.ADMIN);
        });

        assertEquals("Card Details Not found. Card No. 1", exception.getMessage());

    }

    @Test
    public void testGetCard() {

        CardsModel card = new CardsModel();
        card.setStatus(CardStatus.TODO);
        card.setName("TestCard");
        card.setId(1);
        card.setDescription("test");
        card.setColor("#1234565");
        card.setEmailAddress("test@gmail.com");
        card.setCreatedOn(Utilities.getCurrentDateTimeForZone());
        card.setUpdatedOn(Utilities.getCurrentDateTimeForZone());

        CardRequest cr = new CardRequest();
        cr.setCardstatus(CardStatus.TODO);
        cr.setColor("#1234565");
        cr.setDescription("test card");
        cr.setName("test");

        String emailAddress = "test@gmail.com";

        Mockito.when(cardsRepository.getCardById(1)).thenReturn(Optional.of(card));

        assertEquals(cardService.getCard(1L, emailAddress,UserRole.ADMIN), card);

    }
}
