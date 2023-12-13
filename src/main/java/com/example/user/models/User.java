package com.example.user.models;

import com.example.user.dto.UserAddress;
import com.example.user.dto.UserName;
import com.example.user.enums.Tenant;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private Integer age;
    @Column(name = "address")
    private String address;
    @Column(name = "tenant")
    @Enumerated(EnumType.STRING)
    private Tenant tenant;
    @Column(name = "created_on")
    private OffsetDateTime createdOn;
    @Column(name = "updated_on")
    private OffsetDateTime updatedOn;
    @Column(name = "is_active")
    private Boolean isActive;
}
