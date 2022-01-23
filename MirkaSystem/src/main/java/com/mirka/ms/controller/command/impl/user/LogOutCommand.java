package com.mirka.ms.controller.command.impl.user;

import com.mirka.ms.controller.SessionRequestContext;
import com.mirka.ms.controller.command.Command;

public class LogOutCommand implements Command {
    @Override
    public String execute(SessionRequestContext sessionRequestContext) {
        sessionRequestContext.setLogout(true);
        return "/jsp/guest/login.jsp";
    }
}