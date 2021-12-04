package edu.fa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fa.model.Authorities;

public interface AuthorRepository extends JpaRepository<Authorities, Serializable>{
	Authorities findOneByUsername(String username);
}
