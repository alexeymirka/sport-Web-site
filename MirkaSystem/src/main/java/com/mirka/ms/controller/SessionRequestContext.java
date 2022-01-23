package com.mirka.ms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SessionRequestContext {

    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private boolean isLogout;

    public SessionRequestContext(HttpServletRequest request) {
        requestAttributes = extractRequestAttributes(request);
        requestParameters = extractRequestParameters(request);
        sessionAttributes = extractSessionAttributes(request);
    }

    public void insertAttributes(HttpServletRequest request) {
        requestAttributes.forEach(request::setAttribute);
        HttpSession session = request.getSession();
        sessionAttributes.forEach(session::setAttribute);
        if (isLogout) {
            session.invalidate();
        }
    }

    public Optional<String> getRequestParameter(String key) {
        if (requestParameters.isEmpty() || !requestParameters.containsKey(key)) {
            return Optional.empty();
        }
        return Optional.of(requestParameters.get(key)[0]);
    }

    public void setSessionAttribute(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    public void setLogout(boolean logout) {
        isLogout = logout;
    }

    private Map<String, Object> extractRequestAttributes(HttpServletRequest request) {
        Map<String, Object> attributes = new HashMap<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            attributes.put(name, request.getAttribute(name));
        }
        return attributes;
    }

    private Map<String, String[]> extractRequestParameters(HttpServletRequest request) {
        return request.getParameterMap();
    }

    private Map<String, Object> extractSessionAttributes(HttpServletRequest request) {
        Map<String, Object> attributes = new HashMap<>();
        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            attributes.put(name, session.getAttribute(name));
        }
        return attributes;
    }
}