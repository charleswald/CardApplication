package com.example.cardsapp.cardsapp.model;

import com.example.cardsapp.cardsapp.utils.Constants.UserRole;
import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
