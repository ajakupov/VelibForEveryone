<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="RegistrationController" method="post">
      <input type="hidden" name="action" value="search"> 

      <table cellpadding="10" cellspacing="0" border="0">
      	<tr>
      		<td>Your name</td>
      		<td><input type = "text" name = "nom"> </td>
      	</tr>
      	<tr>
      		<td>Your surname</td>
      		<td><input type = "text" name = "prenom"> </td>
      	</tr>
      	<tr>
      		<td>Your mail (not mandatory)</td>
      		<td><input type = "text" name = "email"> </td>
      	</tr>
      	<tr>
      		<td>Your password</td>
      		<td><input type = "password" name = "vuPassword"> </td>
      	</tr>
      </table>
      <input type = "submit" value = "register">
    </form>
</body>
</html>