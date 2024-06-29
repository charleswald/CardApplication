package com.example.cardsapp.cardsapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Details about the User")
public class UserRequestModel {

    @ApiModelProperty(notes = "The email of the user")
    String emailAddress;

    @ApiModelProperty(notes = "The password of the user")
    String password;
}
