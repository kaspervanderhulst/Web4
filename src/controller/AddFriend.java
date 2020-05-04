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
            //Adding the person to your own friendslist
            p.addFriends(getPersonService().getPerson(request.getParameter("name")));

            //Adding yourself to this persons friendslist
            getPersonService().getPerson(request.getParameter("name")).addFriends(p);
        }
    }
}
