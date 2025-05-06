package com.example.VehicleManagement.auth;

import com.example.VehicleManagement.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String NationalId;
    private Role role;
    private String password;
}
