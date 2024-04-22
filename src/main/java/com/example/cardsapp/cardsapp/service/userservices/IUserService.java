package com.example.cardsapp.cardsapp.service.userservices;

import com.example.cardsapp.cardsapp.dtos.UserDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{

    public UserDTO getUser(String email);

    public UserDTO getUserByUserId(String userId);
}
