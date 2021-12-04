package edu.fa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.fa.model.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String>, TrainerRepositoryCustom {

	Trainer findOneByEmail(String email);
	

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "insert into trainer_rating values(?1,?2,?3)", nativeQuery = true)
	void addTrainerRating(String trainerID, String traineeID, double rating);
	

	
	
	@Query(value = "select * from Trainer t where t.email like %:keyword%", nativeQuery = true)
	List<Trainer> searchEmailByKeyword1(@Param("keyword") String keyword);

	@Query(value = "select * from Trainer t where t.fullName like %:keyword% AND t.email in (select username from users where enabled = 1);", nativeQuery = true)
	List<Trainer> searchEmailByKeyword(@Param("keyword") String keyword);

	@Query(value = "select * from trainer where  verify = 0 and email in (select username from users where enabled = 1)", nativeQuery = true)
	List<Trainer> getListByStatus();

	@Query(value = "select * from trainer where email in "
			+ "(SELECT trainerID FROM trainer_request where courseID = ?1);", nativeQuery = true)
	List<Trainer> getListTrainerByCourseID(int courseID);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "delete from trainer_request where courseID = ?1 and trainerID = ?2 ", nativeQuery = true)
	void deleteTeacherAndCourseFromTrainerRequest(int courseID, String trainerID);

	@Transactional
	@Query(value = "Select * FROM trainer where email in (select username from users where enabled = 1) AND verify = 1", nativeQuery = true)
	List<Trainer> getListTrainerByEnable();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "Update users set enabled = 2 where username=?1", nativeQuery = true)
	void deleteTrainerByAdminSetEnable(String email);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update trainer_rating set ratingScore = ?1 where trainerID =?2 and traineeID =?3", nativeQuery = true)
	public void insertTrainerRating(double ratingScore, String trainerID, String traineeID);

	@Transactional
	@Query(value = "select ratingScore from trainer_rating where trainerID =?1 and traineeID =?2", nativeQuery = true)
	public int selectRatingByTrainerEmailAndTraineeEmail(String trainerID, String traineeID);

	@Query(value = "SELECT * FROM (\r\n" + "    SELECT *, \r\n" + "        (\r\n" + "            (\r\n"
			+ "                (\r\n" + "                    acos(\r\n"
			+ "                        sin(( ?2 * pi() / 180))\r\n" + "                        *\r\n"
			+ "                        sin(( latitude * pi() / 180)) + cos(( ?2 * pi() /180 ))\r\n"
			+ "                        *\r\n"
			+ "                        cos(( latitude * pi() / 180)) * cos(((  ?1 - longitude) * pi()/180)))\r\n"
			+ "                ) * 180/pi()\r\n" + "            ) * 60 * 1.1515 * 1.609344\r\n" + "        )\r\n"
			+ "    as distance FROM trainer\r\n" + ") myTable\r\n"
			+ "WHERE distance <= 10 and email not like  '%-watting-update-%' and email in (select trainerID from trainer_subject s where subjectID = ?3) and verify = 1 and email not in (select c.trainerID from course as c where  c.dayInWeek = ?4 and c.timeInDay = ?5 and c.status = 2)\r\n"
			+ "LIMIT 15;", nativeQuery = true)
	List<Trainer> suggestTrainer(double longitude, double latitude, int subjectID, int dayInWeek, int timeInDay);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "Insert into trainer_subject(trainerID,subjectID) values(?1,?2)", nativeQuery = true)
	void insertTrainerSubject(String email, int subjectID);

	@Transactional
	@Query(value = "SELECT * FROM trainer where email in (select username from users where enabled = 2);", nativeQuery = true)
	public List<Trainer> getBlackListTrainer();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "Update users set enabled = 1 where username=?1", nativeQuery = true)
	void unBanTrainer(String email);
	

}
