package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the LOGIN_USER database table.
 * 
 */
@Entity
@Table(name="LOGIN_USER")
@NamedQuery(name="LoginUser.findAll", query="SELECT l FROM LoginUser l")
public class LoginUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOGIN_USER_ID")
	private Long loginUserId;

	@ManyToOne
	@JoinColumn(name="LOGIN_USER_ROLE_ID")
	private LoginUserRole role;

	private String password;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private Company company;

	//bi-directional many-to-one association to JobSeeker
	@ManyToOne
	@JoinColumn(name="JOB_SEEKER_ID")
	private JobSeeker jobSeeker;

	//bi-directional one-to-one association to LoginUser

	public LoginUser() {
	}

	public Long getLoginUserId() {
		return this.loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public LoginUserRole getRole() {
		return this.role;
	}

	public void setRole(LoginUserRole role) {
		this.role = role;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public JobSeeker getJobSeeker() {
		return this.jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

}