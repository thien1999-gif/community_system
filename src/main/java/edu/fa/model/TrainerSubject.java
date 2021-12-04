package edu.fa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Trainer_Subject")
public class TrainerSubject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "trainerID", length=200)
	private String trainerID;
	
	@Id
	@Column(name = "subjectID", precision = 8)
	private int subjectID;
	
	
//map by trainer and subject=====================================================
	@ManyToOne(optional = false)
    @JoinColumn(name = "trainerID", insertable = false, updatable = false, referencedColumnName = "email")
	private Trainer trainer;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "subjectID", insertable = false, updatable = false)
	private Subject subject;
//================================================================================

	public String getTrainerID() {
		return trainerID;
	}

	public void setTrainerID(String trainerID) {
		this.trainerID = trainerID;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public TrainerSubject() {
		super();
	}
	
	
}
