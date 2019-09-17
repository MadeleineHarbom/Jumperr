package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;

@WebServlet("/DriverServlet")
public class DriverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            // initialiserer nogle trips-objekter
            // init_trips();
            response.sendRedirect("/");

        } else {

            // hvis det er Admin
            if (user.getAdmin() == 1) {

                request.getRequestDispatcher("/WEB-INF/jsp/driver_adminPage.jsp").forward(request, response);

            } else {

                request.getRequestDispatcher("/WEB-INF/jsp/driver.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // driver-user
        User user = (User) request.getSession().getAttribute("user");

        String date = request.getParameter("date");
        String timeOfDeparture = request.getParameter("timeOfDeparture");
        String timeOfArrival = request.getParameter("timeOfArrival");
        String departureAddress = request.getParameter("departureAddress");
        String arrivalAddress = request.getParameter("arrivalAddress");

        Controller.createTrip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user);

        response.sendRedirect("/ProfileSettings");

    }

}
