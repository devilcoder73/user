package com.example.user.service;

import com.example.user.dto.UserRequest;
import com.example.user.enums.Tenant;
import com.example.user.models.User;
import com.example.user.repository.UserRepository;
import com.example.user.validators.EmailValidator;
import com.example.user.validators.PhoneValidator;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String PHONE_REGEX = "[1-9][0-9]{9}";
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsersByTenant(String tenant) {
        Tenant val = null;
        for (Tenant t : Tenant.values()) {
            if(t.toString().equals(tenant)) {
                val = t;
            }
        }
        if(Objects.isNull(val)) {
            throw new IllegalArgumentException("Please provide a valid tenant.");
        }
        return this.userRepository.findByTenant(val);
    }

    public Optional<User> getUserByUserId(String userId) {
        Optional<User> user = this.userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User does not exist with the user_id : "+userId);
        }
        return user;
    }

    public Boolean addUser(UserRequest userRequest) {
        this.validateRequest(userRequest);

        String userId = UUID.randomUUID().toString();

        String address = userRequest.getAddress().getAddressLineOne()
            + ", " + userRequest.getAddress().getAddressLineTwo()
            + ", " + userRequest.getAddress().getPinCode()
            + ", " + userRequest.getAddress().getCity()
            + ", " + userRequest.getAddress().getState()
            + ", " + userRequest.getAddress().getCountry();

        String name;
        if (StringUtils.isNotEmpty(userRequest.getName().getMiddleName())) {
            name = userRequest.getName().getFirstName()
                + " " + userRequest.getName().getMiddleName()
                + " " + userRequest.getName().getLastName();
        } else {
            name = userRequest.getName().getFirstName()
                + " " + userRequest.getName().getLastName();
        }

        User user = User.builder()
            .userId(userId)
            .age(userRequest.getAge())
            .address(address)
            .phone(userRequest.getPhone())
            .email(userRequest.getEmail())
            .tenant(userRequest.getTenant())
            .name(name)
            .isActive(true)
            .build();

        this.userRepository.save(user);
        return true;
    }

    private void validateRequest(UserRequest userRequest) {
        if(userRequest.getName().getFirstName().length() > 10) {
            throw new IllegalArgumentException("User First Name exceeds the approved limit.");
        }
        if(userRequest.getName().getLastName().length() > 10) {
            throw new IllegalArgumentException("User Last Name exceeds the approved limit.");
        }
        if(StringUtils.isNotEmpty(userRequest.getName().getMiddleName()) && userRequest.getName().getMiddleName().length() > 10) {
            throw new IllegalArgumentException("User Middle Name exceeds the approved limit.");
        }
        if(!EmailValidator.emailMatch(userRequest.getEmail(), EMAIL_REGEX)) {
            throw new IllegalArgumentException("User Email is not valid.");
        }
        if(!PhoneValidator.phoneMatch(userRequest.getPhone(), PHONE_REGEX)) {
            throw new IllegalArgumentException("User Phone is not valid.");
        }
        if(userRequest.getAge() <= 0 || userRequest.getAge() > 110) {
            throw new IllegalArgumentException("Please provide age between 1 and 110");
        }
    }
}
