package edu.fa.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "email", length = 200)
	private String email;
	
	@Column(name = "dayOfBirth")
	private Date dayOfBirth;

	@Column(name = "fullName", length = 45)
	private String fullName;
	
	@Column(name = "avatar", length = 200)
	private String avatar;

	@Column(name = "phone", length = 12)
	private String phone;

//map to users==================================================================
	@ManyToOne
    @JoinColumn(name = "email", insertable = false, updatable = false)
	private Users users;
//================================================================================

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Admin() {
		super();
	}
	
	
}
