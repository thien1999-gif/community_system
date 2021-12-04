package edu.fa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.dto.District;
import edu.fa.dto.Province;
import edu.fa.dto.Village;
import edu.fa.dto.Ward;
import edu.fa.model.Subject;
import edu.fa.model.Trainee;
import edu.fa.model.Trainer;
import edu.fa.repository.SubjectRepository;
import edu.fa.repository.TraineeRepository;
import edu.fa.repository.TrainerRepository;

@Service
public class TraineeService {
	@Autowired
	private TraineeRepository traineeResRepository;

	@Autowired
	private SubjectRepository trainerSubjectRepository;

	@Autowired
	private TrainerRepository trainerRepository;

	public Trainee findTraineeByEmail(String email) {
		return traineeResRepository.findOneByEmail(email);
	}

	public void saveStudent(Trainee trainee) {
		// TODO Auto-generated method stub
		traineeResRepository.save(trainee);
	}

	public boolean checkEmai(String email) {
		return traineeResRepository.exists(email);
	}

//get subject list.================================================
	public List<Subject> getSubjectList(String email) {
		return trainerSubjectRepository.findAll();
	}
//=================================================================

	public List<Trainer> getTrainerProfileList(String email, int subjectID, int curPage) {
		return traineeResRepository.getTrainerProfileBySubject(email, subjectID, curPage);
	}

	public int countTrainerProfile(String email, int subjectID) {
		return traineeResRepository.countTrainerProfileBySubject(email, subjectID);
	}

	public Trainee getInfoTrainee(String email) {
		return traineeResRepository.findByEmail(email);
	}

	public Trainee UpdateProfile(Trainee trainee) {
		return traineeResRepository.save(trainee);
	}

	public List<Trainee> getListTrainee() {
		return traineeResRepository.getListTraineeByEnable();
	}

	public void deleteTraineeByAdminSetEnable(String email) {
		traineeResRepository.deleteTraineeByAdminSetEnable(email);
	}

	public List<Trainee> getListEmail(String keyword) {
		return traineeResRepository.searchEmailByKeyword(keyword);
	}

	public Trainer findByEmailTrainer(String trainerID) {
		// TODO Auto-generated method stub
		return trainerRepository.findOne(trainerID);
	}

	public Trainee selectInfo(String username) {
		// TODO Auto-generated method stub
		return traineeResRepository.findOne(username);
	}

	public List<Trainee> getBlackListTrainer() {
		return traineeResRepository.getBlackListTrainee();
	}

	public void unBanTrainer(String id) {
		trainerRepository.unBanTrainer(id);
	}
	public List<Province> getListProvince(){
		return traineeResRepository.getListProvince();
	}
	public List<District> getListDistrict(){
		return traineeResRepository.getListDistrict();
	}
	public List<Ward> getListWard(){
		return traineeResRepository.getListWard();
	}	
	public List<Village> getListVillage(){
		return traineeResRepository.getListVillage();
	}
	public List<District> getListDistrictByProvinceId(String provinceID){
		return traineeResRepository.getListDistrictByProvinceId(provinceID);
	}
	public List<Ward> getListWardByDistrictId(String districtid){
		return traineeResRepository.getListWardByDistrictId(districtid);
	}
	public List<Village> getListVillageByWardId(String wardid){
		return traineeResRepository.getListVillageByWardId(wardid);
	}
	public boolean existCourseOfTrainee(String traineeID, int dayInWeek, int timeInDay) {
		return traineeResRepository.existCourseOfTrainee(traineeID, dayInWeek, timeInDay);
	}

}
