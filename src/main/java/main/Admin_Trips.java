package main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.User;
import model.Trip;
import storage.Storage;

@WebServlet("/trips")
//eller en bedre path:P
public class Admin_Trips extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	// users initialiseres
    public void init_trips() {
    	User u = new User("Madeleine", "madeleine.harbom@gmail.com", "Laurvigsgade 2B", 60653173, "made", "madeofwin");
        Storage.addTrip(new Trip("Today", "Now", "Later", "Laurvigsgade 2B", "SoenderHoej 30", u));
    }

	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//Just some init shit
		if (Storage.getTrips() == null || Storage.getTrips().size() == 0 ) { //eclipse vil ha !=null check ><
			init_trips();
			System.out.println("Trips inited");
			
		}
		
		
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

        } else {
            // hvis det er Admin
            if (user.getAdmin() == 1) {

                request.setAttribute("trips", Storage.getTrips());
                request.setAttribute("username", user.getUsername());

                request.getRequestDispatcher("/WEB-INF/jsp/trips_adminPage.jsp").forward(request, response);

            } else {

                request.setAttribute("username", user.getUsername());
                response.sendRedirect("/Jumper");
            }
        }
    }
}
