package edu.fa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Subject")
public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subjectID", precision = 8)
	private int subjectID;

	@Column(name = "subject", length = 20)
	private String subject;
//map to course=================================================================
	@OneToMany(mappedBy = "subjectID", cascade = CascadeType.ALL)
	private Set<Course> course;
//==============================================================================

//map to trainer subject========================================================
	@OneToMany(mappedBy = "subjectID", cascade = CascadeType.ALL)
	private Set<TrainerSubject> trainerSubjects;

//==============================================================================
	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

	public Set<TrainerSubject> getTrainerSubjects() {
		return trainerSubjects;
	}

	public void setTrainerSubjects(Set<TrainerSubject> trainerSubjects) {
		this.trainerSubjects = trainerSubjects;
	}

	public Subject(int subjectID, String subject) {
		super();
		this.subjectID = subjectID;
		this.subject = subject;
	}

	public Subject(String subject) {
		super();
		this.subject = subject;
	}

	public Subject() {
		super();
	}

}
