package edu.fa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import edu.fa.model.Users;

public interface AccountRepository extends JpaRepository<Users, String>{
	@Modifying(clearAutomatically = true)     
	@Transactional
	@Query(value=" UPDATE users SET enabled = ?2 WHERE username = ?1 ; ", nativeQuery = true)
	void enableUser(String email, int status);

//	@Query(value = "SELECT * FROM users WHERE username = ?1;", nativeQuery = true)
	Optional<Users> findByUsername(String username);

//	@Query(value = "SELECT * FROM users WHERE reset_token = ?1;", nativeQuery = true)
	Optional<Users> findByResetToken(String resetToken);
}
