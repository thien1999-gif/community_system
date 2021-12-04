package edu.fa.repository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.fa.constant.Constant;
import edu.fa.dto.CustomCourse;
import edu.fa.dto.TrainerRating;
import edu.fa.model.Course;
import edu.fa.model.Trainee;

@Repository
@Transactional(readOnly = false)
public class TrainerRepositoryImpl implements TrainerRepositoryCustom {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	private String users = "users";
	private String trainee = "trainee";
	private String trainer = "trainer";
	private String course = "course";
	private String subject = "subject";
	private String trainerSubject = "trainer_subject";
	private String trainerRating = "trainer_rating";
	private String trainerRequest = "trainer_request";

	private String getTraineeRequest = " SELECT CASE WHEN count(" + trainerRequest + ".courseID) > 0 " + " THEN "
			+ trainerRequest + ".courseID ELSE -1 END as courseID " + "    FROM " + trainerRequest + ", " + course
			+ ", " + trainer + ", " + users + " " + "    WHERE " + course + ".courseID = " + trainerRequest
			+ ".courseID " + "    AND " + trainer + ".email = " + trainerRequest + ".trainerID " + "    AND " + trainer
			+ ".email = :email " + " 	 AND " + users + ".username = " + trainer + ".email " + "	 AND " + users
			+ ".enabled = " + Constant.ACCOUNT_ACTIVE_STATUS;

	private String getTraineePost = " SELECT " + course + ".courseID,  " + course + ".timeInDay, " + course
			+ ".dayInWeek " + " FROM " + trainee + ", " + course + ", " + users + ", " + " " + trainer + ", "
			+ trainerSubject + ", " + " ( " + getTraineeRequest + " ) as request " + " WHERE " + trainer + ".email = "
			+ trainerSubject + ".trainerID " + " AND " + trainee + ".email = " + course + ".traineeID " + " AND "
			+ course + ".subjectID = " + trainerSubject + ".subjectID " + " AND " + course + ".status = "
			+ Constant.COURSE_STATUS_WAITING + " AND " + users + ".enabled = " + Constant.ACCOUNT_ACTIVE_STATUS
			+ " AND " + course + ".courseID != request.courseID " + " AND " + trainer + ".email = :email " + " AND "
			+ users + ".username = " + trainee + ".email " + " AND " + course + ".trainerID is null " + " AND " + course
			+ ".subjectID = CASE WHEN :subjectID = 0 THEN " + course + ".subjectID ELSE :subjectID END 	" + " AND (pow("
			+ trainer + ".latitude - " + trainee + ".latitude,2)+pow(" + trainer + ".longitude - " + trainee
			+ ".longitude,2)) <= 0.0064  " + " ORDER BY (pow(" + trainer + ".latitude - " + trainee
			+ ".latitude,2)+pow(" + trainer + ".longitude - " + trainee + ".longitude,2)) ";

	private String getPage = " LIMIT " + Constant.TOTAL_CONTENT_PER_PAGE + " OFFSET :offset ";

	private String getTrainerTeachingCourseCalendar = " SELECT DISTINCT " + " CASE WHEN COUNT(" + course
			+ ".timeInDay) <=0 THEN 0 ELSE " + course + ".timeInDay END as timeInDay, " + " CASE WHEN COUNT(" + course
			+ ".dayInWeek) <=0 THEN 0 ELSE " + course + ".dayInWeek END as dayInWeek " + " FROM " + course + ", " + " "
			+ trainer + " " + " WHERE " + trainer + ".email = " + course + ".trainerID " + " AND " + trainer
			+ ".email = :email " + " AND " + course + ".status = " + Constant.COURSE_STATUS_STARTED;

