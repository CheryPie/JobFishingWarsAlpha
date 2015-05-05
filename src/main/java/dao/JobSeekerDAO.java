package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.JobSeeker;

public class JobSeekerDAO {
	
	private EntityManager em;
	public JobSeekerDAO(){
		this.em= Persistence.createEntityManagerFactory("jobwars").createEntityManager();
	}

	public void create(JobSeeker jobSeeker){
		em.persist(jobSeeker);
	}
	
	
	public JobSeeker find(Long id){
		return em.find(JobSeeker.class, id);
	}
}
