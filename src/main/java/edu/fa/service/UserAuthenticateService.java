package edu.fa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.fa.constant.Constant;
import edu.fa.dto.UserAuthenticateDetails;
import edu.fa.model.Admin;
import edu.fa.model.Trainee;
import edu.fa.model.Trainer;
import edu.fa.model.Users;
import edu.fa.repository.AdminRepository;
import edu.fa.repository.AuthorRepository;
import edu.fa.repository.TraineeRepository;
import edu.fa.repository.TrainerRepository;
import edu.fa.repository.UserAuthenticateRepository;
//import edu.fa.repository.UserAuthenticateRepository;
@Service
public class UserAuthenticateService implements UserDetailsService{

	@Autowired
	private UserAuthenticateRepository userAuthenticateRepository;
	@Autowired
	private TrainerRepository trainerRepository;
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private AdminRepository adminRepository;	
	@Autowired
	private AuthorRepository authorRepository;
	
//load user for Authentication	==================================================================
	@SuppressWarnings("unlikely-arg-type")
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
//		
//		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//		Users test = new Users();
//		test.setUsername(email);
//		test.setPassword(encode.encode("123456"));
//		test.setEnabled(1);
//		userAuthenticateRepository.save(test);
////		
//	//==============================================================================
		String fullName = "";
		String phone = "";
		String avatar = "";
		
//	//get userEmail and password for Authentication ===========================================
		Users user = userAuthenticateRepository.findOneByUsername(email);	
//		if(user == null) {
//			 throw new UsernameNotFoundException("User not found!");
//		}
		
//	//get user role ===================================================================
		String role = authorRepository.findOneByUsername(email).getAuthority();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
//	//check role to get user's details base on user's role====================
		if(authorities.contains(Constant.ACCOUNT_ROLE_TRAINER)) {
			
			Trainer trainer = trainerRepository.findOneByEmail(email);
			fullName = trainer.getFullName();
			phone = trainer.getPhone();
			avatar = trainer.getAvatar();
		}
		else if(authorities.contains(Constant.ACCOUNT_ROLE_TRAINEE)){
			
			Trainee trainee = traineeRepository.findOneByEmail(email);
			fullName = trainee.getFullName();
			phone = trainee.getPhone();
			avatar = trainee.getAvatar();
		}
		else if(authorities.contains(Constant.ACCOUNT_ROLE_ADMIN)) {
			Admin admin = adminRepository.findOneByEmail(email);
			fullName = admin.getFullName();
			phone = admin.getPhone();
			avatar = admin.getAvatar();
		}
//	//store user's detail=====================================================
		
		UserAuthenticateDetails loginUser = new UserAuthenticateDetails(user.getUsername(), 
																	user.getPassword(), 
																	true, true, true, true, 
																	authorities);
		loginUser.setFullName(fullName);
		loginUser.setPhone(phone);
		loginUser.setAvatar(avatar);
		return loginUser;
	}
//Update Temp User
		public Users updateUser(Users user) {
			return userAuthenticateRepository.save(user);
		}
		public Users getUser(String email) {
			return userAuthenticateRepository.findOne(email);
		}
}
