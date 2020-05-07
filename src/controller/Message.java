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
       Person sender = (Person) request.getSession().getAttribute("user");

        String message = request.getParameter("message");
        String recipientId = request.getParameter("recipient");


        String json = "";

        Person recipient = getPersonService().getPerson(recipientId);
        System.out.println("Message: " + message);
        System.out.println("Recipient: " + recipientId);
        System.out.println("Sender: " + sender.getUserId());
        getMessageService().addMessageService(sender, recipient, message);

        response.setContentType("application/json");
        json = getMessagesAsString(sender, recipient);
        System.out.println(json + " in Message.java");
        response.getWriter().write(json);
    }

    public String getMessagesAsString(Person user, Person recipient) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(getMessageService().getMessagesForUserString(user.getUserId(), recipient.getUserId()));
    }
}
