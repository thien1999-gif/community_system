package edu.fa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username", length = 200)
	private String username;
	
	@Column(name = "password", length = 100)
	private String password;
	
	@Column(name = "enabled", precision = 1)
	private int enabled;

	@Column(name = "reset_token")
	private String resetToken;

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
// map to role=============================================================
	@OneToMany(mappedBy = "username", cascade = CascadeType.ALL)
	private Set<Authorities> authorities;
//=========================================================================

	@OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
	private Set<Trainer> trainer;
	
	@OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
	private Set<Trainee> trainee;
	
	@OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
	private Set<Admin> admin;
	
	public Set<Trainee> getTrainee() {
		return trainee;
	}

	public void setTrainee(Set<Trainee> trainee) {
		this.trainee = trainee;
	}

	public Set<Trainer> getTrainer() {
		return trainer;
	}

	public void setTrainer(Set<Trainer> trainer) {
		this.trainer = trainer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Admin> getAdmin() {
		return admin;
	}

	public void setAdmin(Set<Admin> admin) {
		this.admin = admin;
	}

	public Users() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}
	
}
