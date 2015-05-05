package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.JobPost;
import model.JobSeeker;
import model.JobSeekerPost;

public class JobSeekerPostDAO {

	private EntityManager em;

	public JobSeekerPostDAO() {
		this.em = Persistence.createEntityManagerFactory("jobwars")
				.createEntityManager();
	}

	public void apply(JobSeekerPost rel) {
		em.getTransaction().begin();
		em.persist(rel);
		em.getTransaction().commit();
	}

	public void apply(Long seekerId, Long postId) {
		em.getTransaction().begin();
		JobSeeker seeker = em.find(JobSeeker.class, new Long(seekerId));
		JobPost post = em.find(JobPost.class, new Long(postId));
		JobSeekerPost rel = new JobSeekerPost();
		rel.setJobPost(post);
		rel.setJobSeeker(seeker);
		em.persist(rel);
		em.flush();
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<JobSeekerPost> findApllied(String companyId) {
		return em
				.createNativeQuery(
						"select * from job_seeker_post "
								+ " where JOB_POST_ID in (select JOB_POST_ID from job_post where company_id="
								+ companyId + ")").getResultList();
	}
}