	private String getTrainerRequest = " FROM " + trainee + ", " + course + ", " + " " + subject + ", " + trainerRequest
			+ ", " + users + " " + " WHERE " + course + ".subjectID = " + subject + ".subjectID " + " AND " + trainee
			+ ".email = " + course + ".traineeID " + " AND " + trainerRequest + ".courseID = " + course + ".courseID "
			+ " AND " + course + ".status = " + Constant.COURSE_STATUS_WAITING + " AND " + users + ".enabled = "
			+ Constant.ACCOUNT_ACTIVE_STATUS + " AND " + users + ".username = " + trainee + ".email " + " AND " + course
			+ ".subjectID = CASE WHEN :subjectID = 0 THEN " + course + ".subjectID ELSE :subjectID END " + " AND "
			+ trainerRequest + ".trainerID = :email ";

	private String getTraineeAndTrainerCourse = " FROM " + trainee + ", " + course + ", " + subject + ", " + users + " "
			+ " WHERE " + course + ".subjectID = " + subject + ".subjectID " + " AND " + trainee + ".email = " + course
			+ ".traineeID " + " AND " + course + ".status = :courseStatus " + " AND " + users + ".enabled = "
			+ Constant.ACCOUNT_ACTIVE_STATUS + " AND " + users + ".username = " + trainee + ".email " + " AND " + course
			+ ".subjectID = CASE WHEN :subjectID = 0 THEN " + course + ".subjectID ELSE :subjectID END " + " AND "
			+ course + ".trainerID = :email ";

