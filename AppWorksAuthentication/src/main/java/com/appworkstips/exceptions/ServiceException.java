package com.appworkstips.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceException extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(ServiceException.class.getSimpleName());

    public ServiceException(String message) {
        LOGGER.log(Level.SEVERE, message);
    }
}
