package edu.fa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Trainer_Request")
public class TrainerRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "trainerID", length=200)
	private String trainerID;
	
	@Id
	@Column(name = "courseID", precision = 8)
	private int courseID;
	
	//map by trainer and course=====================================================
		@ManyToOne(optional = false)
	    @JoinColumn(name = "trainerID", insertable = false, updatable = false, referencedColumnName = "email")
		private Trainer trainer;
		
		@ManyToOne(optional = false)
	    @JoinColumn(name = "courseID", insertable = false, updatable = false)
		private Course course;
	//================================================================================

		public String getTrainerID() {
			return trainerID;
		}

		public void setTrainerID(String trainerID) {
			this.trainerID = trainerID;
		}

		public int getCourseID() {
			return courseID;
		}

		public void setCourseID(int courseID) {
			this.courseID = courseID;
		}

		public Trainer getTrainer() {
			return trainer;
		}

		public void setTrainer(Trainer trainer) {
			this.trainer = trainer;
		}

		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}

		public TrainerRequest() {
			super();
		}

}

