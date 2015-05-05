package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Skill;

public class SkillDAO {
	private EntityManager em;

	public SkillDAO(){
		this.em= Persistence.createEntityManagerFactory("jobwars").createEntityManager();
	}

	public void create(Skill skill){	
		em.getTransaction().begin();
		em.persist(skill);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Skill> findByJobSeeker(String jobSeekerId){
		return em.createNativeQuery(" select * from skill where skill_id in "
				+ "(select skill_id from job_seeker_skill_rel where "
				+ "job_seeker_id="+jobSeekerId+")",Skill.class).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Skill> findByJobPost(String jobPostId){
		return em.createNativeQuery(" select * from skill where skill_id in "
				+ "(select skill_id from job_post_skill where "
				+ "job_post_id="+jobPostId+")",Skill.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Skill> findAll(){
		return em.createNativeQuery(" select * from skill" ,Skill.class).getResultList();
	}
	
	public Skill find(Long id){
		return em.find(Skill.class, id);
	}
}
