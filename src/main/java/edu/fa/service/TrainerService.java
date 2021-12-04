package edu.fa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.dto.CustomCourse;
import edu.fa.dto.TrainerRating;
import edu.fa.model.Subject;
import edu.fa.model.Trainee;
import edu.fa.model.Trainer;
import edu.fa.repository.SubjectRepository;
import edu.fa.repository.TrainerRepository;

@Service
public class TrainerService {
	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private SubjectRepository trainerSubjectRepository;

//get subject list.================================================
	public List<Subject> getSubjectList(String email) {
		return trainerSubjectRepository.findByTrainerID(email);
	}

//=================================================================
//get trainee course.================================================================================
	public List<CustomCourse> getTraineeCourseBySubject(String email, int subjectID, int curPage) {
		return trainerRepository.getTraineeCourseBySubject(email, subjectID, curPage);
	}

	//
	public int countTraineeCourseBySubject(String email, int subjectID) {
		return trainerRepository.countTraineeCourseBySubject(email, subjectID);
	}
//===================================================================================================

//send request to trainee ========================================================
	public boolean sendRequestToTraineeCourse(String trainerID, int courseID) {
		return trainerRepository.saveNewTrainerRequest(trainerID, courseID);
	}

//================================================================================
	public List<CustomCourse> getTrainerRequestCourse(String email, int subjectID, int curPage) {
		return trainerRepository.getTrainerRequestCourseList(email, subjectID, curPage);
	}

	public int countTrainerRequestCourse(String email, int subjectID) {
		return trainerRepository.countTrainerRequestCourse(email, subjectID);
	}

	public boolean cancelTrainerRequest(String trainerID, int courseID) {
		return trainerRepository.cancelTrainerRequest(trainerID, courseID);
	}

	public List<CustomCourse> getTrainerTeachingCourse(String email, int subjectID, int curPage) {
		return trainerRepository.getTrainerTeachingCourseList(email, subjectID, curPage);
	}

	public int countTrainerTeachingCourse(String email, int subjectID) {
		return trainerRepository.countTrainerTeachingCourse(email, subjectID);
	}

	public boolean endTeachingCourse(String trainerID, int courseID) {
		return trainerRepository.endTeachingCourse(trainerID, courseID);
	}

	public String sumRatingScoreByTrainerID(String trainerID) {
		return trainerRepository.sumRatingScoreByTrainerID(trainerID);
	}

	public List<CustomCourse> getTraineeRequestCourse(String email, int subjectID, int curPage) {
		return trainerRepository.getTraineeRequestCourse(email, subjectID, curPage);
	}

	public int countTraineeRequestCourse(String email, int subjectID) {
		return trainerRepository.countTraineeRequestCourse(email, subjectID);
	}

	public boolean denyTraineeRequest(String trainerID, int courseID) {
		return trainerRepository.denyTraineeRequest(trainerID, courseID);
	}

	public Trainee viewTraineeProfile(String traineeID) {
		return trainerRepository.getTraineeProfile(traineeID);
	}

	public List<CustomCourse> getTrainerEndedCourse(String email, int subjectID, int curPage) {
		return trainerRepository.getEndedCourse(email, subjectID, curPage);
	}

	public int countTrainerEndedCourse(String email, int subjectID) {
		return trainerRepository.countEndedCourse(email, subjectID);
	}

	public boolean accpetTraineeRequest(String trainerID, int courseID) {
		return trainerRepository.acceptTraineeRequest(trainerID, courseID);
	}

	public Trainer getProfile(String email) {
		return trainerRepository.findOne(email);
	}

	public List<Trainer> getListEmail(String keyword) {
		return trainerRepository.searchEmailByKeyword(keyword);
	}

	public List<Trainer> getListEmail1(String keyword) {
		return trainerRepository.searchEmailByKeyword1(keyword);
	}

	public Trainer updateProfile(Trainer trainer) {

		return trainerRepository.save(trainer);
	}

	public void deleteShaProfile(String shaEmail) {
		trainerRepository.delete(shaEmail);
	}

	public List<Trainer> getListByStatus() {
		return trainerRepository.getListByStatus();
	}

	public boolean checkEmai(String email) {
		System.out.println("vao check mail");
		return trainerRepository.exists(email);
	}

	public void saveTeacher(Trainer trainer) {
		// TODO Auto-generated method stub
		trainerRepository.save(trainer);
	}

	public List<Trainer> getListTrainerByCourseID(int courseID) {
		return trainerRepository.getListTrainerByCourseID(courseID);
	}

	public void deleteTeacherAndCourseFromTrainerRequest(int courseID, String trainerID) {
		trainerRepository.deleteTeacherAndCourseFromTrainerRequest(courseID, trainerID);
	}

	public List<Trainer> getListTrainer() {
		return trainerRepository.getListTrainerByEnable();
	}

	public void deleteTrainerByAdminSetEnable(String email) {
		trainerRepository.deleteTrainerByAdminSetEnable(email);
	}

	public void insert(String trainerID, String traineeID, double ratingScore) {
		trainerRepository.insertTrainerRating(ratingScore, trainerID, traineeID);
	}

	public int selectRating(String traineeID, String trainerID) {
		// TODO Auto-generated method stub
		System.out.println("vao trainer service");
		return trainerRepository.selectRatingByTrainerEmailAndTraineeEmail(trainerID, traineeID);
	}

	public List<Trainer> suggestTrainer(double longitude, double latitude, int subjectID, int dayInWeek, int timeInDay) {
		return trainerRepository.suggestTrainer(longitude, latitude, subjectID , dayInWeek , timeInDay);
	}

	public void insertTrainerSubject(String email, int subjectID) {
		trainerRepository.insertTrainerSubject(email, subjectID);
	}

	public List<TrainerRating> getTopTrainer() {
		return trainerRepository.getTopTrainer();
	}

	public List<Trainer> getBlackListTrainer() {
		return trainerRepository.getBlackListTrainer();
	}

	public void unBanTrainer(String id) {
		trainerRepository.unBanTrainer(id);
	}
	public void insertTrainerRating(String trainerID, String traineeID) {
		trainerRepository.addTrainerRating(trainerID, traineeID, 0);
	}
	public boolean existTraierRating(String trainerID, String traineeID) {
		return trainerRepository.existTrainerRating(trainerID, traineeID);
	}
	public boolean checkTrainerTeaching(String trainerID, int dayInWeek, int timeInDay) {
		return trainerRepository.existCourseOfTrainer(trainerID, dayInWeek, timeInDay);
	}
}
