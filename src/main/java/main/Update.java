package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Trip;
import model.User;
import storage.LocalStorage;

@WebServlet("/Update")
public class Update extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String tripId = request.getParameter("tripId");
        User user1 = (User) request.getSession().getAttribute("user");

        if (user1 != null && user1.getAdmin() == 1) {

            if (userId != null) {

                User user2 = Controller.getUserById(userId);

                request.setAttribute("userId", userId);
                request.setAttribute("name", user2.getName());
                request.setAttribute("email", user2.getEmail());
                request.setAttribute("address", user2.getAddress());
                request.setAttribute("telephoneNumber", Integer.toString(user2.getTelephoneNumber()));
                request.setAttribute("username", user2.getUsername());
                request.setAttribute("password", user2.getPassword());

                request.getRequestDispatcher("/WEB-INF/jsp/admin/updateUser_adminPage.jsp").forward(request, response);

            } else if (tripId != null) {

                Trip trip = Controller.getTripById(tripId);

                request.setAttribute("tripId", tripId);
                request.setAttribute("date", trip.getDate());
                request.setAttribute("timeOfDeparture", trip.getTimeOfDeparture());
                request.setAttribute("timeOfArrival", trip.getTimeOfArrival());
                request.setAttribute("departureAddress", trip.getDepartureAddress());
                request.setAttribute("arrivalAddress", trip.getArrivalAddress());

                request.getRequestDispatcher("/WEB-INF/jsp/admin/updateTrip_adminPage.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int telephoneNumber = Integer.parseInt(request.getParameter("telephoneNumber"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Controller.updateUser(userId, email, name, address, telephoneNumber, username, password);

        Controller.updateUsersInGoogleStorage(LocalStorage.getUsers(), "User.txt");

        response.sendRedirect("/");
    }
}
