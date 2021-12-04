package edu.fa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.fa.constant.Constant;
import edu.fa.dto.District;
import edu.fa.dto.Province;
import edu.fa.dto.Village;
import edu.fa.dto.Ward;
import edu.fa.model.Course;
import edu.fa.model.Trainer;

@Repository
@Transactional(readOnly = false)
public class TraineeRepositoryImpl implements TraineeRepositoryCustom{

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;
    
    private String users = "users";    
    private String trainee = "trainee";
    private String trainer = "trainer";
    private String course = "course";
    @SuppressWarnings("unused")
	private String subject = "subject";
    private String trainerSubject = "trainer_subject";
    private String trainerRating = "trainer_rating";
    @SuppressWarnings("unused")
	private String trainerRequest = "trainer_request";
    
    private String getBusyTrainer = " SELECT DISTINCT " + 
			    		"   CASE WHEN COUNT("+course+".trainerID) < 6 THEN 'Empty' ELSE "+course+".trainerID END as trainerID " + 
			    		"	FROM "+course+", "+trainer+", "+users+" " + 
			    		"	WHERE "+trainer+".email = "+course+".trainerID " + 
			    		"	AND "+course+".status = " + Constant.COURSE_STATUS_STARTED +
			    		"   AND "+users+".username = "+trainer+".email " + 
			    		"   AND "+users+".enabled = " + Constant.ACCOUNT_ACTIVE_STATUS +
			    		"   GROUP BY "+course+".trainerID ";
    
    private String getTrainerProfile = " WHERE "+trainer+".email != ALL ( " + 
    		getBusyTrainer + 
    		" ) " + 
    		" AND "+trainer+".email = ratingList.email " + 
    		" AND "+trainee+".email = :email " + 
    		" AND "+trainer+".email = "+trainerSubject+".trainerID " + 
    		" AND users.username = "+trainer+".email " + 
    		" AND users.enabled = " + Constant.ACCOUNT_ACTIVE_STATUS + 
    		" AND "+trainerSubject+".subjectID = CASE WHEN :subjectID = 0 THEN "+trainerSubject+".subjectID ELSE :subjectID END " + 
    		" AND (pow("+trainer+".latitude - "+trainee+".latitude,2)+pow("+trainer+".longitude - "+trainee+".longitude,2)) <= 0.0064 " + 
    		" ORDER BY ratingList.score DESC ";
    private String getTrainerProfile1 = " WHERE trainer.verify = 1 AND "+trainer+".email != ALL ( " + 
    		getBusyTrainer + 
    		" ) " + 
    		" AND "+trainer+".email = ratingList.email " + 
    		" AND "+trainee+".email = :email " + 
    		" AND "+trainer+".email = "+trainerSubject+".trainerID " + 
    		" AND users.username = "+trainer+".email " + 
    		" AND users.enabled = " + Constant.ACCOUNT_ACTIVE_STATUS + 
    		" AND "+trainerSubject+".subjectID = CASE WHEN :subjectID = 0 THEN "+trainerSubject+".subjectID ELSE :subjectID END " + 
    		" AND (pow("+trainer+".latitude - "+trainee+".latitude,2)+pow("+trainer+".longitude - "+trainee+".longitude,2)) <= 0.0064 " + 
    		" ORDER BY ratingList.score DESC ";
    
    private String getTrainerRating = " SELECT "+trainer+".email, CASE WHEN "+trainerRating+".ratingScore is null THEN -1 ELSE AVG("+trainerRating+".ratingScore) END as score " 
    						+ " FROM "+trainer+" "
    						+ " LEFT JOIN "+trainerRating+" " 
    						+ " ON "+trainerRating+".trainerID = "+trainer+".email "
    						+ " GROUP BY "+trainer+".email ";
    
    private String getPage = 
    		" LIMIT "+Constant.TOTAL_CONTENT_PER_PAGE+" OFFSET :offset ";
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Trainer> getTrainerProfileBySubject(String email,int subjectID, int curPage) {
		String sql = " SELECT DISTINCT "+trainer+".* " + 
				" FROM "+trainer+", "+trainee+", "+trainerSubject+", "+users+", ( "+getTrainerRating+" ) as ratingList "
				+getTrainerProfile1+  getPage+" ;";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Trainer.class))
				.setParameter("subjectID", subjectID)
				.setParameter("email", email)
        		.setParameter("offset", (curPage-1)*Constant.TOTAL_CONTENT_PER_PAGE).list();
	}

	@Override
	public int countTrainerProfileBySubject(String email, int subjectID) {
		String sql = " SELECT COUNT(email) " + 
				" FROM ( "
				+ " SELECT DISTINCT "+trainer+".email " +
				" FROM "+trainer+", "+trainee+", "+trainerSubject+", "+users+", ( "+getTrainerRating+" ) as ratingList "
				+getTrainerProfile+" "
				+ ") as list ;";
			String count = entityManager.createNativeQuery(sql).unwrap(Query.class)
					.setParameter("subjectID", subjectID)
					.setParameter("email", email).list().get(0)+"";
			return (int)Math.ceil((Double.parseDouble(count)/Constant.TOTAL_CONTENT_PER_PAGE));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Province> getListProvince() {
		String sql = "select * from province";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Province.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> getListDistrict() {
		String sql = "select * from district";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(District.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ward> getListWard() {
		String sql = "select * from ward";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Ward.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getListVillage() {
		String sql = "select * from village";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Village.class)).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<District> getListDistrictByProvinceId(String provinceID) {
		String sql = "select * from district where provinceid = :provinceid";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(District.class)).setParameter("provinceid", provinceID).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ward> getListWardByDistrictId(String districtId) {
		String sql = "select * from ward where districtid = :districtid";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Ward.class)).setParameter("districtid", districtId).list();
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getListVillageByWardId(String wardid) {
		String sql = "select * from village where wardid = :wardid";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Village.class)).setParameter("wardid", wardid).list();
	
	}

	@Override
	public boolean existCourseOfTrainee(String traineeID, int dayInWeek, int timeInDay) {
		String sql = "select * from course as c where c.traineeID = :traineeID and c.dayInWeek = :dayInWeek and c.timeInDay = :timeInDay and (c.status = 0 or c.status = 1 or c.status = 2) " ;
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Course.class)).setParameter("traineeID", traineeID).
				setParameter("timeInDay", timeInDay).setParameter("dayInWeek", dayInWeek).list().size() > 0;
	}
	
	


	
}
