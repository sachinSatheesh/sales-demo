package com.sparksupport.sales.service.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String type;
    private boolean active;
    private String password;
}
