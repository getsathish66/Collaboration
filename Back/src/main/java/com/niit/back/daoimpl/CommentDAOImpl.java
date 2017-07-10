package com.niit.back.daoimpl;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.back.dao.CommentDAO;
import com.niit.back.model.Comment;

@Repository("CommentDAO")
public class CommentDAOImpl implements CommentDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public CommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Comment> list() {
		@SuppressWarnings({ "unchecked" })
		List<Comment> listComment = (List<Comment>) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listComment;
	}
	
	@Transactional
	public Comment getForumComments(String forumId) {
		String hql = "from Comment where forumid ='" + forumId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Comment> listComment = (List<Comment>) query.list();
		if (listComment != null && !listComment.isEmpty()) {
			return listComment.get(0);
		}
		return null;
	}
	
	@Transactional
	public Comment saveOrUpdate(Comment comment) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(comment);
		return comment;

	}
	@Transactional
	public void delete(int CommentId) {
		
		Comment commentToDelete = new Comment();
		commentToDelete.setCommentid(CommentId);
		sessionFactory.getCurrentSession().delete(commentToDelete);

	}

	@Transactional
	public Comment getComment(int CommentId) {
		
		String hql = "from Comment where commentid ='" + CommentId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Comment> listComment = (List<Comment>) query.list();
		if (listComment != null && !listComment.isEmpty()) {
			return listComment.get(0);
		}
		return null;
	}

	@Transactional
	public void create(Comment comment) {
		sessionFactory.getCurrentSession().saveOrUpdate(comment);
		
	}

}
