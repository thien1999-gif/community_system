package edu.fa.repository;

import java.util.List;

import edu.fa.dto.CustomCourse;
import edu.fa.dto.TrainerRating;
import edu.fa.model.Trainee;

public interface TrainerRepositoryCustom {

	List<CustomCourse> getTraineeCourseBySubject(String email, int subjectID, int curPage);
	
	int countTraineeCourseBySubject(String email, int subjectID);
	
	boolean saveNewTrainerRequest(String trainerID, int courseID);
	
	List<CustomCourse> getTrainerRequestCourseList(String email, int subjectID, int curPage);
	
	int countTrainerRequestCourse(String email, int subjectID);
	
	boolean cancelTrainerRequest(String trainerID, int courseID);
	
	List<CustomCourse> getTrainerTeachingCourseList(String email, int subjectID, int curPage);
	
	int countTrainerTeachingCourse(String email, int subjectID);
	
	boolean endTeachingCourse(String trainerID, int courseID);
	
	List<CustomCourse> getTraineeRequestCourse(String email, int subjectID, int curPage);

	int countTraineeRequestCourse(String email, int subjectID);

	boolean denyTraineeRequest(String trainerID, int courseID);
	
	boolean acceptTraineeRequest(String trainerID, int courseID);

	String sumRatingScoreByTrainerID(String trainerID);
	
	Trainee getTraineeProfile(String traineeID);

	List<CustomCourse> getEndedCourse(String email, int subjectID, int curPage);

	int countEndedCourse(String email, int subjectID);
	public List<TrainerRating> getTopTrainer();
	boolean existTrainerRating(String trainerID, String traineeID);
	boolean existCourseOfTrainer(String trainerID,int  dayInWeek, int timeInDay); 

}
