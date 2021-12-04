package edu.fa.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import edu.fa.constant.Constant;

public class CustomCourse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int courseID;

	private String title;

	private String description;

	private String trainerID;	
	
	private String trainerName;
	
	private String trainerAvatar;

	private String traineeID;
	
	private String traineeName;
	
	private String traineeAvatar;
	
	private String address;
	
	private int timeInDay;

	private int dayInWeek;

	private int status;

	private int subjectID;
	
	private String subject;
	
	private int deposit;

	private Timestamp createDate;

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

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getTrainerAvatar() {
		return trainerAvatar;
	}

	public void setTrainerAvatar(String trainerAvatar) {
		this.trainerAvatar = trainerAvatar;
	}

	public String getTraineeID() {
		return traineeID;
	}

	public void setTraineeID(String traineeID) {
		this.traineeID = traineeID;
	}

	public String getTraineeName() {
		return traineeName;
	}

	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}

	public String getTraineeAvatar() {
		return traineeAvatar;
	}

	public void setTraineeAvatar(String traineeAvatar) {
		this.traineeAvatar = traineeAvatar;
	}

	public String getTimeInDay() {
		String result = "";
		if(timeInDay == Constant.CALENDAR_TIME_MORNING){
			result = "Morning";
		}else if(timeInDay == Constant.CALENDAR_TIME_AFTERNOON){
			result = "Afternoon";
		}else {
			result = "Evening";
		}
		return result;
	}

	public void setTimeInDay(int timeInDay) {
		this.timeInDay = timeInDay;
	}

	public String getDayInWeek() {
		String result = "";
		if(dayInWeek == Constant.CALENDAR_DAY_EVEN){
			result = "Mon-Wed-Fri";
		}else {
			result = "Tue-Thu-Sat";
		}
		return result;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public CustomCourse() {
		super();
	}

	public CustomCourse(int courseID, String title, String description, String trainerID, String trainerName,
			String trainerAvatar, String traineeID, String traineeName, String traineeAvatar, String address,
			int timeInDay, int dayInWeek, int status, int subjectID, String subject, int deposit,
			Timestamp createDate) {
		super();
		this.courseID = courseID;
		this.title = title;
		this.description = description;
		this.trainerID = trainerID;
		this.trainerName = trainerName;
		this.trainerAvatar = trainerAvatar;
		this.traineeID = traineeID;
		this.traineeName = traineeName;
		this.traineeAvatar = traineeAvatar;
		this.address = address;
		this.timeInDay = timeInDay;
		this.dayInWeek = dayInWeek;
		this.status = status;
		this.subjectID = subjectID;
		this.subject = subject;
		this.deposit = deposit;
		this.createDate = createDate;
	}

	
	public CustomCourse(int courseID, String title, String description,
			int timeInDay, int dayInWeek, int status, int subjectID, String subject, int deposit,
			Timestamp createDate) {
		super();
		this.courseID = courseID;
		this.title = title;
		this.description = description;
		this.timeInDay = timeInDay;
		this.dayInWeek = dayInWeek;
		this.status = status;
		this.subjectID = subjectID;
		this.subject = subject;
		this.deposit = deposit;
		this.createDate = createDate;
	}
}
