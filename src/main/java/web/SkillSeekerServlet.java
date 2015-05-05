package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Skill;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.JobSeekerSkillDAO;
import dao.SkillDAO;

/**
 * Servlet implementation class SkillSeekerServlet
 */
@WebServlet("/SkillSeekerServlet")
public class SkillSeekerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	//private JobSeekerDAO seekerDAO;
	private SkillDAO skillDAO;
	private JobSeekerSkillDAO relDAO;
	
    public SkillSeekerServlet() {
        super();
        //seekerDAO= new JobSeekerDAO();
        skillDAO = new SkillDAO();
        relDAO = new JobSeekerSkillDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final String SEEKER_ID="seekerId";
	private static final String SKILL_ID="skillId";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String seekerId = request.getParameter(SEEKER_ID);
		List<Skill> skills =  null;
		if(seekerId!=null){
			 skills = skillDAO.findByJobSeeker(seekerId);
		}else{
			 skills = skillDAO.findAll();
		}
		String resource= gson.toJson(skills);
		response.setContentType("application/json");
		response.getWriter().print(resource);
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seekerId = request.getParameter(SEEKER_ID);
		String skillId = request.getParameter(SKILL_ID);
		if(seekerId!=null && skillId!=null){
//			JobSeeker seeker = seekerDAO.find(new Long(seekerId));
//			Skill skill = skillDAO.find(new Long(skillId));
//			JobSeekerSkillRel rel = new JobSeekerSkillRel();
//			rel.setSkill(skill);
//			rel.setJobSeeker(seeker);
//			relDAO.create(rel);
			relDAO.create(new Long(seekerId), new Long(skillId));
			 request.getRequestDispatcher("job_seeker_page.html").forward(request, response);
		}
	}

}
