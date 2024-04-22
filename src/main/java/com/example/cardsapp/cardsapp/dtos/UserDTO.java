package com.example.cardsapp.cardsapp.dtos;

import com.example.cardsapp.cardsapp.utils.Constants.UserRole;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String emailAddress;
    private UserRole role;
    private boolean active;
    private String userToken;
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String encryptedPassword;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
