package edu.fa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import edu.fa.model.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, String>, TraineeRepositoryCustom {
	Trainee findOneByEmail(String email);

	Trainee findByEmail(String email);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "Update users set enabled = 2 where username=?1", nativeQuery = true)
	void deleteTraineeByAdminSetEnable(String email);

	@Transactional
	@Query(value = "Select * FROM trainee where email in (select username from users where enabled = 1) ORDER BY email ASC", nativeQuery = true)
	List<Trainee> getListTraineeByEnable();

	@Query(value = "select * from trainee t where t.fullName like %:keyword% AND t.email in (select username from users where enabled = 1);", nativeQuery = true)
	List<Trainee> searchEmailByKeyword(@Param("keyword") String keyword);

	@Transactional
	@Query(value = "SELECT * FROM trainee where email in (select username from users where enabled = 2);", nativeQuery = true)
	public List<Trainee> getBlackListTrainee();

}
