package com.mirka.ms.controller.command.impl.user;

import com.mirka.ms.controller.SessionRequestContext;
import com.mirka.ms.controller.command.Command;
import com.mirka.ms.model.entity.User;
import com.mirka.ms.model.service.UserService;

import java.util.Optional;

public class LogInCommand implements Command {

    private static final String EMPTY_LOGIN_PARAMETERS = "Empty login parameters";
    private static final String ERROR_ENTERING = "Errors while enter :(";

    @Override
    public String execute(SessionRequestContext sessionRequestContext) {
        UserService userService = UserService.getInstance();
        String pageResult = "/jsp/guest/login.jsp";
        Optional<String> login = sessionRequestContext.getRequestParameter("login");
        Optional<String> password = sessionRequestContext.getRequestParameter("password");
        Optional<User> checkUser;
        if (!login.isPresent() || !password.isPresent()) {
            sessionRequestContext.setSessionAttribute("error_message", EMPTY_LOGIN_PARAMETERS);
        } else {
            checkUser = userService.loginUser(login.get(), password.get());
            if (checkUser.isPresent()) {
                sessionRequestContext.setSessionAttribute("user", checkUser.get());
                switch (checkUser.get().getUserType()) {
                    case ADMIN:
                        pageResult = "/jsp/admin/homeAdmin.jsp";
                        break;
                    case USER:
                        pageResult = "/jsp/user/homeUser.jsp";
                        break;
                }
            } else {
                sessionRequestContext.setSessionAttribute("error_message", ERROR_ENTERING);
            }
        }
        return pageResult;
    }
}