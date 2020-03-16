package com.ccz.service;

import com.ccz.po.User;

public interface UserService {
    User checkUserLogin(String username, String password);

    User getUser(Long id);
}
