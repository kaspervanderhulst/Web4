package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Message extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       Person person = (Person) request.getSession().getAttribute("user");

        String message = request.getParameter("message");
        String recipientId = request.getParameter("recipient");


        String json = "";

        Person recipient = getPersonService().getPerson(recipientId);
        System.out.println("Message: " + message);
        System.out.println("Recipient: " + recipientId);
        System.out.println("User: " + person.getUserId());
        getMessageService().addMessageService(person, recipient, message);

//        JsonObject object = getMessageService().getMessages(person);
        response.setContentType("application/json");
        json = test(person, recipient);
        System.out.println(json + "in the messages");
        response.getWriter().write(json);
    }

    public String test(Person user, Person recipient) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(getMessageService().getMessagesForUserString(user.getUserId(), recipient.getUserId()));
    }
}
