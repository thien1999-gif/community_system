package edu.fa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import edu.fa.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	
	@Query(value = " SELECT b.subjectID, b.subject " + 
			" FROM trainer_subject as a, subject as b " + 
			" WHERE a.trainerID = ?1 " + 
			" AND a.subjectID = b.subjectID; ", 
			  nativeQuery = true)
	List<Subject> findByTrainerID(String email);
	
	List<Subject> findBySubjectLike(String name);
	
	Subject findBySubject(String name);
	
	Subject findBySubjectID(int id);
	
	List<Subject> findAll();
	
	@Query(value = "Select subjectID from subject", nativeQuery = true)
	List<Long> findSubjectID();

	@Query(value = "Select * from subject ORDER BY subject ASC;", nativeQuery = true)
	List<Subject> select();
	
	
	@Transactional
	@Query(value = "Select count(*) from trainer_subject as t where t.subjectID = ?1 ", nativeQuery = true)
	int checkExistSubject(int subjectID);
	@Transactional
	@Query(value = "select count(*) from course where subjectID = ?1", nativeQuery = true)
	int checkExistSubjectInCourse(int subjectID);
}
