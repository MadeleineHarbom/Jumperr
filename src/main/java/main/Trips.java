package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import storage.LocalStorage;

@WebServlet("/Trips")
public class Trips extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getAdmin() == 1) {

            request.setAttribute("trips", LocalStorage.getTrips());
            request.setAttribute("username", user.getUsername());

            request.getRequestDispatcher("/WEB-INF/jsp/admin/trips_adminPage.jsp").forward(request, response);

        } else {
            response.sendRedirect("/");
        }
    }

}