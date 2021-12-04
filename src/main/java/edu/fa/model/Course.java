package edu.fa.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "courseID", precision=8)
	private int courseID;
	
	@Column(name = "title", length = 100)
	private String title;
	
	@Column(name = "description", length = 400)
	private String description;
	
	@Column(name = "trainerID", length = 200)
	private String trainerID;	
	
	@Column(name = "traineeID", length = 200)
	private String traineeID;	
	
	@Column(name = "timeInDay", precision = 1)
	private int timeInDay;
	
	@Column(name = "dayInWeek", precision = 1)
	private int dayInWeek;
	
	@Column(name = "status", precision = 1)
	private int status;
	
	@Column(name = "subjectID", precision = 8)
	private int subjectID;
	
	@Column(name = "deposit", precision = 9)
	private int Deposit;
	
	@Column(name = "createDate")
	private Timestamp createDate;

//map to subject=================================================================
	@ManyToOne
    @JoinColumn(name = "subjectID", insertable = false, updatable = false)
	private Subject subject;
//================================================================================	

//map to trainer Request=================================================================
	@OneToMany(mappedBy = "courseID", cascade = CascadeType.ALL)
	private Set<TrainerRequest> trainerRequest;
//================================================================================	


	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
		

	public int getDeposit() {
		return Deposit;
	}

	public void setDeposit(int deposit) {
		Deposit = deposit;
	}

	public int getTimeInDay() {
		return timeInDay;
	}

	public void setTimeInDay(int timeInDay) {
		this.timeInDay = timeInDay;
	}

	public int getDayInWeek() {
		return dayInWeek;
	}

	public void setDayInWeek(int dayInWeek) {
		this.dayInWeek = dayInWeek;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Set<TrainerRequest> getTrainerRequest() {
		return trainerRequest;
	}

	public void setTrainerRequest(Set<TrainerRequest> trainerRequest) {
		this.trainerRequest = trainerRequest;
	}

	public Course() {
		super();
	}
	
	
	public Course(int courseID, String title, String description, String trainerID, String traineeID, int timeInDay,
			int dayInWeek, int status, int subjectID, int deposit, Timestamp createDate) {
		super();
		this.courseID = courseID;
		this.title = title;
		this.description = description;
		this.trainerID = trainerID;
		this.traineeID = traineeID;
		this.timeInDay = timeInDay;
		this.dayInWeek = dayInWeek;
		this.status = status;
		this.subjectID = subjectID;
		Deposit = deposit;
		this.createDate = createDate;
	}

//	@Override
//	public String toString() {
//		return "Course [courseID=" + courseID + ", title=" + title + ", description=" + description + ", trainerID="
//				+ trainerID + ", traineeID=" + traineeID + ", timeInDay=" + timeInDay + ", dayInWeek=" + dayInWeek
//				+ ", status=" + status + ", subjectID=" + subjectID + ", createDate=" + createDate + "]";
//	}
	
	
}
