package com.niit.back;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.back.dao.JobDAO;
import com.niit.back.model.Job;

public class JobDAOTest {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static JobDAO jobDAO;

	@Autowired
	static Job job;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.back");
		context.refresh();

		// get the jobDAO from context
		jobDAO = (JobDAO) context.getBean("JobDAO");

		job = (Job) context.getBean("job");
	}

	@Test
	public void createJobDAOTest() {

		job.setJobdesc("jobs");
		job.setJobprofile("job");
		job.setQualification("be");
		job.setStatus("n");
		
		jobDAO.saveOrUpdate(job);

	}
	


}
