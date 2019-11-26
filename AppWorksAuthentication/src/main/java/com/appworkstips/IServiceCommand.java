package com.appworkstips;

import com.appworkstips.exceptions.ServiceException;

import javax.xml.soap.SOAPMessage;

public interface IServiceCommand {
    void execute() throws ServiceException;

    SOAPMessage buildSoapMessage();
}
