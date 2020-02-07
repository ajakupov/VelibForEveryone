

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.sun.corba.se.spi.orbutil.fsm.State;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import dao.StationHome;
import jdk.internal.org.xml.sax.SAXException;
import model.Station;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		String searchBy = request.getParameter("searchby");
		String str = request.getParameter("str").trim().toLowerCase();
		int stationId = -1;
		String stName = null;
		String address = null; 
		String fullAddress = null;
		double longitude = 0;
		double lattitude = 0;
		int stOpen = 0;
		int stBonus = 0;
		Date updateDate = new Date();
		
		XMLParser xmlParser = new XMLParser();
		StationHome stationDAO = new StationHome();
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		
		try {
			Document stationsList = xmlParser.loadXMLSTring(service.
					path("service").path("carto").accept(MediaType.TEXT_PLAIN).
					get(String.class));
			
			NodeList nodeList = stationsList.getElementsByTagName("marker");
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				for (int j =0; j < nodeList.item(i).getAttributes().getLength(); 
						j++) {
					String parameter = nodeList.item(i).getAttributes().
							item(j).getNodeName();
					String value = nodeList.item(i).getAttributes().item(j).
							getNodeValue();
					
					if (parameter.equals("address")) {
						address = value.trim().toLowerCase();
					} else if (parameter.equals("bonus")) {
						stBonus = Integer.parseInt(value);
					} else if (parameter.equals("fullAddress")) {
						fullAddress = value.trim().toLowerCase();
					} else if (parameter.equals("lat")) {
						lattitude = Double.parseDouble(value);
					} else if (parameter.equals("lng")) {
						longitude = Double.parseDouble(value);
					} else if (parameter.equals("name")) {
						stName = value.trim().toLowerCase();
					} else if (parameter.equals("number")) {
						stationId = Integer.parseInt(value);
					} else if (parameter.equals("open")) {
						stOpen = Integer.parseInt(value);
					}
					
					if (!(stationId<0 || stName.equals(null))) {
						Station station = new Station(stationId, stName, 
								address, fullAddress, longitude, lattitude,
								stOpen, stBonus, updateDate);
						System.out.println("Saving station data");
						stationDAO.saveOrUpdate(station);
						System.out.println("Success");
					}
				}
				System.out.println("Station list has "
						+ "been successfully updated");				
				
				RequestDispatcher requestDispatcher = 
						request.getRequestDispatcher("/showStations.jsp");
				requestDispatcher.forward(request, response);
			}
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		}
		
		System.out.println(searchBy + ", " + str);
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://www.velib.paris/").build();
	}
}
