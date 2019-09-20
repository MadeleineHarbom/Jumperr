package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.*;
import storage.Storage;

@WebServlet("/")
public class Frontpage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // users initialiseres
    public void init_users() {
        Storage.addUser(new Admin("Lars", "Lars@hotmail.com", "Banegårdsgade 4", 10203040, "Lars_P", "123456"));
        Storage.addUser(new User("Rabeea", "Rabeea@hotmail.com", "Vibyvej 69", 10203040, "Rabeea_M", "privat"));
        Storage.addUser(new User("Hans", "Hans@hotmail.com", "Haslevej 12", 10203040, "Hans_L", "123"));
        Storage.addUser(new User("Jens", "Jens@hotmail.com", "Vestre ringgade 40", 10203040, "Jens_P", "pass"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            // initialiserer nogle user-objekter
            init_users();

            request.getRequestDispatcher("/WEB-INF/jsp/notLoggedIn/login.jsp").forward(request, response);

        } else {

            // hvis det er Admin
            if (user.getAdmin() == 1) {

                request.setAttribute("users", Storage.getUsers());
                request.setAttribute("username", user.getUsername());

                request.getRequestDispatcher("/WEB-INF/jsp/admin/users_adminPage.jsp").forward(request, response);

            } else {

                request.setAttribute("username", user.getUsername());
                response.sendRedirect("/Jumper");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String error = null;

        User user = Controller.authenticateUser(username, password);

        if (user != null) {

            request.getSession().setAttribute("user", user);
            response.sendRedirect("/");

        } else {
            error = "The username & password combination are incorrect...";
            request.setAttribute("error", error);

            request.getRequestDispatcher("/WEB-INF/jsp/notLoggedIn/login.jsp").forward(request, response);
        }

    }
}