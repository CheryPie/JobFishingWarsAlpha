package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LOGIN_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="LOGIN_USER_ROLE")
@NamedQuery(name="LoginUserRole.findAll", query="SELECT l FROM LoginUserRole l")
public class LoginUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOGIN_USER_ROLE_ID")
	private Long loginUserRoleId;

	private String name;

	public LoginUserRole() {
	}

	public Long getLoginUserRoleId() {
		return this.loginUserRoleId;
	}

	public void setLoginUserRoleId(Long loginUserRoleId) {
		this.loginUserRoleId = loginUserRoleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}