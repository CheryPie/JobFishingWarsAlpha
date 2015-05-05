	package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Company;
import model.JobSeeker;
import model.LoginUser;
import model.LoginUserRole;

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
//        String txtQuery = "select u from LoginUser u where u.userName=:userName and u.password=:password";
//        TypedQuery<LoginUser> query = em.createQuery(txtQuery, LoginUser.class);
//        query.setParameter("userName", userName);
//        //query.setParameter("password", getHashedPassword(pass));
//        query.setParameter("password",pass);
//        return query.getSingleResult();
	}

	public void createCompany(LoginUser user) {
		em.getTransaction().begin();
		String idStr = em
				.createNativeQuery("select company_seq.nextval from dual")
				.getSingleResult().toString();
		Long idLong = new Long(idStr);
		Company company = new Company();
		company.setCompanyId(idLong);
		user.setRole(em.find(LoginUserRole.class,new Long("2")));
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
		user.setRole(em.find(LoginUserRole.class,new Long("1")));
		em.persist(seeker);
		user.setJobSeeker(seeker);
		em.persist(user);
		em.getTransaction().commit();
	}
}
