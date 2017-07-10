package com.niit.back.dao;

import java.util.List;

import com.niit.back.model.Job;

public interface JobDAO {
	
	public List<Job>list();

	public Job saveOrUpdate(Job job);
	
	public void delete (int jobId);
	
	public Job getByJobid(int jobId);
	
	public Job getByJobcategory(String jobCategory);
}
