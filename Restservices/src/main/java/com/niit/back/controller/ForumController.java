package com.niit.back.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.back.dao.ForumDAO;
import com.niit.back.model.Blog;
import com.niit.back.model.Forum;
import com.niit.back.model.User;

@RestController
public class ForumController {

	@Autowired
	private ForumDAO forumDAO;
	
	public ForumDAO getForumDAO() {
		return forumDAO;
	}

	public void setForumDAO(ForumDAO forumDAO) {
		this.forumDAO = forumDAO;
	}
	
	@GetMapping("/forum")
	public ResponseEntity<List<Forum>> getForum() {
		List<Forum> listforum = forumDAO.list();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/forum/{forumid}")
	public ResponseEntity getByForumid(@PathVariable("forumid") int forumid) {

		Forum forum = forumDAO.getByForumid(forumid);
		if (forum == null) {
			return new ResponseEntity("No Forum found for ID " + forumid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes" })
	@PostMapping(value = "/forum")
	public ResponseEntity createForum(@RequestBody Forum forum,HttpSession session) {
		forum.setCreatedate(new Date());
forum.setStatus("NA");
		
		User user = (User) session.getAttribute("user");   
		
		forum.setUserId(user.getUserId());
		forum.setUsername(user.getUsername());
		forum.setEmail(user.getEmail());
		forumDAO.saveOrUpdate(forum);
		
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/forum/{forumid}")
	public ResponseEntity deleteForum(@PathVariable int forumid) {
		Forum forum=forumDAO.getByForumid(forumid);
 		if (forum==null) {
			return new ResponseEntity("No Forum found for ID " + forumid, HttpStatus.NOT_FOUND);
		}
 		forumDAO.delete(forumid);
		return new ResponseEntity(forumid, HttpStatus.OK);
   }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/forum/{forumid}")
	public ResponseEntity saveorupdateForum(@PathVariable int forumid, @RequestBody Forum forum) {

		forum = forumDAO.saveOrUpdate(forum);
		if (null == forum) {
			return new ResponseEntity("No Forum found for ID " + forumid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	@GetMapping("/acceptedforum")
	public ResponseEntity<List<Forum>> acceptedForumsList() {
		List<Forum> listforum = forumDAO.getAcceptedList();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	@GetMapping("/notAcceptedforum")
	public ResponseEntity<List<Forum>> notAcceptedForumList() {
		List<Forum> listforum = forumDAO.getNotAcceptedList();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/acceptForum")
	public ResponseEntity acceptForum(@RequestBody Forum forum) {
		forum.setStatus("A");
		forumDAO.Update(forum);
		return new ResponseEntity(forum, HttpStatus.OK);
	}
}