	private String getTraineeAndTrainerCourse2 = " FROM " + trainee + ", " + course + ", " + subject + " " + " WHERE "
			+ course + ".subjectID = " + subject + ".subjectID " + " AND " + trainee + ".email = " + course
			+ ".traineeID " + " AND ( course.status = 4 or " + course + ".status = :courseStatus )" + " AND " + course
			+ ".subjectID = CASE WHEN :subjectID = 0 THEN " + course + ".subjectID ELSE :subjectID END " + " AND "
			+ course + ".trainerID = :email  ";

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomCourse> getTraineeCourseBySubject(String email, int subjectID, int curPage) {
		String sql = " SELECT distinct " + course + ".courseID, " + course + ".title, " + course + ".description, "
				+ " " + course + ".traineeID as traineeID, " + trainee + ".fullName as traineeName, " + trainee
				+ ".avatar as traineeAvatar, " + " '' as trainerID, '' as trainerName, '' as trainerAvatar, " + " "
				+ trainee + ".address, " + " " + course + ".timeInDay, " + course + ".dayInWeek, " + " " + course
				+ ".status, " + " " + course + ".subjectID, " + " " + subject + ".subject, " + " " + course
				+ ".deposit, " + " " + course + ".createDate " + " FROM ( " + getTraineePost + getPage
				+ " ) as courseList, " + " ( " + getTrainerTeachingCourseCalendar + " ) as calList, " + trainee + ", "
				+ trainer + ", " + subject + ",trainer_request as tr, " + course + " " + " WHERE ((courseList.dayInWeek = calList.dayInWeek "
				+ " AND courseList.timeInDay != calList.timeInDay) "
				+ " OR (courseList.dayInWeek != calList.dayInWeek)) " + " AND " + course + ".traineeID = " + trainee
				+ ".email " + " AND " + course + ".subjectID = " + subject + ".subjectID "
				+ " AND courseList.courseID = " + course + ".courseID AND (courseList.courseID not in (select courseID trainerID from trainer_request where trainerID =:email)); ";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(CustomCourse.class))
				.setParameter("subjectID", subjectID).setParameter("email", email)
				.setParameter("offset", (curPage - 1) * Constant.TOTAL_CONTENT_PER_PAGE).list();
	}

	@Override
	public int countTraineeCourseBySubject(String email, int subjectID) {
		String sql = " SELECT count(courseID) " + " FROM ( SELECT distinct courseList.courseID " + " FROM ( "
				+ getTraineePost + " ) as courseList, " + " ( " + getTrainerTeachingCourseCalendar + " ) as calList, "
				+ trainee + ", " + trainer + ", " + subject + ", " + course + " "
				+ " WHERE ((courseList.dayInWeek = calList.dayInWeek "
				+ " AND courseList.timeInDay != calList.timeInDay) "
				+ " OR (courseList.dayInWeek != calList.dayInWeek))) as list; ";
		String count = entityManager.createNativeQuery(sql).unwrap(Query.class).setParameter("subjectID", subjectID)
				.setParameter("email", email).list().get(0) + "";
		return (int) Math.ceil((Double.parseDouble(count) / Constant.TOTAL_CONTENT_PER_PAGE));
	}

	@Override
	public boolean saveNewTrainerRequest(String trainerID, int courseID) {
		String sql = "INSERT INTO trainer_request (trainerID, courseID) VALUES (:trainerID, :courseID)";
		Query query = entityManager.createNativeQuery(sql).unwrap(Query.class);
		query.setParameter("trainerID", trainerID);
		query.setParameter("courseID", courseID);
		int result = query.executeUpdate();
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomCourse> getTrainerRequestCourseList(String email, int subjectID, int curPage) {
		String sql = " SELECT " + course + ".courseID, " + course + ".title, " + course + ".description, " + " "
				+ course + ".traineeID as traineeID, " + trainee + ".fullName as traineeName, " + trainee
				+ ".avatar as traineeAvatar, " + " '' as trainerID, '' as trainerName, '' as trainerAvatar, " + " "
				+ trainee + ".address, " + " " + course + ".timeInDay, " + course + ".dayInWeek, " + " " + course
				+ ".status, " + " " + course + ".subjectID, " + " " + subject + ".subject, " + " " + course
				+ ".deposit, " + " " + course + ".createDate " + getTrainerRequest + getPage + " ;";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(CustomCourse.class))
				.setParameter("subjectID", subjectID).setParameter("email", email)
				.setParameter("offset", (curPage - 1) * Constant.TOTAL_CONTENT_PER_PAGE).list();
	}

	@Override
	public int countTrainerRequestCourse(String email, int subjectID) {
		String sql = " SELECT count(courseID) " + " FROM ( SELECT " + course + ".courseID " + getTrainerRequest
				+ " ) as courseList; ";
		String count = entityManager.createNativeQuery(sql).unwrap(Query.class).setParameter("subjectID", subjectID)
				.setParameter("email", email).list().get(0) + "";
		return (int) Math.ceil((Double.parseDouble(count) / Constant.TOTAL_CONTENT_PER_PAGE));
	}

	@Override
	public boolean cancelTrainerRequest(String trainerID, int courseID) {
		String sql = " DELETE FROM trainer_request as " + trainerRequest + " " + " WHERE " + trainerRequest
				+ ".trainerID= :trainerID " + " AND " + trainerRequest + ".courseID = :courseID ; ";
		Query query = entityManager.createNativeQuery(sql).unwrap(Query.class);
		query.setParameter("trainerID", trainerID);
		query.setParameter("courseID", courseID);
		int result = query.executeUpdate();
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomCourse> getTrainerTeachingCourseList(String email, int subjectID, int curPage) {
		String sql = " SELECT " + course + ".courseID, " + course + ".title, " + course + ".description, " + " "
				+ course + ".traineeID as traineeID, " + trainee + ".fullName as traineeName, " + trainee
				+ ".avatar as traineeAvatar, " + " '' as trainerID, '' as trainerName, '' as trainerAvatar, " + " "
				+ trainee + ".address, " + " " + course + ".timeInDay, " + course + ".dayInWeek, " + " " + course
				+ ".status, " + " " + course + ".subjectID, " + " " + subject + ".subject, " + " " + course
				+ ".deposit, " + " " + course + ".createDate " + getTraineeAndTrainerCourse2 + getPage + " ;";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(CustomCourse.class))
				.setParameter("courseStatus", Constant.COURSE_STATUS_STARTED).setParameter("subjectID", subjectID)
				.setParameter("email", email).setParameter("offset", (curPage - 1) * Constant.TOTAL_CONTENT_PER_PAGE)
				.list();
	}

	@Override
	public int countTrainerTeachingCourse(String email, int subjectID) {
		String sql = " SELECT count(courseID) " + " FROM ( SELECT " + course + ".courseID "
				+ getTraineeAndTrainerCourse2 + " ) as courseList; ";
		String count = entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setParameter("courseStatus", Constant.COURSE_STATUS_STARTED).setParameter("subjectID", subjectID)
				.setParameter("email", email).list().get(0) + "";
		return (int) Math.ceil((Double.parseDouble(count) / Constant.TOTAL_CONTENT_PER_PAGE));
	}

	@Override
	public boolean endTeachingCourse(String trainerID, int courseID) {
		String sql = " UPDATE course " + " SET createDate = :updateDate , status = " + Constant.COURSE_STATUS_ENDED
				+ " WHERE courseID = :courseID " + " AND trainerID = :trainerID ; ";
		Query query = entityManager.createNativeQuery(sql).unwrap(Query.class);
		query.setParameter("trainerID", trainerID);
		query.setParameter("courseID", courseID);

		// 1) create a java calendar instance
		Calendar calendars = Calendar.getInstance();
		// 2) get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		Date now = calendars.getTime();

		// 3) a java current time (now) instance
		Timestamp currentTimestamp = new Timestamp(now.getTime());

		query.setParameter("updateDate", currentTimestamp);
		int result = query.executeUpdate();
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String sumRatingScoreByTrainerID(String trainerID) {
		String sql = " SELECT CASE WHEN " + trainerRating + ".ratingScore is null THEN -1 ELSE AVG(" + trainerRating
				+ ".ratingScore) END as score " + " FROM " + trainerRating + ", " + trainer + " " + " WHERE "
				+ trainerRating + ".trainerID = " + trainer + ".email " + " AND " + trainer + ".email = :trainerID ; ";
		return entityManager.createNativeQuery(sql).unwrap(Query.class).setParameter("trainerID", trainerID).list()
				.get(0) + "";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomCourse> getTraineeRequestCourse(String email, int subjectID, int curPage) {
		String sql = " SELECT " + course + ".courseID, " + course + ".title, " + course + ".description, " + " "
				+ course + ".traineeID as traineeID, " + trainee + ".fullName as traineeName, " + trainee
				+ ".avatar as traineeAvatar, " + " '' as trainerID, '' as trainerName, '' as trainerAvatar, " + " "
				+ trainee + ".address, " + " " + course + ".timeInDay, " + course + ".dayInWeek, " + " " + course
				+ ".status, " + " " + course + ".subjectID, " + " " + subject + ".subject, " + " " + course
				+ ".deposit, " + " " + course + ".createDate " + getTraineeAndTrainerCourse + getPage + " ;";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(CustomCourse.class))
				.setParameter("courseStatus", Constant.COURSE_STATUS_WAITING).setParameter("subjectID", subjectID)
				.setParameter("email", email).setParameter("offset", (curPage - 1) * Constant.TOTAL_CONTENT_PER_PAGE)
				.list();
	}

	@Override
	public int countTraineeRequestCourse(String email, int subjectID) {
		String sql = " SELECT count(courseID) " + " FROM ( SELECT " + course + ".courseID " + getTraineeAndTrainerCourse
				+ " ) as courseList; ";
		String count = entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setParameter("courseStatus", Constant.COURSE_STATUS_WAITING).setParameter("subjectID", subjectID)
				.setParameter("email", email).list().get(0) + "";
		return (int) Math.ceil((Double.parseDouble(count) / Constant.TOTAL_CONTENT_PER_PAGE));
	}

	@Override
	public boolean denyTraineeRequest(String trainerID, int courseID) {
		String sql = " UPDATE course " + " SET createDate = :updateDate , status = " + Constant.COURSE_STATUS_DENIED
				+ " WHERE courseID = :courseID " + " AND trainerID = :trainerID ; ";
		Query query = entityManager.createNativeQuery(sql).unwrap(Query.class);
		query.setParameter("trainerID", trainerID);
		query.setParameter("courseID", courseID);

		// 1) create a java calendar instance
		Calendar calendars = Calendar.getInstance();
		// 2) get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		Date now = calendars.getTime();

		// 3) a java current time (now) instance
		Timestamp currentTimestamp = new Timestamp(now.getTime());

		query.setParameter("updateDate", currentTimestamp);
		int result = query.executeUpdate();
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomCourse> getEndedCourse(String email, int subjectID, int curPage) {
		String sql = " SELECT " + course + ".courseID, " + course + ".title, " + course + ".description, " + " "
				+ course + ".traineeID as traineeID, " + trainee + ".fullName as traineeName, " + trainee
				+ ".avatar as traineeAvatar, " + " '' as trainerID, '' as trainerName, '' as trainerAvatar, " + " "
				+ trainee + ".address, " + " " + course + ".timeInDay, " + course + ".dayInWeek, " + " " + course
				+ ".status, " + " " + course + ".subjectID, " + " " + subject + ".subject, " + " " + course
				+ ".deposit, " + " " + course + ".createDate " + getTraineeAndTrainerCourse2 + getPage + " ;";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(CustomCourse.class))
				.setParameter("courseStatus", Constant.COURSE_STATUS_ENDED).setParameter("subjectID", subjectID)
				.setParameter("email", email).setParameter("offset", (curPage - 1) * Constant.TOTAL_CONTENT_PER_PAGE)
				.list();
	}

	@Override
	public int countEndedCourse(String email, int subjectID) {
		String sql = " SELECT count(courseID) " + " FROM ( SELECT " + course + ".courseID "
				+ getTraineeAndTrainerCourse2 + " ) as courseList; ";
		String count = entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setParameter("courseStatus", Constant.COURSE_STATUS_ENDED).setParameter("subjectID", subjectID)
				.setParameter("email", email).list().get(0) + "";
		return (int) Math.ceil((Double.parseDouble(count) / Constant.TOTAL_CONTENT_PER_PAGE));
	}

	@Override
	public Trainee getTraineeProfile(String traineeID) {
		String sql = "SELECT email, address, avatar, dayOfBirth, " + " facebook, fullName, gender, phone, zalo, "
				+ " latitude, longitude " + " FROM " + trainee + " " + " WHERE email = :email ; ";
		return (Trainee) entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Trainee.class)).setParameter("email", traineeID)
				.list().get(0);
	}

	@Override
	public boolean acceptTraineeRequest(String trainerID, int courseID) {
		String sql = " UPDATE course " + " SET createDate = :updateDate , status = " + Constant.COURSE_STATUS_STARTED
				+ " WHERE courseID = :courseID " + " AND trainerID = :trainerID ; ";
		Query query = entityManager.createNativeQuery(sql).unwrap(Query.class);
		query.setParameter("trainerID", trainerID);
		query.setParameter("courseID", courseID);

		// 1) create a java calendar instance
		Calendar calendars = Calendar.getInstance();
		// 2) get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		Date now = calendars.getTime();

		// 3) a java current time (now) instance
		Timestamp currentTimestamp = new Timestamp(now.getTime());

		query.setParameter("updateDate", currentTimestamp);
		int result = query.executeUpdate();
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrainerRating> getTopTrainer() {
		String sql = "select trainer.* , b.rating from trainer,(SELECT  trainerID ,avg(ratingScore) as rating FROM trainer_rating  group by trainerID order by avg(ratingScore) desc limit 4) as b where email = b.trainerID;";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(TrainerRating.class)).list();
	}
	@Override
	public boolean existTrainerRating(String trainerID, String traineeID) {
		
		String sql = "select * from trainer_rating where trainerID=:trainerID and traineeID=:traineeID";
		
		
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(edu.fa.model.TrainerRating.class)).setParameter("trainerID", trainerID).
				setParameter("traineeID", traineeID).list().size() > 0;
	}

	@Override
	public boolean existCourseOfTrainer(String trainerID, int dayInWeek, int timeInDay) {
		String sql = "select courseID from course as c where c.trainerID = :trainerID and c.status = 2 and c.timeInDay = :timeInDay and c.dayInWeek = :dayInWeek";
		return entityManager.createNativeQuery(sql).unwrap(Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(Course.class)).setParameter("trainerID", trainerID).
				setParameter("timeInDay", timeInDay).setParameter("dayInWeek", dayInWeek).list().size() > 0;
	}
	
	
}
