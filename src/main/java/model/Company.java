package model;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.List;


/**
 * The persistent class for the COMPANY database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@Column(name="COMPANY_ID")
	private Long companyId;

	@Expose
	private String name;

	//bi-directional many-to-one association to JobPost
	@OneToMany(mappedBy="company")
	@Expose
	private List<JobPost> jobPosts;

	//bi-directional many-to-one association to LoginUser
	@OneToMany(mappedBy="company")
	private List<LoginUser> loginUsers;

	public Company() {
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JobPost> getJobPosts() {
		return this.jobPosts;
	}

	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}

	public JobPost addJobPost(JobPost jobPost) {
		getJobPosts().add(jobPost);
		jobPost.setCompany(this);

		return jobPost;
	}

	public JobPost removeJobPost(JobPost jobPost) {
		getJobPosts().remove(jobPost);
		jobPost.setCompany(null);

		return jobPost;
	}

	public List<LoginUser> getLoginUsers() {
		return this.loginUsers;
	}

	public void setLoginUsers(List<LoginUser> loginUsers) {
		this.loginUsers = loginUsers;
	}

	public LoginUser addLoginUser(LoginUser loginUser) {
		getLoginUsers().add(loginUser);
		loginUser.setCompany(this);

		return loginUser;
	}

	public LoginUser removeLoginUser(LoginUser loginUser) {
		getLoginUsers().remove(loginUser);
		loginUser.setCompany(null);

		return loginUser;
	}

}