package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CountMessages extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Person sender = (Person) request.getSession().getAttribute("user");
        String recipientId =  request.getParameter("recipient");
        Person recipient = getPersonService().getPerson(recipientId);

        String text = getMessageCountAsString(sender, recipient);

        response.setContentType("application/text");
        response.getWriter().write(text);
    }

    //almost same method as in getMessages but here we take the size of the list
    //to get the ammount of messages sent
    public String getMessageCountAsString(Person sender, Person recipient) {
        List<String> x = getMessageService().getMessagesForUserString(sender.getUserId(), recipient.getUserId());
        return String.valueOf(x.size());
    }
}
