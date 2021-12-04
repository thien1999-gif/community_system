package edu.fa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.model.Users;
import edu.fa.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	public void saveAccount(Users account) {
		// TODO Auto-generated method stub
		accountRepo.save(account);
	}
	
	public void saveAccountStatus(String email, int status) {
		// TODO Auto-generated method stub
		accountRepo.enableUser(email,status);
	}
	
	public boolean checkEmai(String email) {
		return accountRepo.exists(email);
	}
	
	@SuppressWarnings("rawtypes")
	public Optional findUserByEmail(String email) {
		return accountRepo.findByUsername(email);
	}

	
	@SuppressWarnings("rawtypes")
	public Optional findUserByResetToken(String resetToken) {
		return accountRepo.findByResetToken(resetToken);
	}
	
	public void save(Users user) {
		accountRepo.save(user);
	}
}
