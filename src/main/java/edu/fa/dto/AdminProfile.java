package edu.fa.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class AdminProfile {
	private String email;
	private String password;
	private String fullName;
	private Date dayOfBirth;
	private MultipartFile profileImage;
	private String avatar;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getDayOfBirth() {
		return dayOfBirth;
	}
	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	public MultipartFile getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}
