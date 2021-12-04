package edu.fa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Trainer_Rating")
public class TrainerRating implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "trainerID", length=200)
	private String trainerID;
	
	@Id
	@Column(name = "traineeID", length=200)
	private String traineeID;
	
	@Column(name = "ratingScore", precision = 2)
	private double ratingScore;	
	
//map by trainer and trainee=====================================================
		@ManyToOne(optional = false)
	    @JoinColumn(name = "trainerID", insertable = false, updatable = false, referencedColumnName = "email")
		private Trainer trainer;
		
		@ManyToOne(optional = false)
	    @JoinColumn(name = "traineeID", insertable = false, updatable = false, referencedColumnName = "email")
		private Trainee trainee;
//================================================================================

		public String getTrainerID() {
			return trainerID;
		}

		public void setTrainerID(String trainerID) {
			this.trainerID = trainerID;
		}

		public String getTraineeID() {
			return traineeID;
		}

		public void setTraineeID(String traineeID) {
			this.traineeID = traineeID;
		}

		public Trainer getTrainer() {
			return trainer;
		}

		public void setTrainer(Trainer trainer) {
			this.trainer = trainer;
		}

		public Trainee getTrainee() {
			return trainee;
		}

		public void setTrainee(Trainee trainee) {
			this.trainee = trainee;
		}

		public double getRatingScore() {
			return ratingScore;
		}
		
		public void setRatingScore(double ratingScore) {
			this.ratingScore = ratingScore;
		}

		public TrainerRating() {
			super();
		}

		public TrainerRating(String trainerID, String traineeID, double ratingScore) {
			super();
			this.trainerID = trainerID;
			this.traineeID = traineeID;
			this.ratingScore = ratingScore;
		}
		
	
}
