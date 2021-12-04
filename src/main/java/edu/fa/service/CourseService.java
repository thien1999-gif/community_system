package edu.fa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.model.Course;
import edu.fa.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository couRes;
		
	public List<Course> searchCoursesByTraineeID(String traineeID){
		return couRes.findByTraineeID(traineeID);
	}
	public Course createCourse(Course course) {
		 return	couRes.save(course);
		}
	public List<Course> searchCoursesByStatus(int status){
		return couRes.findByStatus(status);
	}
	public Course getCourse(int id) {
		return couRes.findOne(id);
	}
	public void deleteCourse(int id) {
		couRes.delete(id);
	}
	public List<Course> findByStatusAndTraineeID(int status, String traineeID){
		return couRes.findByStatusAndTraineeID(status, traineeID);
	}
	public void deleteCourseFromTrainerRequest(int courseID) {
		couRes.deleteCourseIDFromTrainerRequest(courseID);
	}	
	
	public List<Course> selectStatusByTraineeID(String emailTrainee) {
		// TODO Auto-generated method stub
		return couRes.selectStatus(emailTrainee);
	}

	public List<Course> selectCourseFinished(String emailTrainee) {
		// TODO Auto-generated method stub
		return couRes.selectCourseFinished(emailTrainee);
	}

	public List<Course> selectCourseBegin(String emailTrainee) {
		// TODO Auto-generated method stub
		return couRes.selectCourseBegin(emailTrainee);
	}

	public void updateCourseStatus(Course course) {
		couRes.save(course);

	}

	public int countTimes(String trainerID,String traineeID) {
		// TODO Auto-generated method stub
		return couRes.countTimesStudy(trainerID,traineeID);
	}
	
	public List<Course> selectNewPost() {
		// TODO Auto-generated method stub
		return couRes.selectNewPost();
	}
	public List<Course> selectPostWaiting(String emailTrainee) {
		// TODO Auto-generated method stub
		return couRes.selectCourseWaiting(emailTrainee);
	}
}
