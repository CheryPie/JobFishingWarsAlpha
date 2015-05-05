package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Company;
import model.JobSeeker;
import model.LoginUser;

public class LoginUserDAO {

	private EntityManager em;

	public LoginUserDAO() {
		this.em = Persistence.createEntityManagerFactory("jobwars")
				.createEntityManager();
	}

	public LoginUser autenticate(String userName, String pass) {
		try {
			return (LoginUser) em.createNativeQuery(
					"select * from login_user " + "where user_name='" + userName
							+ "' and password='" + pass+"'", LoginUser.class)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public void createCompany(LoginUser user) {
		em.getTransaction().begin();
		String idStr = em
				.createNativeQuery("select company_seq.nextval from dual")
				.getSingleResult().toString();
		Long idLong = new Long(idStr);
		Company company = new Company();
		company.setCompanyId(idLong);
		em.persist(company);
		user.setCompany(company);
		em.persist(user);
		em.getTransaction().commit();
	}

	public void createJobSeeker(LoginUser user) {
		String idStr = em
				.createNativeQuery("select job_seeker_seq.nextval from dual")
				.getSingleResult().toString();
		Long idLong = new Long(idStr);
		JobSeeker seeker = new JobSeeker();
		seeker.setJobSeekerId(idLong);
		em.getTransaction().begin();
		em.persist(seeker);
		user.setJobSeeker(seeker);
		em.persist(user);
		em.getTransaction().commit();
	}
}
