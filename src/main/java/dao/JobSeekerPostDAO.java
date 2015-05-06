package dao;

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

}
