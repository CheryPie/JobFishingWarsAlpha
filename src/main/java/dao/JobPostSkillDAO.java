package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.JobPost;
import model.JobPostSkillRel;
import model.Skill;

public class JobPostSkillDAO {
	private EntityManager em;
	
	public JobPostSkillDAO(){
		this.em= Persistence.createEntityManagerFactory("jobwars").createEntityManager();
	}

	public void createRel(JobPost post, Skill skill){
		JobPostSkillRel rel = new JobPostSkillRel();
		rel.setJobPost(post);
		rel.setSkill(skill);
		em.getTransaction().begin();
		em.persist(rel);
		em.getTransaction().commit();
	}
}
