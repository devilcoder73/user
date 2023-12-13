package com.example.user.dto;

import lombok.Data;

@Data
public class UserAddress {
    private String addressLineOne;
    private String addressLineTwo;
    private String pinCode;
    private String city;
    private String state;
    private String country;
}
