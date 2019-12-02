package com.appworkstips;

import javax.xml.soap.SOAPMessage;

public interface IServiceCommand {
    void execute(SOAPMessage soapMessage);
}
