package edu.fa.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {
	private MultipartFile profileImage;
	private MultipartFile profileImageTeacher;
	private MultipartFile profileCardID;
	private MultipartFile profileCardIDBehiden;
	public MultipartFile getProfileCardIDBehiden() {
		return profileCardIDBehiden;
	}

	public void setProfileCardIDBehiden(MultipartFile profileCardIDBehiden) {
		this.profileCardIDBehiden = profileCardIDBehiden;
	}

	private MultipartFile profileUploadCV;

	public MultipartFile getProfileImageTeacher() {
		return profileImageTeacher;
	}

	public void setProfileImageTeacher(MultipartFile profileImageTeacher) {
		this.profileImageTeacher = profileImageTeacher;
	}

	public MultipartFile getProfileCardID() {
		return profileCardID;
	}

	public void setProfileCardID(MultipartFile profileCardID) {
		this.profileCardID = profileCardID;
	}

	public MultipartFile getProfileUploadCV() {
		return profileUploadCV;
	}

	public void setProfileUploadCV(MultipartFile profileUploadCV) {
		this.profileUploadCV = profileUploadCV;
	}

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}

	public FileDTO() {
		super();
	}

}
