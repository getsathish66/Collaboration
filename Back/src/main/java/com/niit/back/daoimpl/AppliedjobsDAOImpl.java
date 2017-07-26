package com.niit.back.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.back.dao.AppliedjobsDAO;
import com.niit.back.model.Appliedjobs;

@Transactional
@Repository("AppliedjobsDAO")

public class AppliedjobsDAOImpl implements AppliedjobsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public AppliedjobsDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<Appliedjobs> list() {
		return sessionFactory.getCurrentSession().createQuery("from Appliedjobs").list();
	}

	public List<Appliedjobs> getByJobId(int jobid) {
		Session session=sessionFactory.openSession();
		
		String hql = "from Appliedjobs where jobid ='" + jobid + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Appliedjobs> ajobs=query.list();
		session.close();
		return ajobs;
	}

	public List<Appliedjobs> getByUserName(String username) {
		Session session=sessionFactory.openSession();
		String hql = "from Appliedjobs where username ='" + username + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		@SuppressWarnings("unchecked")
		List<Appliedjobs> uemails=query.list();
		session.close();
		return uemails;
	}

	public void saveOrUpdate(Appliedjobs ajob) {
		sessionFactory.getCurrentSession().saveOrUpdate(ajob);
		
	}

	public Appliedjobs getByAJobId(String jobid) {   
		Appliedjobs ajobListByID = (Appliedjobs) sessionFactory.getCurrentSession().get(Appliedjobs.class, jobid);

		return ajobListByID;
	}

	public void delete(int jobid) {
		Appliedjobs ajobDelete = new Appliedjobs();
		ajobDelete.setJobid(jobid);
		sessionFactory.getCurrentSession().delete(ajobDelete);
		
	}
	public List<Appliedjobs> getByUserId(int userid) {
		
		Session session=sessionFactory.openSession();
		String hql = "from Appliedjobs where userid ='" + userid + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Appliedjobs> uemails=query.list();
		session.close();
		return uemails;
	}

}
