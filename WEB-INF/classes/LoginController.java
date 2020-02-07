

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VelibUserHome;
import model.VelibUser;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login").trim();
		String password = request.getParameter("password").trim();
		
		VelibUser velibUser = null;
		
		System.out.println(login+ ", " + password);
		VelibUserHome userDAO = new VelibUserHome();
		velibUser = userDAO.getVelibUser(login, password);
		if (velibUser != null) {
			request.getSession().setAttribute("velibUser", velibUser);
			
			RequestDispatcher requestDispatcher = 
					request.getRequestDispatcher("/welcome.jsp");
			requestDispatcher.forward(request, response);
		} else {
			RequestDispatcher requestDispatcher = 
					request.getRequestDispatcher("/signin.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
