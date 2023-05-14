package com.sparksupport.sales.service.service;

import com.sparksupport.sales.service.dto.UserDto;
import com.sparksupport.sales.service.entity.User;
import com.sparksupport.sales.service.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> addUser(UserDto userDto){
        try {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            user = userRepository.save(user);
            return ResponseEntity.accepted().body(user);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
