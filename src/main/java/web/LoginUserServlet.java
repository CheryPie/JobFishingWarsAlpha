package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.JobSeeker;
import model.LoginUser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.CompanyDAO;
import dao.JobSeekerDAO;
import dao.LoginUserDAO;

/**
 * Servlet implementation class LoginUserServlet
 */
@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private LoginUserDAO loginDAO;
	private CompanyDAO companyDAO;
	private JobSeekerDAO seekerDAO;

	public LoginUserServlet() {
		super();
		loginDAO = new LoginUserDAO();
		companyDAO = new CompanyDAO();
		seekerDAO = new JobSeekerDAO();
		// loginDAO.createCompany(new LoginUser());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private static final String USER_NAME = "username";
	private static final String PASSWORD = "password";
	private static String CURRENT_COMPANY_ID;
	private static String CURRENT_JOBSEEKER_ID;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String resource = null;
		if (CURRENT_COMPANY_ID != null) {
			Company company = companyDAO.find(new Long(CURRENT_COMPANY_ID));
			resource = gson.toJson(company);
		} else if (CURRENT_JOBSEEKER_ID != null) {
			JobSeeker seeker = seekerDAO.find(new Long(CURRENT_JOBSEEKER_ID));
			resource = gson.toJson(seeker);
		}
		response.setContentType("application/json");
		response.getWriter().print(resource);
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter(USER_NAME);
		String password = request.getParameter(PASSWORD);
		if (userName != null && password != null) {
			LoginUser user = loginDAO.autenticate(userName, password);
			if (user != null) {
				if (user.getCompany() != null
						&& user.getCompany().getCompanyId() != null) {
					CURRENT_COMPANY_ID = user.getCompany().getCompanyId()
							.toString();
					request.getRequestDispatcher("company_page.html").forward(
							request, response);
					return;
				} else if (user.getJobSeeker() != null
						&& user.getJobSeeker().getJobSeekerId() != null) {
					CURRENT_JOBSEEKER_ID = user.getJobSeeker().getJobSeekerId()
							.toString();
					request.getRequestDispatcher("job_seeker_page.html")
							.forward(request, response);
					return;
				}
			} else {
				request.getRequestDispatcher("login1.html").forward(request,
						response);
				return;
			}
		} else {
			request.getRequestDispatcher("login1.html").forward(request,
					response);
			return;
		}
		request.getRequestDispatcher("login1.html").forward(request, response);
		return;
	}

}
