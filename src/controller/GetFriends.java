package controller;

import com.google.gson.JsonObject;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetFriends extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person u = (Person) request.getSession().getAttribute("user");
        response.setContentType("application/json");
        try {
            response.getWriter().write(toJson(u.getFriends()));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private String toJson(List<Person> list) {
        JsonObject json = new JsonObject();
        for (Person u : list) {
            JsonObject user = new JsonObject();
            user.addProperty("name", u.getFirstName());
            user.addProperty("statusname", u.getStatus());
            json.add(u.getLastName(), user);
        }
        return json.toString();
    }
}