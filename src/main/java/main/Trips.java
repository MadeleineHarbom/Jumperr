package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Trip;
import model.User;
import storage.LocalStorage;

@WebServlet("/Trips")
public class Trips extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // users initialiseres
    public void init_trips() {
        User u = new User("Madeleine", "madeleine.harbom@gmail.com", "Laurvigsgade 2B", 60653173, "made", "madeofwin");
        LocalStorage.addTrip(new Trip("Today", "Now", "Later", "Laurvigsgade 2B", "SoenderHoej 30", u));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Just some init shit
        if (LocalStorage.getTrips() == null || LocalStorage.getTrips().size() == 0) { // eclipse vil ha !=null check ><
            init_trips();
            System.out.println("Trips inited");

        }

        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getAdmin() == 1) {

            request.setAttribute("trips", LocalStorage.getTrips());
            request.setAttribute("username", user.getUsername());

            request.getRequestDispatcher("/WEB-INF/jsp/admin/trips_adminPage.jsp").forward(request, response);

        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}