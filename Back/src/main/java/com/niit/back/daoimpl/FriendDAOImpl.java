package com.niit.back.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.back.dao.FriendDAO;
import com.niit.back.model.Friend;

@Repository("FriendDAO")
public class FriendDAOImpl implements FriendDAO  {
	
	@Autowired
	private SessionFactory sessionFactory;
	public FriendDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Friend> list() {
		List<Friend> listFriend = sessionFactory.getCurrentSession().createQuery("from Friend").list();
		return listFriend;
	}
	@Transactional
	public void delete(int friendId) {
	Friend friendToDelete = new Friend();
	friendToDelete.setFriendid(friendId);
	sessionFactory.getCurrentSession().delete(friendToDelete);

	}
   @Transactional
	public Friend getByFriendid(int friendId) {
	Friend Friendid = (Friend) sessionFactory.getCurrentSession().get(Friend.class, friendId);

	return Friendid;
	}
   
   @Transactional
   public Friend saveOrUpdate(Friend friend) {
	sessionFactory.getCurrentSession().saveOrUpdate(friend);
	return friend;
   }
   @Transactional
   public List<Friend> getByFriendRequestList(int userid) {
	   String hql = "from Friend where userid=" + "'" + userid + "'   and status = " + "'N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		
		return list;
   }
   @Transactional
   public List<Friend> FriendList(int userid) {
	   String hql = "from Friend where userid=" + "'" + userid + "'   and status = " + "'A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		
		return list;
   }
  }
