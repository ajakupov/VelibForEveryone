<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="LoginController" method="post">
        <table border="0" cellpadding="5" cellspacing="0">
          <tr>
            <td align="left" valign="top" class="inverted">
              <b>ENTER ID and PASSWORD</b>
            </td>
          </tr>

          <tr>
            <td align="left" valign="top">
              <b class="smaller">Login</b><br>

              <input type="text" name="login" maxlength="50" size="20">

              <b class="smaller">Password</b><br>

              <input type="password" name="password" maxlength="20" size="20">
            </td>
          </tr>

          <tr>
            <td align="center" valign="top" class="inverted">
              <input type="submit" value="Login" name="login">
            </td>
          </tr>
        </table>
      </form>
</body>
</html>