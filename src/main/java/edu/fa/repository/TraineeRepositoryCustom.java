package edu.fa.repository;

import java.util.List;

import edu.fa.dto.District;
import edu.fa.dto.Province;
import edu.fa.dto.Village;
import edu.fa.dto.Ward;
import edu.fa.model.Trainer;

public interface TraineeRepositoryCustom {

	List<Trainer> getTrainerProfileBySubject(String email, int subjectID, int curPage);

	int countTrainerProfileBySubject(String email, int subjectID);
	List<Province> getListProvince();
	List<District> getListDistrict();
	List<Ward> getListWard();
	List<Village> getListVillage();
	
	List<District> getListDistrictByProvinceId(String provinceID);
	List<Ward> getListWardByDistrictId(String districtId);
	List<Village> getListVillageByWardId(String wardid);
	 boolean existCourseOfTrainee(String traineeID, int dayInWeek, int timeInDay);
}
