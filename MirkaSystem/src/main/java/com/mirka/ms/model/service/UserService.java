package com.mirka.ms.model.service;

import com.mirka.ms.model.dao.UserDao;
import com.mirka.ms.model.entity.User;
import com.mirka.ms.model.entity.UserType;
import com.mirka.ms.model.util.Encryptor;

import java.util.Optional;

public class UserService {

    private static final UserService instance = new UserService();
    private final UserDao userDao = new UserDao();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }

    public Optional<User> loginUser(String login, String password) {
        Optional<User> foundUser = Optional.empty();
        Optional<String> userDbPassword;
        if (userDao.existsLogin(login)) {
            userDbPassword = userDao.findUserPasswordByLogin(login);
            if (userDbPassword.isPresent() && Encryptor.checkInputPassword(password, userDbPassword.get())) {
                foundUser = userDao.findUserByLogin(login);
            }
        }
        return foundUser;
    }

    public Optional<User> registerUser(String login, String password, String repeatPassword) {
        Optional<User> foundUser = Optional.empty();
        if (!userDao.existsLogin(login) && repeatPassword.equals(password)) {
            String encryptedPassword = Encryptor.encryptPassword(password);
            foundUser = Optional.of(new User(0, login, UserType.USER));
            userDao.addUser(foundUser.get(), encryptedPassword);
        }
        return foundUser;
    }
}