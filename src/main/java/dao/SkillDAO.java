package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	
	public List<Skill> findByJobSeeker(Long jobSeekerId){
		String txtQuery = "select s from Skill s where s.skillId in "
				+ " ( select r.skill.skillId from JobSeekerSkillRel r where r.jobSeeker.jobSeekerId=:jobSeekerId)";
		TypedQuery<Skill> query = em.createQuery(txtQuery, Skill.class);
		query.setParameter("jobSeekerId", jobSeekerId);
		return query.getResultList();
	}
	
	public List<Skill> findAll(){
		String txtQuery = "select s from Skill s";
		TypedQuery<Skill> query = em.createQuery(txtQuery, Skill.class);
		return query.getResultList();
	}
	
	public Skill find(Long id){
		return em.find(Skill.class, id);
	}
}
