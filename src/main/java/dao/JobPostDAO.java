package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Company;
import model.JobPost;
import model.JobPostSkillRel;
import model.Skill;

public class JobPostDAO {

	private EntityManager em;

	public JobPostDAO() {
		this.em = Persistence.createEntityManagerFactory("jobwars")
				.createEntityManager();
	}

	public void create(JobPost jobPost, List<Skill> skills) {
		String idStr = em
				.createNativeQuery("select job_post_seq.nextval from dual")
				.getSingleResult().toString();
		Long idLong = new Long(idStr.substring(1, idStr.length() - 1));
		jobPost.setJobPostId(idLong);
		em.getTransaction().begin();
		em.persist(jobPost);
		for (Skill skill : skills) {
			JobPostSkillRel rel = new JobPostSkillRel();
			rel.setJobPost(jobPost);
			rel.setSkill(skill);
			em.persist(rel);
		}
		em.getTransaction().commit();
	}

	public void create(String companyId, String description, String[] skills) {
		em.getTransaction().begin();
		Company company = em.find(Company.class, new Long(companyId));
		JobPost post = new JobPost();
		String idStr = em
				.createNativeQuery("select job_post_seq.nextval from dual")
				.getSingleResult().toString();
		post.setJobPostId(new Long(idStr));
		post.setCompany(company);
		post.setDescription(description);
		em.persist(post);
		// List<JobPostSkillRel> rels = new ArrayList<JobPostSkillRel>();
		for (String skill : skills) {
			// JobPostSkillRel rel = new JobPostSkillRel();
			// Skill skillObj = em.find(Skill.class,new Long(skill));
			// rel.setSkill(skillObj);
			// rel.setJobPost(post);
			// em.persist(rel);
			// rels.add(rel);
			em.createNativeQuery(
					"Insert Into Job_Post_Skill_Rel(job_post_id,skill_id) values ("
							+ idStr + "," + skill + ")").executeUpdate();
		}
		// if(rels.size()!=0){
		// em.persist(rels);
		// }
		// em.flush();
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public List<JobPost> findByCompany(String companyId) {
		 return em.createNativeQuery(
		 "select * from job_post where company_id=" + companyId,
		 JobPost.class).getResultList();
//		String txtQuery = "select p from JobPost p where p.company.companyId=:companyId";
//		TypedQuery<JobPost> query = em.createQuery(txtQuery, JobPost.class);
//		query.setParameter("companyId", new Long(companyId));
//		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<JobPost> findByUser(String UserId) {
		return em
				.createNativeQuery(
						"select * from job_post where Job_Post_Id  "
								+ " in (select Distinct JOB_POST_ID from JOB_POST_SKILL_REL where SKILL_ID "
								+ " in (select skill_id from job_seeker_skill_rel where Job_Seeker_Id="
								+ UserId + ")) ", JobPost.class)
				.getResultList();
	}

	public JobPost find(Long id) {
		return em.find(JobPost.class, id);
	}
}