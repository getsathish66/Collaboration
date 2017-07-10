package com.niit.back.dao;

import java.util.List;

import com.niit.back.model.Comment;

public interface CommentDAO {
	
    public List<Comment> list();
	
	public Comment getForumComments(String forumId);
	
	public Comment getComment(int commentid);
	
	public Comment saveOrUpdate(Comment comment);
		
	public void delete(int CommentId);

	public void create (Comment comment);
}
