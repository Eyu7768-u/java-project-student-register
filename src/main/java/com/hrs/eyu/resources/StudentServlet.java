package com.hrs.eyu.resources;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    // IMPORTANT: Update these to match your local database settings
    private final String DB_URL = "jdbc:mysql://localhost:3306/eyua";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String view = request.getParameter("view");

        // Internal Modern CSS (React-style centering)
        String css = "<style>"
                + "body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f6; margin: 0; display: flex; justify-content: center; padding-top: 50px; }"
                + ".container { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); width: 100%; max-width: 600px; }"
                + "h2 { color: #333; margin-bottom: 20px; font-weight: 600; }"
                + "table { width: 100%; border-collapse: collapse; margin-top: 20px; }"
                + "th, td { text-align: left; padding: 15px; border-bottom: 1px solid #edf2f7; }"
                + "th { background-color: #f8fafc; color: #64748b; text-transform: uppercase; font-size: 12px; letter-spacing: 0.05em; }"
                + "input { width: 100%; padding: 12px; margin: 10px 0; border: 1px solid #e2e8f0; border-radius: 6px; box-sizing: border-box; }"
                + ".btn-primary { background: #3182ce; color: white; border: none; padding: 12px; border-radius: 6px; cursor: pointer; width: 100%; font-weight: 600; margin-top: 10px; }"
                + ".link { display: inline-block; margin-top: 20px; color: #3182ce; text-decoration: none; font-weight: 500; }"
                + ".error-box { color: #e53e3e; background: #fff5f5; padding: 15px; border-radius: 6px; border: 1px solid #feb2b2; margin-bottom: 20px; }"
                + "</style>";

        out.println("<html><head>" + css + "</head><body><div class='container'>");

        if ("list".equals(view)) {
            // VIEW ALL STUDENTS [cite: 5]
            out.println("<h2>Registered Students</h2>");
            out.println("<table><thead><tr><th>Name</th><th>Email</th><th>Year</th></tr></thead><tbody>");
            
            try {
                // Manually loading the driver often fixes "Error connecting to database"
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                     Statement stmt = conn.createStatement();
                      ResultSet rs = stmt.executeQuery("SELECT name, email, year FROM students")) { 
                    
                     while (rs.next()) { 
                        out.println("<tr><td>" + rs.getString("name") + "</td><td>" 
                                    + rs.getString("email") + "</td><td>" 
                                    + rs.getInt("year") + "</td></tr>");
                    }
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='3' class='error-box'>Error connecting to database: " + e.getMessage() + "</td></tr>");
            }
            out.println("</tbody></table>");
            out.println("<a href='StudentServlet' class='link'>+ Add New Student</a>");

        } else {
            // REGISTRATION FORM [cite: 2, 3]
            out.println("<h2>Student Registration</h2>");
            if(request.getParameter("error") != null) {
                out.println("<div class='error-box'>Email already exists or database error!</div>");
            }
            out.println("<form action='StudentServlet' method='POST'>");
            out.println("<label>Name</label><input type='text' name='name' placeholder='e.g. John Doe' required>");
            out.println("<label>Email</label><input type='email' name='email' placeholder='john@example.com' required>");
            out.println("<label>Year</label><input type='number' name='year' placeholder='e.g. 2024' required>");
            out.println("<button type='submit' class='btn-primary'>Register Student</button>");
            out.println("</form>");
            out.println("<a href='StudentServlet?view=list' class='link'>View Registered Students</a>");
        }
        
        out.println("<br><a href='index.jsp' class='link' style='color:#94a3b8; font-size:14px;'>&larr; Back to Home</a>");
        out.println("</div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name"); 
        String email = request.getParameter("email"); 
        String yearStr = request.getParameter("year"); 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                // Check if email already exists
                PreparedStatement check = conn.prepareStatement("SELECT id FROM students WHERE email = ?");
                check.setString(1, email);
                if (check.executeQuery().next()) {
                    response.sendRedirect("StudentServlet?error=duplicate");
                    return;
                }

               
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, email, year) VALUES (?, ?, ?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setInt(3, Integer.parseInt(yearStr));
                ps.executeUpdate();
                
                // Redirect to show all [cite: 5]
                response.sendRedirect("StudentServlet?view=list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("StudentServlet?error=db");
        }
    }
}