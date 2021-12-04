package edu.fa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.model.Authorities;
import edu.fa.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepo;

	public void saveAuthor(Authorities author) {
		// TODO Auto-generated method stub
		authorRepo.save(author);
	}
	
	public boolean checkEmai(String email) {
		return authorRepo.exists(email);
	}
}
