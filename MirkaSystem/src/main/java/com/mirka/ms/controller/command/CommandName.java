package com.mirka.ms.controller.command;

import com.mirka.ms.controller.command.impl.guest.RegisterCommand;
import com.mirka.ms.controller.command.impl.user.LogInCommand;
import com.mirka.ms.controller.command.impl.user.LogOutCommand;

public enum CommandName {

    LOG_IN(new LogInCommand()),

    LOG_OUT(new LogOutCommand()),

    REGISTER(new RegisterCommand());

    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}