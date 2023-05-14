package com.sparksupport.sales.service.controller;

import com.sparksupport.sales.service.dto.UserDto;
import com.sparksupport.sales.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }
}
