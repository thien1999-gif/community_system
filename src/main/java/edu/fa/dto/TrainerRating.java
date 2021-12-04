package edu.fa.dto;

import java.io.Serializable;
import java.util.Date;

public class TrainerRating implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String email, address, avatar;
	Date dayOfBirth;
	String description, experience, facebook, fullName;
	int gender, graduationYear;
	String idCard;
	double latitude, longitude;
	String phone;
	String resumeLink;
	int salaryPerHour;
	String zalo;
	int verify;
	double rating;
	String codeAddress;
	
	
	
	public String getCodeaddress() {
		return codeAddress;
	}

	public void setCodeaddress(String codeaddress) {
		this.codeAddress = codeaddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResumeLink() {
		return resumeLink;
	}

	public void setResumeLink(String resumeLink) {
		this.resumeLink = resumeLink;
	}

	public int getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(int salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

	public String getZalo() {
		return zalo;
	}

	public void setZalo(String zalo) {
		this.zalo = zalo;
	}

	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public TrainerRating(String email, String address, String avatar, Date dayOfBirth, String description,
			String experience, String facebook, String fullName, int gender, int graduationYear, String idCard,
			double latitude, double longitude, String phone, String resumeLink, int salaryPerHour, String zalo,
			int verify, double rating,String codeAddress) {
		super();
		this.email = email;
		this.address = address;
		this.avatar = avatar;
		this.dayOfBirth = dayOfBirth;
		this.description = description;
		this.experience = experience;
		this.facebook = facebook;
		this.fullName = fullName;
		this.gender = gender;
		this.graduationYear = graduationYear;
		this.idCard = idCard;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phone = phone;
		this.resumeLink = resumeLink;
		this.salaryPerHour = salaryPerHour;
		this.zalo = zalo;
		this.verify = verify;
		this.rating = rating;
		this.codeAddress = codeAddress;
	}

	public TrainerRating() {
		super();
	}

}
