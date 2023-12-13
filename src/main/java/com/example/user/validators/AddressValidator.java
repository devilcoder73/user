package com.example.user.validators;

import com.example.user.dto.UserAddress;

public class AddressValidator {
    public boolean validateAddress(UserAddress address) {
        if(address.getAddressLineOne().length() < 0 || address.getAddressLineOne().length() > 20) {
            throw new IllegalArgumentException("Address Line One is not valid.");
        }
        if(address.getAddressLineTwo().length() > 20) {
            throw new IllegalArgumentException("Address Line One is not valid.");
        }
        if(address.getAddressLineOne().length() < 0 || address.getAddressLineOne().length() > 20) {
            throw new IllegalArgumentException("Address Line One is not valid.");
        }
        if(address.getAddressLineOne().length() < 0 || address.getAddressLineOne().length() > 20) {
            throw new IllegalArgumentException("Address Line One is not valid.");
        }
        if(address.getAddressLineOne().length() < 0 || address.getAddressLineOne().length() > 20) {
            throw new IllegalArgumentException("Address Line One is not valid.");
        }
        return true;
    }
}
