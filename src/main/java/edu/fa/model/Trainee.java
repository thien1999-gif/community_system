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
@Table(name = "Trainee")
public class Trainee implements Serializable{

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
	
	@Column(name = "codeAddress", length = 100)
	private String codeAddress;	
	
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
	
	@Transient
	private MultipartFile profileImage;
	
//map to users==================================================================
	@ManyToOne
    @JoinColumn(name = "email", insertable = false, updatable = false)
	private Users users;
//================================================================================
	
//map to trainer rating==================================================================
	@OneToMany(mappedBy = "traineeID", cascade = CascadeType.ALL)
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	
	

	public String getCodeAddress() {
		return codeAddress;
	}

	public void setCodeAddress(String codeAddress) {
		this.codeAddress = codeAddress;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<TrainerRating> getTrainerRating() {
		return trainerRating;
	}

	public void setTrainerRating(Set<TrainerRating> trainerRating) {
		this.trainerRating = trainerRating;
	}

	public Trainee() {
		super();
	}

	
}
