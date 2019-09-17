package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import storage.Storage;

@WebServlet("/Delete")
public class Delete extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        User user1 = (User) request.getSession().getAttribute("user");

        if (user1 != null && user1.getAdmin() == 1) {

            if (userId != null) {

                User user2 = Controller.getUserById(userId);
                Storage.removeUser(user2);
            }
        }
        response.sendRedirect("/");
    }
}
