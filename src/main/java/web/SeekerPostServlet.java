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
import dao.JobSeekerPostDAO;

/**
 * Servlet implementation class SeekerPostServlet
 */
@WebServlet("/SeekerPostServlet")
public class SeekerPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private JobPostDAO postDAO;
	private JobSeekerPostDAO applyDAO;
	//private JobSeekerDAO seekerDAO;

	public SeekerPostServlet() {
		super();
		postDAO = new JobPostDAO();
		applyDAO = new JobSeekerPostDAO();
		//seekerDAO = new JobSeekerDAO();
	}	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private static final String SEEKER_ID = "seekerId";
	private static final String POST_ID = "postId";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String seekerId = request.getParameter(SEEKER_ID);
		List<JobPost> posts = null;
		posts = postDAO.findByUser(seekerId);
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
		String seekerId = request.getParameter(SEEKER_ID);
		String postId = request.getParameter(POST_ID);
		if (seekerId != null && postId != null) {
			// JobSeeker seeker = seekerDAO.find(new Long(seekerId));
			// JobPost post = postDAO.find(new Long(postId));
			// JobSeekerPost rel = new JobSeekerPost();
			// rel.setJobPost(post);
			// rel.setJobSeeker(seeker);
			// applyDAO.apply(rel);
			applyDAO.apply(new Long(seekerId), new Long(postId));
		}
	}

}
