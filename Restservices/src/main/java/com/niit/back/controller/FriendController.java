package com.niit.back.controller;

import java.util.List;

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

import com.niit.back.dao.FriendDAO;
import com.niit.back.model.Friend;

@RestController
public class FriendController {

	@Autowired
	private FriendDAO friendDAO;
	
	public FriendDAO getFriendDAO() {
		return friendDAO;
	}

	public void setFriendDAO(FriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}
		
	@GetMapping("/friend")
	public ResponseEntity<List<Friend>> getFriends() {
		List<Friend> listfriend = friendDAO.list();
		return new ResponseEntity<List<Friend>>(listfriend, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/friend/{friendid}")
	public ResponseEntity getByfriendId(@PathVariable("friendid") int friendid) {

		Friend friend = friendDAO.getByFriendid(friendid);
		if (friend == null) {
			return new ResponseEntity("No Friend found for ID " + friendid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(friend, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/friend")
	public ResponseEntity createFreiend(@RequestBody Friend friend) {

		friendDAO.saveOrUpdate(friend);

		return new ResponseEntity(friend, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/friend/{friendid}")
	public ResponseEntity deleteFriend(@PathVariable int friendid) {
		Friend friend=friendDAO.getByFriendid(friendid);
 		if (friend==null) {
			return new ResponseEntity("No Friend found for ID " + friendid, HttpStatus.NOT_FOUND);
		}
 		friendDAO.delete(friendid);
		return new ResponseEntity(friendid, HttpStatus.OK);
   }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/friend/{friendid}")
	public ResponseEntity saveorupdateUser(@PathVariable int friendid, @RequestBody Friend friend) {

		friend = friendDAO.saveOrUpdate(friend);
		if (null == friend) {
			return new ResponseEntity("No User found for ID " + friendid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(friend, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/friendsList/{userid}")
	public ResponseEntity<List> getFriendList(@PathVariable ("userid") int userid) {
		
		List listuser  = friendDAO.FriendList(userid);
		return new ResponseEntity(listuser ,HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/friendRequest/{userid}")
	public ResponseEntity<List> getFriendRequests(@PathVariable ("userid") int userid) {
		
		List listuser = friendDAO.getByFriendRequestList(userid);
		return new ResponseEntity(listuser,HttpStatus.OK);
	}
	
}
