package edu.fa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.model.Subject;
import edu.fa.repository.SubjectRepository;

@Service
public class SubjectService {
	@Autowired
	private SubjectRepository subRes;
	
	public Subject searchSubjectByID(int id) {
		return subRes.findBySubjectID(id);
	}
	
	public boolean addNewSubject(String subjectName) {
		if(subRes.findBySubject(subjectName)!=null) {
			return false;
		}// check if subject is existed
		else {
			return subRes.save(new Subject(subjectName)) != null;
		}// else subject is not existed		
	}
	public List<Subject> searchSubjectNameLike(String subjectName) {
		return subRes.findBySubjectLike("%"+subjectName+"%");
	}
	public boolean deteleSubjectByID(int subjectID) {
		Subject subject = subRes.findBySubjectID(subjectID);
		int checkExistInTrainerSubject = subRes.checkExistSubject(subjectID);
		int checkExistInCourse = subRes.checkExistSubjectInCourse(subjectID);
		if(subject != null && checkExistInTrainerSubject == 0 && checkExistInCourse == 0) {
			
			subRes.delete(subject);
			return true;
		}//if subject is not null
		else {
			
			return false;
		}//else subject is null
	}
	
	public boolean updateSubject(Subject subject) {
		if(subRes.findBySubject(subject.getSubject())!=null) {
			return false;
		}// check if subject is existed
		else {
			return subRes.save(subject) != null;
		}// else subject is not existed	
		
		
		
	}
	
	public List<Subject> showAll(){
		return subRes.findAll();
	}
	public List<Long> getListSubjectID(){
		return subRes.findSubjectID();
		
	}

	public List<Subject> selectSubject() {
		// TODO Auto-generated method stub
		return subRes.select();
	}
	public Subject findSubject(int subjectID) {
		return subRes.findOne(subjectID);
	}

}
