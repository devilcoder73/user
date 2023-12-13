package com.example.user.dto;

import com.example.user.enums.Tenant;
import lombok.Data;

@Data
public class UserRequest {
    private UserName name;
    private String email;
    private String phone;
    private Integer age;
    private UserAddress address;
    private Tenant tenant;
}
