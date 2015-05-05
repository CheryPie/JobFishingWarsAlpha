package model;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.List;


/**
 * The persistent class for the JOB_POST database table.
 * 
 */
@Entity
@Table(name="JOB_POST")
@NamedQuery(name="JobPost.findAll", query="SELECT j FROM JobPost j")
public class JobPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_POST_ID")
	@Expose
	private Long jobPostId;
	
	@Expose
	private String description;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	@Expose
	private Company company;

	//bi-directional many-to-one association to JobPostSkillRel
	@OneToMany(mappedBy="jobPost")
	@Expose
	private List<JobPostSkillRel> jobPostSkillRels;

	//bi-directional many-to-one association to JobSeekerPost
	@OneToMany(mappedBy="jobPost")
	private List<JobSeekerPost> jobSeekerPosts;

	public JobPost() {
	}

	public Long getJobPostId() {
		return this.jobPostId;
	}

	public void setJobPostId(Long jobPostId) {
		this.jobPostId = jobPostId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<JobPostSkillRel> getJobPostSkillRels() {
		return this.jobPostSkillRels;
	}

	public void setJobPostSkillRels(List<JobPostSkillRel> jobPostSkillRels) {
		this.jobPostSkillRels = jobPostSkillRels;
	}

	public JobPostSkillRel addJobPostSkillRel(JobPostSkillRel jobPostSkillRel) {
		getJobPostSkillRels().add(jobPostSkillRel);
		jobPostSkillRel.setJobPost(this);

		return jobPostSkillRel;
	}

	public JobPostSkillRel removeJobPostSkillRel(JobPostSkillRel jobPostSkillRel) {
		getJobPostSkillRels().remove(jobPostSkillRel);
		jobPostSkillRel.setJobPost(null);

		return jobPostSkillRel;
	}

	public List<JobSeekerPost> getJobSeekerPosts() {
		return this.jobSeekerPosts;
	}

	public void setJobSeekerPosts(List<JobSeekerPost> jobSeekerPosts) {
		this.jobSeekerPosts = jobSeekerPosts;
	}

	public JobSeekerPost addJobSeekerPost(JobSeekerPost jobSeekerPost) {
		getJobSeekerPosts().add(jobSeekerPost);
		jobSeekerPost.setJobPost(this);

		return jobSeekerPost;
	}

	public JobSeekerPost removeJobSeekerPost(JobSeekerPost jobSeekerPost) {
		getJobSeekerPosts().remove(jobSeekerPost);
		jobSeekerPost.setJobPost(null);

		return jobSeekerPost;
	}

}