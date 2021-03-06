package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetMessages extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Person sender = (Person) request.getSession().getAttribute("user");
        String recipientid =  request.getParameter("recipient");
        Person recipient = getPersonService().getPerson(recipientid);

        String json = "";
        json = getMessagesAsString(sender, recipient);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    //get a Stringvalue of all the messages sent and received for the sender and receiver
    public String getMessagesAsString(Person sender, Person recipient) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> x = getMessageService().getMessagesForUserString(sender.getUserId(), recipient.getUserId());
        return mapper.writeValueAsString(x);
    }

}
