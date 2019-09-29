package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseConnection;

@WebServlet("/DatabaseConnectionTest")
public class DatabaseConnectionTest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Testing Database connection.");

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement("INSERT INTO entries(guestName, content) VALUES (?,?)");

                stmt.clearParameters();
                stmt.setString(1, "Rabeea");
                stmt.setString(2, "My first insert from JDBC in Eclipse");
                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
        out.println("End of initial demo.");
    }
}
