package com.example.cardsapp.cardsapp.repos;

import com.example.cardsapp.cardsapp.model.CardsModel;
import com.example.cardsapp.cardsapp.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardsRepository extends JpaRepository<CardsModel,Long> {

    Optional<CardsModel> getCardById(long cardId);
    Page<CardsModel> getCardByEmailAddress(String email, Pageable pageable);

}

