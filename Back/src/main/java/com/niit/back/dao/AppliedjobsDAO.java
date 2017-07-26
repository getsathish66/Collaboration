package com.niit.back.dao;

import java.util.List;

import com.niit.back.model.Appliedjobs;

public interface AppliedjobsDAO {
	
public List<Appliedjobs> getByJobId(int jobid);    
	
	public List<Appliedjobs> getByUserName(String username); 
	
	public List<Appliedjobs> getByUserId(int userid);
	
	public void saveOrUpdate(Appliedjobs appljob);

	public Appliedjobs getByAJobId(String jobid);
	
	
	public void delete(int jobid);

	public List<Appliedjobs> list();

}
