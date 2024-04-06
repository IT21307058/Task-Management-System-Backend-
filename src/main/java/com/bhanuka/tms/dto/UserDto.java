package com.bhanuka.tms.dto;

import com.bhanuka.tms.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}