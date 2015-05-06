package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Company;
import model.JobSeeker;
import model.LoginUser;
import model.LoginUserRole;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class LoginUserDAO {

	private EntityManager em;

	public LoginUserDAO() {
		this.em = Persistence.createEntityManagerFactory("jobwars")
				.createEntityManager();
	}

	public LoginUser autenticate(String userName, String pass) {
		// try {
		// pass = Hashing.sha256().hashString(pass, Charsets.UTF_8).toString();
		// return (LoginUser) em.createNativeQuery(
		// "select * from login_user " + "where user_name='" + userName
		// + "' and password='" + pass+"'", LoginUser.class)
		// .getSingleResult();
		// } catch (Exception e) {
		// return null;
		// }
		try {
			String txtQuery = "SELECT * from LOGIN_USER u where u.USER_NAME=?1 and u.password=?2";
			Query query =  em.createNativeQuery(txtQuery, LoginUser.class);
			query.setParameter(1, userName);
			pass = Hashing.sha256().hashString(pass, Charsets.UTF_8).toString();
			query.setParameter(2, pass);
			return (LoginUser) query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());
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
		String pass = user.getPassword();
		pass = Hashing.sha256().hashString(pass, Charsets.UTF_8).toString();
		user.setPassword(pass);
		user.setRole(em.find(LoginUserRole.class, new Long("2")));
		em.persist(company);
		user.setCompany(company);
		em.persist(user);
		em.getTransaction().commit();
	}

	public void createJobSeeker(LoginUser user) {
		em.getTransaction().begin();
		String idStr = em
				.createNativeQuery("select job_seeker_seq.nextval from dual")
				.getSingleResult().toString();
		Long idLong = new Long(idStr);
		JobSeeker seeker = new JobSeeker();
		seeker.setJobSeekerId(idLong);
		String pass = user.getPassword();
		pass = Hashing.sha256().hashString(pass, Charsets.UTF_8).toString();
		user.setPassword(pass);
		user.setRole(em.find(LoginUserRole.class, new Long("1")));
		em.persist(seeker);
		user.setJobSeeker(seeker);
		em.persist(user);
		em.getTransaction().commit();
	}
}
