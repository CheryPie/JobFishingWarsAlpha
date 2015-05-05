package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginUserDAO;
import model.LoginUser;

/**
 * Servlet implementation class RegisterSeekerServlet
 */
@WebServlet("/RegisterSeekerServlet")
public class RegisterSeekerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private LoginUserDAO loginDAO;

	public RegisterSeekerServlet() {
		super();
		loginDAO = new LoginUserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private static final String USER_NAME = "username";
	private static final String PASSWORD = "password";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter(USER_NAME);
		String password = request.getParameter(PASSWORD);
		LoginUser user = new LoginUser();
		if (userName != null && password != null) {
			user.setUserName(userName);
			user.setPassword(password);
			loginDAO.createJobSeeker(user);
			request.getRequestDispatcher("login1.html").forward(request,
					response);
		}
	}

}
