package ru.itmo.soalab2.validators;

import ru.itmo.soalab2.model.Error;
import ru.itmo.soalab2.model.ErrorListWrap;

import javax.xml.ws.WebFault;
import java.util.ArrayList;

@WebFault(name = "validateFieldsException", targetNamespace = "http://controller.soalab2.itmo.ru/")
public class ValidateFieldsException extends Exception {

    private ErrorListWrap errorListWrap;

    public ValidateFieldsException(String message, ArrayList<Error> errors) {
        super(message);
        this.errorListWrap = new ErrorListWrap(errors);
    }

    public ErrorListWrap getErrorListWrap() {
        return errorListWrap;
    }

    public void setErrorListWrap(ErrorListWrap errorListWrap) {
        this.errorListWrap = errorListWrap;
    }

}
