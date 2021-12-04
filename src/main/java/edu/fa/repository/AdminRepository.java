package edu.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.fa.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{
	Admin findOneByEmail(String email);
	
	@Query(value = "select COUNT(courseID)  from course where status = 0;", nativeQuery = true)
	int countCreatePostRequest();
	@Query(value = "select COUNT(email) from trainer where verify =0;", nativeQuery = true)
	int countRegisTrainer();
	@Query(value = "select COUNT(email) from trainer where email like '%-watting-update-%';", nativeQuery = true)
	int countUpdateInfoTrainer();
}
