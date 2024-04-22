package com.example.cardsapp.cardsapp.service.userservices;

import com.example.cardsapp.cardsapp.dtos.UserDTO;
import com.example.cardsapp.cardsapp.model.UserModel;
import com.example.cardsapp.cardsapp.repos.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO getUser(String email) {
        UserModel userEntity = userRepository.findUserByEmailAddress(email).orElseThrow(()->
                new UsernameNotFoundException(email));

        UserDTO returnUserDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnUserDTO);
        return returnUserDTO;
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        UserDTO returnUserDTO = new UserDTO();
        UserModel userEntityByUserId = userRepository.findByUserId(userId).orElseThrow(()->
                new UsernameNotFoundException(userId));;

        BeanUtils.copyProperties(userEntityByUserId, returnUserDTO);
        return returnUserDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userEntity = userRepository.findUserByEmailAddress(email).orElseThrow(()->
                new UsernameNotFoundException(email));

        return new User(userEntity.getEmailAddress(), userEntity.getEncryptedPassword(),
                new ArrayList<>());
    }
}
