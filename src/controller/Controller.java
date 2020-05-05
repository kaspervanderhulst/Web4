package controller;

import domain.MessageService;
import domain.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PersonService model = new PersonService();
    private ControllerFactory controllerFactory = new ControllerFactory();
    private MessageService messageService = new MessageService();


    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String destination;
        if (action != null) {

            try {
                controllerFactory.getController(action, model, messageService).handleRequest(request, response);
            } catch (NotAuthorizedException exc) {
                List<String> errors = new ArrayList<String>();
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                destination = "index.jsp";
                RequestDispatcher view = request.getRequestDispatcher(destination);
            }
        }else {
            controllerFactory.getController(action,model, messageService).handleRequest(request,response);
            destination = "index.jsp";
            RequestDispatcher view = request.getRequestDispatcher(destination);
            view.forward(request,response);
        }
    }

}
