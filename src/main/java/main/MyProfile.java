package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet {
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

                request.getRequestDispatcher("/WEB-INF/jsp/admin/myProfile_adminPage.jsp").forward(request, response);

            } else {

                request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/myProfile.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}