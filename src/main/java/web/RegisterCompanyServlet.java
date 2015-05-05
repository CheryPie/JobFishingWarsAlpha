package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginUser;
import dao.LoginUserDAO;

/**
 * Servlet implementation class RegisterCompanyServlet
 */
@WebServlet("/RegisterCompanyServlet")
public class RegisterCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private LoginUserDAO loginDAO;
	
    public RegisterCompanyServlet() {
        super();
        loginDAO = new LoginUserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    private static final String USER_NAME="username";
    private static final String PASSWORD="password";
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter(USER_NAME);
		String password = request.getParameter(PASSWORD);
		LoginUser user = new LoginUser();
		if(userName!=null && password!=null){
			user.setUserName(userName);
			user.setPassword(password);
			loginDAO.createCompany(user);
			request.getRequestDispatcher("login1.html").forward(request, response);
		}
	}

}
