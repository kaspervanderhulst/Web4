package controller;

import com.google.gson.JsonObject;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class GetFriends extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person u = (Person) request.getSession().getAttribute("user");
        response.setContentType("application/json");
        try {
            response.getWriter().write(toJson(u.getFriends()));
        } catch (IOException e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
    }

    private String toJson(Set<Person> list){
        JsonObject json = new JsonObject();
        System.out.println(list.size());
        for (Person u: list){
            JsonObject user = new JsonObject();
            user.addProperty("name",u.getLastName());
            user.addProperty("statusname",u.getStatus());
            json.add(u.getLastName(),user);
        }
        return json.toString();
    }
}