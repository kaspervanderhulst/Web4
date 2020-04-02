package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddFriend extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person p = (Person) request.getSession().getAttribute("user");
        if (p != null){
            p.addFriends(getPersonService().getPerson(request.getParameter("name")));
        }
    }
}
