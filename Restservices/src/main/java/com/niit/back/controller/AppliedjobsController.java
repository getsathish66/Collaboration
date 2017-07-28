package com.niit.back.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.back.dao.AppliedjobsDAO;
import com.niit.back.model.Appliedjobs;
import com.niit.back.model.Job;
import com.niit.back.model.User;

@RestController
public class AppliedjobsController {
	@Autowired
	private AppliedjobsDAO  appliedJobsDAO;

	
public AppliedjobsDAO getAppliedJobsDAO() {
		return appliedJobsDAO;
	}

	public void setAppliedJobsDAO(AppliedjobsDAO appliedJobsDAO) {
		this.appliedJobsDAO = appliedJobsDAO;
	}

@GetMapping("/allappliedjobs")

public List<Appliedjobs> ajobs()
	{	
	return appliedJobsDAO.list();
}

@GetMapping("/alljobid/{jobid}")
public List<Appliedjobs> ajobsid(@PathVariable("jobid")int jobid)
{	
return appliedJobsDAO.getByJobId(jobid);
}

@GetMapping("/alljobuserId/{userId}") 
public List<Appliedjobs> ajobsuid(@PathVariable("userId") int userId)
{	
return appliedJobsDAO.getByUserId(userId);
}

@GetMapping("/alljobusername/{username}") 
public List<Appliedjobs> ajobsuname(@PathVariable("username")String username)
{	
return appliedJobsDAO.getByUserName(username);
}


@SuppressWarnings({ "rawtypes", "unchecked" })
@GetMapping("/appliedjobs/{ajobid}") 
public ResponseEntity getaJobs(@PathVariable("ajobid") String jobid) {

	Appliedjobs ajob = appliedJobsDAO.getByAJobId(jobid);
	if (ajob == null) {
		return new ResponseEntity("No  Applied Job found for ID " + jobid, HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity(ajob, HttpStatus.OK);
}
@SuppressWarnings({ "rawtypes", "unchecked" })
@PostMapping(value = "/appliedjobs")
public ResponseEntity applyJob(@RequestBody Appliedjobs appliedjobs, HttpSession session) {
	User user = (User) session.getAttribute("user");
	appliedjobs.setDateTime(new Date());
	appliedjobs.setUserId(user.getUserId());
	appliedjobs.setEmail(user.getEmail());
	//appliedJobs.setTimeStamp(user.getTimeStamp());
	appliedJobsDAO.saveOrUpdate(appliedjobs);

	return new ResponseEntity(appliedjobs, HttpStatus.OK);
}
@SuppressWarnings({ "rawtypes", "unchecked" })
@DeleteMapping("/appliedjobs/{jobid}") 
public ResponseEntity deleteaJob(@PathVariable String id) {
	Appliedjobs ajob=appliedJobsDAO.getByAJobId(id);
		if (ajob==null) {
		return new ResponseEntity("No Job found for ID " + id, HttpStatus.NOT_FOUND);
	}
		//appliedJobsDAO.delete(id);
	return new ResponseEntity(id, HttpStatus.OK);

}


@SuppressWarnings({ "unchecked", "rawtypes" })
@PostMapping(value = "/jobapply")
public ResponseEntity applyjob(@RequestBody Appliedjobs appliedjobs,HttpSession session) {
	User user = (User) session.getAttribute("user");
	appliedjobs.setEmail(user.getEmail());
	appliedjobs.setUserId(user.getUserId());
	appliedjobs.setUsername(user.getUsername());
	appliedjobs.setDateTime(new Date());
	appliedJobsDAO.saveOrUpdate(appliedjobs);
	return new ResponseEntity(appliedjobs, HttpStatus.OK);
}



}