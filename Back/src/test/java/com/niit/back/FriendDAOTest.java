package com.niit.back;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.back.dao.FriendDAO;
import com.niit.back.model.Friend;

public class FriendDAOTest {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static FriendDAO friendDAO;

	@Autowired
	static Friend friend;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.back");
		context.refresh();

		// get the friendDAO from context
		friendDAO = (FriendDAO) context.getBean("FriendDAO");

		friend = (Friend) context.getBean("friend");
	}

	@Test
	public void createFriendDAOTest() {

		friend.setUserid(7);
		friend.setStatus("P");
		
		friendDAO.saveOrUpdate(friend);

	}
	


}
