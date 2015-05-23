package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import model.LoginUser;
import dao.LoginUserDAO;

//@Stateless
@Path("company")
public class CompanyManager {

	//@EJB
	private LoginUserDAO userDAO = new LoginUserDAO();
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerCompany(LoginUser newCompany){
		userDAO.createCompany(newCompany);
	}
	
}
