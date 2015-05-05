package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JobPost;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.JobPostDAO;

/**
 * Servlet implementation class CompanyPostServlet
 */
@WebServlet("/CompanyPostServlet")
public class CompanyPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private JobPostDAO postDAO;
	
    public CompanyPostServlet() {
        super();
        postDAO = new JobPostDAO();
    }
	
	private static final String COMPANY_ID = "companyId";
	private static final String DESCRIPTION = "description";
	private static final String SKILLS_ID="skillsId";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String companyId = request.getParameter(COMPANY_ID);	
		List<JobPost> posts = postDAO.findByCompany(companyId);
		String resource = gson.toJson(posts);
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
		String companyId = request.getParameter(COMPANY_ID);
		String description = request.getParameter(DESCRIPTION);
		String[] skillsId = request.getParameterValues(SKILLS_ID);
		if (companyId != null && description != null) {
			postDAO.create(companyId, description,skillsId);
			request.getRequestDispatcher("company_page.html").forward(request,
					response);
		}
	}

}
