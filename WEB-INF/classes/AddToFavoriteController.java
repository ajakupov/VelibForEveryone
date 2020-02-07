

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import dao.FavoritesHome;
import model.Favorites;
import model.Station;
import model.VelibUser;

/**
 * Servlet implementation class AddToFavoriteController
 */
@WebServlet("/AddToFavoriteController")
public class AddToFavoriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToFavoriteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Station station = (Station) request.getSession().
				getAttribute("station");
		VelibUser user = (VelibUser) request.getSession().
				getAttribute("velibUser");
		System.out.println(station.toString());
		FavoritesHome favoriteDAO = new FavoritesHome();
		int id = favoriteDAO.getFavorites().size() + 10;
		System.out.println("id" + id +", stationid" + station.getStationId());
		Favorites favorites = new Favorites(id, user, station);
		System.out.println(favorites.toString());
		favoriteDAO.saveOrUpdate(favorites);
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher("/usersFavorites.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
