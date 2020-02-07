<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Station Details</title>
</head>
<body>
<jsp:useBean id="station" scope = "session" class = "model.Station"/>
<h1>Station <%=station.getStName()%> </h1>

<h2>Address: <%=station.getFullAddress()%></h2>
<h2>Updated at: <%=station.getUpdateDate().toGMTString()%></h2>
<h2>Bonus: <%=station.getStBonus()%></h2>
<h2>Open: <%=station.getStOpen()%></h2>
<jsp:useBean id="velibUser" scope = "session" class = "model.VelibUser"/>

<%
if (velibUser.getNom()==null) {
	out.print("You have to <a href=\"signup.jsp\">Login here</a> to add the station to your favorite");
}else {
	out.print("<a href = \"AddToFavoriteController\">Add to my Favorites</a>");
}
%>
</body>
</html>