package edu.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fa.model.Users;

@Repository
public interface UserAuthenticateRepository extends JpaRepository<Users, String>{
	
	Users findOneByUsername(String email);
	
}
