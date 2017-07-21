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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.back.dao.JobDAO;
import com.niit.back.model.Job;
import com.niit.back.model.User;

@RestController
public class JobController {

	@Autowired
	private JobDAO jobDAO;
	
	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}
	
	@GetMapping("/job")
	public ResponseEntity<List<Job>> getJobs() {
		List<Job> listjob = jobDAO.list();
		return new ResponseEntity<List<Job>>(listjob, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/job/{jobid}")
	public ResponseEntity getByJobId(@PathVariable("jobid") int jobid) {

		Job job = jobDAO.getByJobid(jobid);
		if (job == null) {
			return new ResponseEntity("No Job found for ID " + jobid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/job")
	public ResponseEntity createjob(@RequestBody Job job,HttpSession session) {
		job.setPostdate(new Date());
		job.setStatus("A");
		User user = (User) session.getAttribute("user");   
		
		job.setUserId(user.getUserId());
		job.setUsername(user.getUsername());
		job.setEmail(user.getEmail());
		jobDAO.saveOrUpdate(job);

		return new ResponseEntity(job, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/job/{jobid}")
	public ResponseEntity deleteJob(@PathVariable int jobid) {
		Job job=jobDAO.getByJobid(jobid);
 		if (job==null) {
			return new ResponseEntity("No Job found for ID " + jobid, HttpStatus.NOT_FOUND);
		}
 		jobDAO.delete(jobid);
		return new ResponseEntity(jobid, HttpStatus.OK);
   }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/job/{jobid}")
	public ResponseEntity saveorupdateUser(@PathVariable int jobid, @RequestBody Job job) {

		job = jobDAO.saveOrUpdate(job);
		if (null == job) {
			return new ResponseEntity("No Job found for ID " + jobid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}

}
