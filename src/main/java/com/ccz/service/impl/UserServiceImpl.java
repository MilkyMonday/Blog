package com.ccz.service.impl;

import com.ccz.dao.UserRepository;
import com.ccz.po.User;
import com.ccz.service.UserService;
import com.ccz.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUserLogin(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }
}
