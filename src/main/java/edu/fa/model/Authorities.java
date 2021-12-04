package edu.fa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username", length = 200)
	private String username;
	
	@Column(name = "authority", length = 50)
	private String Authority;
	
	@ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
	private Users users;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return Authority;
	}

	public void setAuthority(String authority) {
		Authority = authority;
	}

	public Authorities() {
		super();
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
