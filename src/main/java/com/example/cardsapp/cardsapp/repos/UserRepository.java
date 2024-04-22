package com.example.cardsapp.cardsapp.repos;

import com.example.cardsapp.cardsapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findUserByEmailAddress(String email);

    Optional<UserModel> findByUserId(String userId);
}
