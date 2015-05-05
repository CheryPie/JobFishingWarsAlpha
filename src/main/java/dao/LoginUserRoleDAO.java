package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.LoginUserRole;

public class LoginUserRoleDAO {


	private EntityManager em;

	public LoginUserRoleDAO() {
		this.em = Persistence.createEntityManagerFactory("jobwars")
				.createEntityManager();
	}
	
	public LoginUserRole findCompanyRole(){
		return em.find(LoginUserRole.class,new Long(2));
	}
	
	public LoginUserRole findJobSeekerRole(){
		return em.find(LoginUserRole.class,new Long(1));
	}
}
