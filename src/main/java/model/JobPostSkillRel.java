package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the JOB_POST_SKILL_REL database table.
 * 
 */
@Entity
@Table(name="JOB_POST_SKILL_REL")
@NamedQuery(name="JobPostSkillRel.findAll", query="SELECT j FROM JobPostSkillRel j")
public class JobPostSkillRel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_POST_SKILL_REL_ID")
	private Long jobPostSkillRelId;

	//bi-directional many-to-one association to JobPost
	@ManyToOne
	@JoinColumn(name="JOB_POST_ID")
	private JobPost jobPost;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="SKILL_ID")
	private Skill skill;

	public JobPostSkillRel() {
	}

	public Long getJobPostSkillRelId() {
		return this.jobPostSkillRelId;
	}

	public void setJobPostSkillRelId(Long jobPostSkillRelId) {
		this.jobPostSkillRelId = jobPostSkillRelId;
	}

	public JobPost getJobPost() {
		return this.jobPost;
	}

	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}