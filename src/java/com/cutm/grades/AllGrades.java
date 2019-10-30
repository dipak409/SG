package com.cutm.grades;

import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dipak
 */
public class AllGrades extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
		//HttpSession session = request.getSession(false);
		//if(session != null)
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{			
			out.println("<center>");
			out.println("<h2>Student Grades </h2>");
			out.println("<h3>Welcome to student Profile</h3>");
			
			try 
			{	
				//Load and register the driver with
				//DriverManager Service.
				Class.forName("org.apache.derby.jdbc.ClientDriver");
				
				Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/StudentDB", "dipak", "dipak");
				if(con != null)
				{
					//Prepare the query and execute
					String query = "SELECT * FROM GRADES";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, cookies[0].getValue());
					ResultSet rs = pstmt.executeQuery();
					if(rs.next())
					{ 
						out.println("<table align='center' border='5' > ");
						out.println("<tr>");
						out.println("<td>REGISTRATION NUMBER</td>");
						out.println("<td>"+ rs.getString(1)+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>STUDENTNAME</td>");
						out.println("<td>"+ rs.getString(2)+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>SGRADE</td>");
						out.println("<td>"+ rs.getString(3)+"</td>");
						out.println("</tr>");
						
						out.println("</table>");
					}
				}
			}
			catch(ClassNotFoundException | SQLException e){ }				
			
			out.println("<a href='Login.jsp'>BACK</a>");
			out.println("</center>");
		}
		else
		{
			out.println("<center>");
			out.println("<h4>Not yet logged in.</h4>");
			out.println("<h4>Click <a href='Login.jsp'> here </a> to login</h4>");
			out.println("</center>");
		}
    }
        
   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
