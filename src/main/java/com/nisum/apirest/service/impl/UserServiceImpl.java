package com.nisum.apirest.service.impl;

import com.nisum.apirest.constants.AppConstants;
import com.nisum.apirest.model.User;
import com.nisum.apirest.repository.UserRepository;
import com.nisum.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Value("${nisum.password-validator}")
    private String passwordRegEx;

    @Override
    public User create(User user) {

        if(!Pattern.compile(passwordRegEx).matcher(user.getPassword()).matches()) {
            throw new IllegalArgumentException(AppConstants.INVALID_PASSWORD_MSG);
        }

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setIsActive(Boolean.TRUE);
        return repository.save(user);
    }
}
