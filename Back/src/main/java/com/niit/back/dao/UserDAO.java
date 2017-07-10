package com.niit.back.dao;

import java.util.List;

import com.niit.back.model.User;

public interface UserDAO {
	
	public List<User> list();

	public User saveOrUpdate(User user);
	
	public User create(User user);
	
	public void delete(int userId);

	public User getByUserId(int userId);

	public User getByMail(String email);

	

	public User login(User user);
}
