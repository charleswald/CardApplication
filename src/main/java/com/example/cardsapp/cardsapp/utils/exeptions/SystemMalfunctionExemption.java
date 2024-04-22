package com.example.cardsapp.cardsapp.utils.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemMalfunctionExemption extends RuntimeException{

    public SystemMalfunctionExemption(String message){
        super(message);
    }
}
