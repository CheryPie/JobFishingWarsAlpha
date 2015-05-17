package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import model.LoginUser;
import dao.LoginUserDAO;

@Path("jobseeker")
public class JobSeekerManager {

	private LoginUserDAO userDAO = new LoginUserDAO();
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerSeeker(LoginUser newSeeker){
		userDAO.createJobSeeker(newSeeker);
	}
}
