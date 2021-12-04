package edu.fa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Trainer")
public class Trainer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "email", length = 200)
	private String email;
	
	@Column(name = "dayOfBirth")
	private Date dayOfBirth;
	
	@Column(name = "gender", precision=1)
	private int gender;
	
	@Column(name = "fullName", length = 45)
	private String fullName;
	
	@Column(name = "avatar", length = 200)
	private String avatar;
	

	
	@Column(name = "address", length = 100)
	private String address;
	
	@Column(name = "latitude", precision=3, scale=6)
	private double latitude;
	
	@Column(name = "longitude", precision=3, scale=6)
	private double longitude;
	
	@Column(name = "zalo", length = 200)
	private String zalo;
	
	@Column(name = "facebook", length = 200)
	private String facebook;
	
	@Column(name = "phone", length = 12)
	private String phone;
	
	@Column(name = "idCard", length = 2000)
	private String idCard;
	
	@Column(name = "resumeLink", length = 200)
	private String resumeLink;
	
	@Column(name = "description", length = 400)
	private String description;
	
	@Column(name = "salaryPerHour", precision = 9)
	private int salaryPerHour;
	
	@Column(name = "experience", length = 100)
	private String experience;
	
	@Column(name = "graduationYear", precision = 4)
	private int graduationYear;
	
	@Column(name = "verify", precision = 4)
	private int verify;
	
	@Column(name = "codeAddress", length = 100)
	private String codeAddress;	
	
	
	
	public String getCodeAddress() {
		return codeAddress;
	}

	public void setCodeAddress(String codeAddress) {
		this.codeAddress = codeAddress;
	}

	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
	}
	

	@Transient
	private MultipartFile profileImage;
	
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

	@Transient
	MultipartFile profileCardID;
	
	@Transient
	MultipartFile profileCardIDBehiden;

	public MultipartFile getProfileCardIDBehiden() {
		return profileCardIDBehiden;
	}

	public void setProfileCardIDBehiden(MultipartFile profileCardIDBehiden) {
		this.profileCardIDBehiden = profileCardIDBehiden;
	}

	@Transient
	MultipartFile profileUploadCV;
//map to users==================================================================
	@ManyToOne
    @JoinColumn(name = "email", insertable = false, updatable = false)
	private Users users;
//================================================================================

//map to trainer Subject==========================================================
	@OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL)
	private Set<TrainerSubject> TrainerSubject;
//================================================================================
	
//map to trainer request==========================================================
	@OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL)
	private Set<TrainerRequest> trainerRequest;
//================================================================================

//map to trainer rating==================================================================
	@OneToMany(mappedBy = "trainerID", cascade = CascadeType.ALL)
	private Set<TrainerRating> trainerRating;
//================================================================================
		
	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getZalo() {
		return zalo;
	}

	public void setZalo(String zalo) {
		this.zalo = zalo;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getResumeLink() {
		return resumeLink;
	}

	public void setResumeLink(String resumeLink) {
		this.resumeLink = resumeLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(int salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Set<TrainerRating> getTrainerRating() {
		return trainerRating;
	}

	public void setTrainerRating(Set<TrainerRating> trainerRating) {
		this.trainerRating = trainerRating;
	}

	public Set<TrainerSubject> getTrainerSubject() {
		return TrainerSubject;
	}

	public void setTrainerSubject(Set<TrainerSubject> trainerSubject) {
		TrainerSubject = trainerSubject;
	}

	public Set<TrainerRequest> getTrainerRequest() {
		return trainerRequest;
	}

	public void setTrainerRequest(Set<TrainerRequest> trainerRequest) {
		this.trainerRequest = trainerRequest;
	}

	public Trainer() {
		super();
	}

	@Override
	public String toString() {
		return "Trainer [email=" + email + ", dayOfBirth=" + dayOfBirth + ", gender=" + gender + ", fullName="
				+ fullName + ", avatar=" + avatar + ", address=" + address + ", latitude=" + latitude + ", longitude="
				+ longitude + ", zalo=" + zalo + ", facebook=" + facebook + ", phone=" + phone + ", idCard=" + idCard
				+ ", resumeLink=" + resumeLink + ", description=" + description + ", salaryPerHour=" + salaryPerHour
				+ ", experience=" + experience + ", graduationYear=" + graduationYear + ", verify=" + verify
				+ ", profileImage=" + profileImage + ", profileCardID=" + profileCardID + ", profileCardIDBehiden="
				+ profileCardIDBehiden + ", profileUploadCV=" + profileUploadCV + ", users=" + users
				+ ", TrainerSubject="  + ", trainerRequest="  + ", trainerRating="
				 + "]";
	} 
	
	
}
