<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:useBean id="velibUser" scope = "session" class = "model.VelibUser"/>

<%
if (velibUser.getNom()==null) {
	out.print("<a href=\"signup.jsp\">Login here</a>");
}else {
	out.print("Welcome, " + velibUser.getNom());
}
%>

<body>
    <form action="SearchController" method="post">
      <input type="hidden" name="action" value="search"> 

      <table cellpadding="10" cellspacing="0" border="0">
        <tr>
          <td align="left" valign="top">
            <b>Search by :</b>

            <select name="searchby" size="1">
              <option value="district">
                District
              </option>
              <option value="department">
                Department
              </option>
            </select>
          </td>
          <td align="left" valign="top">
            <input type="text" name="str" value="" size="20" maxlength="120">
            <input type="image" src="images/b_ok.gif" border="0" name="OK" id="OK">
          </td>
        </tr>
      </table>
    </form>
</body>
</html>