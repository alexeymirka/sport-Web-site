package com.mirka.ms.controller.command;

import com.mirka.ms.controller.SessionRequestContext;

@FunctionalInterface
public interface Command {
    String execute(SessionRequestContext sessionRequestContext);
}