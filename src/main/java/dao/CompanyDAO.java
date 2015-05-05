package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Company;

public class CompanyDAO {
	
	private EntityManager em;
	
	public CompanyDAO(){
		this.em= Persistence.createEntityManagerFactory("jobwars").createEntityManager();
	}

	public void create(Company company){
		em.persist(company);
	}
	
	public Company find(Long id){
		return em.find(Company.class, id);
	}
}
