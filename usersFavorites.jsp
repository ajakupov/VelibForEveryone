<%@page import="dao.FavoritesHome"%>
<%@page import="model.Station"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My favorite stations</title>
</head>
<body>
<jsp:useBean id="velibUser" scope = "session" class = "model.VelibUser"/>

<table border="1" cellpadding="10px" cellspacing ="0">
		<tr>
			<td>
				<b>Station name</b>
			</td>
			<td>
				<b>Station address</b>
			</td>
			<td>
				<b>Station open</b>
			</td>
			<td>
				<b>Station bonus</b>
			</td>
			<td>
				<b>Updated at:</b>
			</td>
		</tr>
		
	 <%
	 	FavoritesHome favoriteDAO = new FavoritesHome();
	 	List<Station> favoriteStations = favoriteDAO.getFavoriteStations(velibUser.getNumInd().intValue());
	 	for (int i = 0; i < favoriteStations.size(); i++) {%>
	 		<tr>
	 		<td>
				<%=favoriteStations.get(i).getStName()%> </a>
			</td>
			<td>
				<%=favoriteStations.get(i).getFullAddress()%>
			</td>
			<td>
				<%=favoriteStations.get(i).getStOpen()%>
			</td>
			<td>
				<%=favoriteStations.get(i).getStBonus()%>
			</td>
			<td>
				<%=favoriteStations.get(i).getUpdateDate().toGMTString()%>
			</td>
		</tr>
	 <%}
	 %>
	 </table>
</body>
</html>