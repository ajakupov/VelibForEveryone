

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
 * Servlet implementation class RegistrationController
 */
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
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
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String email = request.getParameter("email").trim();
		String vuPassword = request.getParameter("vuPassword").trim();
		
		VelibUserHome userDAO = new VelibUserHome();
		
		VelibUser velibUser = new VelibUser(userDAO.getVelibUsers().size()+1, 
				nom, prenom, vuPassword, email, 4.4449664, 50.4130605);
		userDAO.saveOrUpdate(velibUser);
		request.getSession().setAttribute("velibUser", velibUser);
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher("/welcome.jsp");
		requestDispatcher.forward(request, response);
	}

}
