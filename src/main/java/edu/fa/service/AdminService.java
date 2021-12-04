package edu.fa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.model.Admin;
import edu.fa.repository.AdminRepository;

@Service
public class AdminService  {
	@Autowired
	AdminRepository  adminRepository;
	
	public Admin getProfile(String email) {
		return adminRepository.findOne(email);
	}
	public Admin updateProfile(Admin admin) {
		return adminRepository.save(admin);
	}
	public int countCreatePostRequest() {
		return adminRepository.countCreatePostRequest();
	}
	public int countRegisTrainer(){
		return adminRepository.countRegisTrainer();
	}
	public int countUpdateInfoTrainer() {
		return adminRepository.countUpdateInfoTrainer();
	}
}
