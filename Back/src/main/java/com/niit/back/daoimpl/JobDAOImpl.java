package com.niit.back.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.back.dao.JobDAO;
import com.niit.back.model.Job;

@Repository("JobDAO")
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> list() {
		List<Job> listJob = sessionFactory.getCurrentSession().createQuery("from Job").list();
		return listJob;
	}
	@Transactional
	public Job saveOrUpdate(Job job) {
		sessionFactory.getCurrentSession().saveOrUpdate(job);
		return job;
}
	@Transactional
	public void delete(int jobId) {
		Job jobToDelete = new Job();
		jobToDelete.setJobid(jobId);
		sessionFactory.getCurrentSession().delete(jobToDelete);
	}
	@Transactional
	public Job getByJobid(int jobId){ 
		Job JobId = (Job) sessionFactory.getCurrentSession().get(Job.class, jobId);

		return JobId;
	}
	@Transactional
	public Job getByJobcategory(String jobCategory) {
		Job JobCategory = (Job) sessionFactory.getCurrentSession().get(Job.class, jobCategory);

		return JobCategory;
	}
}
