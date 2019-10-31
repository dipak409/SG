<%-- 
    Document   : PassFailReport
    Created on : Oct 30, 2019, 1:39:01 PM
    Author     : Dipak
--%>
<%@ page import="java.sql.*" %>

<% Class.forName("org.apache.derby.jdbc.ClientDriver"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Grades</title>
    </head>
    <body>
        <h1>Student Grades</h1>
        <%
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/StudentDB", "dipak", "dipak");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from STUDENTGRADES where overall='O'");
        %>

        <TABLE BORDER="1">
            <TR>
                <TH>REGISTRATION NUMBER</TH>
                <TH>STUDENT NAME</TH>
                <TH>SUBJECT1 GRADE</TH>
                <TH>SUBJECT2 GRADE</TH>
                <TH>SUBJECT3 GRADE</TH>
                <TH>OVERALL GRADE</TH>
            </TR>
            <% while (resultset.next()) {%>
            <TR>
                <TD> <%= resultset.getString(1)%></td>
                <TD> <%= resultset.getString(2)%></TD>
                <TD> <%= resultset.getString(3)%></TD>
                <TD> <%= resultset.getString(4)%></td>
                <TD> <%= resultset.getString(5)%></TD>
                <TD> <%= resultset.getString(6)%></TD>

            </TR>
            <% }%>
        </TABLE>
    </body>
</html>
