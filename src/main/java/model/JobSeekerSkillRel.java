package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


/**
 * The persistent class for the JOB_SEEKER_SKILL_REL database table.
 * 
 */
@Entity
@Table(name="JOB_SEEKER_SKILL_REL")
@NamedQuery(name="JobSeekerSkillRel.findAll", query="SELECT j FROM JobSeekerSkillRel j")
public class JobSeekerSkillRel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_SEEKER_SKILL_REL_ID")
	private Long jobSeekerSkillRelId;

	//bi-directional many-to-one association to JobSeeker
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="JOB_SEEKER_ID")
	private JobSeeker jobSeeker;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="SKILL_ID")
	@Expose
	private Skill skill;

	public JobSeekerSkillRel() {
	}

	public Long getJobSeekerSkillRelId() {
		return this.jobSeekerSkillRelId;
	}

	public void setJobSeekerSkillRelId(Long jobSeekerSkillRelId) {
		this.jobSeekerSkillRelId = jobSeekerSkillRelId;
	}

	public JobSeeker getJobSeeker() {
		return this.jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}