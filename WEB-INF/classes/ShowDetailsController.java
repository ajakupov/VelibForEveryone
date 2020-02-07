

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StationHome;
import model.Station;

/**
 * Servlet implementation class ShowDetailsController
 */
public class ShowDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stationID = request.getParameter("stationId");
		StationHome stationDAO = new StationHome();
		Station station = stationDAO.getStation(
				new BigDecimal(Integer.parseInt(stationID)));
		
		System.out.println("Station info");
		System.out.println(station.toString());
		
		request.getSession().setAttribute("station", station);
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher("/stationDetails.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
