package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetStatus extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person person = getPersonService().getPerson(((Person) request.getSession().getAttribute("user")).getUserId());

        person.setStatus(request.getParameter("status"));
        getPersonService().updatePersons(person);
        request.getSession().setAttribute("user",person);
        response.setContentType("text/json");
        response.getWriter().write(this.toJSON(request.getParameter("status")));
    }

    private String toJSON(String status){
        StringBuffer json = new StringBuffer();
        json.append("{\"status\" : \"");
        json.append(status);
        json.append("\"}");
        return json.toString();
    }
}
