package edu.fa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import edu.fa.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	List<Course> findByTraineeID(String traineeID);
	@Query(value = "Select * from course where status = :keyword", nativeQuery = true)
	List<Course> findByStatus(@Param("keyword") int keyword);
	@Query (value ="Select * from course where status = ?1 and traineeID = ?2", nativeQuery = true )
	List<Course> findByStatusAndTraineeID( int status, String traineeID);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query (value ="delete from trainer_request where courseID = ?1 ", nativeQuery = true )
	void deleteCourseIDFromTrainerRequest( int courseID);
	
	
	@Query(value = "select * from course where traineeID = ?1 and status = 3", nativeQuery = true)
	List<Course> selectStatus(String emailTrainee);
	
	@Query(value = "select * from course where traineeID = ?1 and (status = 3 or status = 4) ", nativeQuery = true)
	List<Course> selectCourseFinished(String emailTrainee);
	
	@Query(value = "select * from course where traineeID = ?1 and status = 2", nativeQuery = true)
	List<Course> selectCourseBegin(String emailTrainee);
	
	@Query(value = "select count(*) from course where trainerID = ?1 and status = 4 and traineeID=?2", nativeQuery = true)
	int countTimesStudy(String trainerID,String traineeID);
	
	@Query(value = "select * from course where status = 1 and trainerID is null", nativeQuery = true)
	List<Course> selectNewPost();
	
	@Query(value = "select * from course where traineeID = ?1 and status = 0", nativeQuery = true)
	List<Course> selectCourseWaiting(String emailTrainee);
	
	
}
