package com.mirka.ms.controller.command.impl.guest;

import com.mirka.ms.controller.SessionRequestContext;
import com.mirka.ms.controller.command.Command;
import com.mirka.ms.model.entity.User;
import com.mirka.ms.model.service.UserService;

import java.util.Optional;

public class RegisterCommand implements Command {

    private static final String EMPTY_SIGN_UP_PARAMETERS = "Empty sign up parameters";
    private static final String CORRECT_SIGN_UP = "Congrats, you are registered!!!!";
    private static final String ERROR_REGISTERING = "Errors while register :(";

    @Override
    public String execute(SessionRequestContext sessionRequestContext) {
        UserService userService = UserService.getInstance();
        String pageResult = "/jsp/guest/register.jsp";
        Optional<String> login = sessionRequestContext.getRequestParameter("login");
        Optional<String> password = sessionRequestContext.getRequestParameter("password");
        Optional<String> repeatPassword = sessionRequestContext.getRequestParameter("repeatPassword");
        if (!login.isPresent() || !password.isPresent() || !repeatPassword.isPresent()) {
            sessionRequestContext.setSessionAttribute("error_message", EMPTY_SIGN_UP_PARAMETERS);
        } else {
            Optional<User> optionalUser;
            optionalUser = userService.registerUser(login.get(), password.get(), repeatPassword.get());
            if (optionalUser.isPresent()) {
                sessionRequestContext.setSessionAttribute("correct_message", CORRECT_SIGN_UP);
                pageResult = "/jsp/guest/login.jsp";
            } else {
                sessionRequestContext.setSessionAttribute("error_message", ERROR_REGISTERING);
            }
        }
        return pageResult;
    }
}