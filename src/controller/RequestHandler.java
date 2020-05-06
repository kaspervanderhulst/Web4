package controller;

import domain.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    private PersonService personService;
    private MessageService messageService;

    public abstract void handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void setModel (PersonService personService, MessageService messageService) {
        this.personService = personService;
        this.messageService = messageService;
    }

    public PersonService getPersonService() {
        return personService;
    }
    public MessageService getMessageService(){
        return messageService;
    }

    protected boolean isFromUserWithRole (HttpServletRequest request, Role role) {
        Person person = (Person) request.getSession().getAttribute("user");
        if (person != null && person.getRole().equals(role)) {
            return true;
        }
        return false;
    }

}
