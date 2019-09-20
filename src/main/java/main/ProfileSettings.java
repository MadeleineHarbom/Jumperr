package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/ProfileSettings")
public class ProfileSettings extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {

            response.sendRedirect("/");

        } else {

            // hvis det er Admin
            if (user.getAdmin() == 1) {

                request.getRequestDispatcher("/WEB-INF/jsp/admin/settings_adminPage.jsp").forward(request, response);

            } else {

                request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/settings.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
