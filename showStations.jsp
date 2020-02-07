<%@page import="dao.StationHome"%>
<%@page import="model.Station"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
  <head>
    <style>
       #map {
        height: 500px;
        width: 100%;
       }
    </style>
  </head>
  <%="Results for : " + request.getParameter("searchby")%>
  <%=" Seach parameter: " + request.getParameter("str")%>
  <%
  List<Station> clientResult = null;
  String searchBy = request.getParameter("searchby");
  String str = request.getParameter("str").trim().toLowerCase();
  StationHome stationDAO = new StationHome();
  int counter = 0;
  if (searchBy.equals("department")) {
		clientResult = stationDAO.getStationsByDepartment(str);
	} else {
		clientResult = stationDAO.getStationsByDistrict(str);
	}
  %>
  <%=clientResult.size() + " station(s) found"%>
  <body>
    <h3>Stations around you</h3>
    <div id="map"></div>
    <script>
      function initMap() {
    	  var direction;
    	  var pos;
    	  var panel = document.getElementById('panel');
    	  var infowindow = new google.maps.InfoWindow();
    	  var user_infowindow = new google.maps.InfoWindow();
          var marker, i, userMarker;
          var iconBase = 'https://maps.google.com/mapfiles/';
          var icons = {
            user_icon: {
              icon: iconBase + 'marker_greenA.png'
            },
            station_icon: {
              icon: iconBase + 'marker_orangeA.png'
            }
          };
            
          if (navigator.geolocation) {
        	  navigator.geolocation.getCurrentPosition(function(position) {
                  pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                  };
                  userMarker = new google.maps.Marker({
                      position: pos, icon: icons['user_icon'].icon,
                       map: map});
                  userMarker.addListener('click', function() {
                      infowindow.setContent("You are here");
                      infowindow.open(map, userMarker);
                	  });
                	                  
                  map.setCenter(pos);
                }, function() {
                  handleLocationError(true, infoWindow, map.getCenter());
                });  
          } else {
            	  handleLocationError(false, infoWindow, map.getCenter());
          }
          var map = new google.maps.Map(document.getElementById('map'), {
              zoom: 10, center: new google.maps.LatLng(48.814206, 2.384207), 
              mapTypeId: google.maps.MapTypeId.ROADMAP});
          direction = new google.maps.DirectionsRenderer({
      		map   : map,
      		panel : panel
      	  });
          
          for (i = 0; i <<%=clientResult.size()%>; i++) {
              <%for (int i = 0; i < clientResult.size(); i++) {%>
              marker = new google.maps.Marker({
                  position: new google.maps.LatLng(
                  <%=clientResult.get(i).getLattitude()%>, 
                  <%=clientResult.get(i).getLongitude()%>), 
                  icon: icons['station_icon'].icon, map: map});
              google.maps.event.addListener(marker, 'click', 
                      (function(marker, i) {
                          return function() {
                               
                              var showDetails = "<a href = \"ShowDetailsController?stationId=<%=clientResult.get(i).getStationId()%>\"> Show Details </a>";
                              var stName = "<%=clientResult.get(i).getStName()%>";
                              infowindow.setContent(stName.concat("<br>", showDetails));
                              infowindow.open(map, marker);
                             }
                          })(marker, i));
              google.maps.event.addListener(marker, 'dblclick', 
                      (function(marker, i) {
                          return function() {
                               
                        	  var request = {
                                      origin      : pos,
                                      destination : "<%=clientResult.get(i).getFullAddress()%>",
                                      travelMode  : google.maps.DirectionsTravelMode.DRIVING // Mode de conduite
                                  }
                                  var directionsService = new google.maps.DirectionsService(); // Service de calcul d'itinÃ©raire
                                  directionsService.route(request, function(response, status){ // Envoie de la requÃªte pour calculer le parcours
                                      if(status == google.maps.DirectionsStatus.OK){
                                          direction.setDirections(response); // Trace l'itinÃ©raire sur la carte et les diffÃ©rentes Ã©tapes du parcours
                                      }
                                  });
                             }
                          })(marker, i));
                          <%}%>
    }
}
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdiXbc-e1K_h6Zhx1l1ZNxDlESDdju0AQ&callback=initMap">
    </script>
  </body>
</html>