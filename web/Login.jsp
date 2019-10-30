<%-- 
    Document   : Login
    Created on : Oct 16, 2019, 1:18:35 PM
    Author     : Dipak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form  method="post" action="LoginServlet" >
    <input type="hidden" name="action" value="Login">	
		<table align="center">
			<tr>
				<th colspan="2" style ="padding:20px;"><h1>LOGIN PAGE</h1></th>
			</tr>
			<tr>
				<td>User ID</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>
			
			<tr>
				
				<td style="padding:20px;"><input type="reset" value="RESET">&nbsp</td><td><input type="submit" value="LOGIN"></td>
                                
                                <td> <a href="SignUp.jsp">NEW USER!!!</a></td>
                                
                        </tr>
                        
			
		</table>
	</form>
</body>
</html>