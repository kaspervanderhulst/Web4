package controller;

import com.google.gson.JsonObject;
import domain.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String comment = request.getParameter("comment");
        int rating = Integer.parseInt(request.getParameter("rating"));

        Comment comment1 = new Comment(name,comment,rating);
        response.setContentType("text/json");
        response.getWriter().write(String.valueOf(this.toJson(comment1)));
    }

    private Object toJson(Comment comment){
        JsonObject jsonObject = new JsonObject();
        JsonObject commentobj = new JsonObject();
        commentobj.addProperty("name", comment.getName());
        commentobj.addProperty("comment",comment.getComment());
        commentobj.addProperty("rating",comment.getRating());

        jsonObject.add(comment.getName(),commentobj);
        return jsonObject.toString();
    }
}
