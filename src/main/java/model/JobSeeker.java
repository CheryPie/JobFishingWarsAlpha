package model;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.List;


/**
 * The persistent class for the JOB_SEEKER database table.
 * 
 */
@Entity
@Table(name="JOB_SEEKER")
@NamedQuery(name="JobSeeker.findAll", query="SELECT j FROM JobSeeker j")
public class JobSeeker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_SEEKER_ID")
	@Expose
	private Long jobSeekerId;

	//bi-directional many-to-one association to JobSeekerPost
	@OneToMany(mappedBy="jobSeeker")
	private List<JobSeekerPost> jobSeekerPosts;

	//bi-directional many-to-one association to JobSeekerSkillRel
	@OneToMany(mappedBy="jobSeeker")
	@Expose
	private List<JobSeekerSkillRel> jobSeekerSkillRels;

	//bi-directional many-to-one association to LoginUser
	@OneToMany(mappedBy="jobSeeker")
	private List<LoginUser> loginUsers;

	public JobSeeker() {
	}

	public Long getJobSeekerId() {
		return this.jobSeekerId;
	}

	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public List<JobSeekerPost> getJobSeekerPosts() {
		return this.jobSeekerPosts;
	}

	public void setJobSeekerPosts(List<JobSeekerPost> jobSeekerPosts) {
		this.jobSeekerPosts = jobSeekerPosts;
	}

	public JobSeekerPost addJobSeekerPost(JobSeekerPost jobSeekerPost) {
		getJobSeekerPosts().add(jobSeekerPost);
		jobSeekerPost.setJobSeeker(this);

		return jobSeekerPost;
	}

	public JobSeekerPost removeJobSeekerPost(JobSeekerPost jobSeekerPost) {
		getJobSeekerPosts().remove(jobSeekerPost);
		jobSeekerPost.setJobSeeker(null);

		return jobSeekerPost;
	}

	public List<JobSeekerSkillRel> getJobSeekerSkillRels() {
		return this.jobSeekerSkillRels;
	}

	public void setJobSeekerSkillRels(List<JobSeekerSkillRel> jobSeekerSkillRels) {
		this.jobSeekerSkillRels = jobSeekerSkillRels;
	}

	public JobSeekerSkillRel addJobSeekerSkillRel(JobSeekerSkillRel jobSeekerSkillRel) {
		getJobSeekerSkillRels().add(jobSeekerSkillRel);
		jobSeekerSkillRel.setJobSeeker(this);

		return jobSeekerSkillRel;
	}

	public JobSeekerSkillRel removeJobSeekerSkillRel(JobSeekerSkillRel jobSeekerSkillRel) {
		getJobSeekerSkillRels().remove(jobSeekerSkillRel);
		jobSeekerSkillRel.setJobSeeker(null);

		return jobSeekerSkillRel;
	}

	public List<LoginUser> getLoginUsers() {
		return this.loginUsers;
	}

	public void setLoginUsers(List<LoginUser> loginUsers) {
		this.loginUsers = loginUsers;
	}

	public LoginUser addLoginUser(LoginUser loginUser) {
		getLoginUsers().add(loginUser);
		loginUser.setJobSeeker(this);

		return loginUser;
	}

	public LoginUser removeLoginUser(LoginUser loginUser) {
		getLoginUsers().remove(loginUser);
		loginUser.setJobSeeker(null);

		return loginUser;
	}

}