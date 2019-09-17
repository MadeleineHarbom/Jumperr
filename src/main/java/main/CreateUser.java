package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;

@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        // hvis en bruger ikke er logget på eller er Admin kan CreateUser-siden vises -
        // ellers hvis en almindelig bruger er logget på navigeres man til forsiden
        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/createUser.jsp").forward(request, response);

        } else if (user.getAdmin() == 1) {
            request.getRequestDispatcher("/WEB-INF/jsp/createUser_adminPage.jsp").forward(request, response);

        } else {
            response.sendRedirect("/");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int telephoneNumber = Integer.parseInt(request.getParameter("telephoneNumber"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Controller.createUser(name, email, address, telephoneNumber, username, password);

        response.sendRedirect("/");

    }

}
