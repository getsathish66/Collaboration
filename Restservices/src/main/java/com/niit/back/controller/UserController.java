
package com.niit.back.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.back.dao.UserDAO;
import com.niit.back.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> getUsers() {
		System.out.println("hiiiii");
		List<User> listuser = userDAO.list();
		return new ResponseEntity<List<User>>(listuser, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/user/{userId}")
	public ResponseEntity getByUserId(@PathVariable("userId") int userId) {

		User user = userDAO.getByUserId(userId);
		if (user == null) {
			return new ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/user")
	public ResponseEntity createUser(@RequestBody User user) {

		userDAO.create(user);

		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/user/{userId}")
	public ResponseEntity deleteUser(@PathVariable int userId) {
		User user=userDAO.getByUserId(userId);
 		if (user==null) {
			return new ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND);
		}
 		userDAO.delete(userId);
		return new ResponseEntity(userId, HttpStatus.OK);
   }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/user/{userId}")
	public ResponseEntity saveorupdateUser(@PathVariable int userId, @RequestBody User user) {

		user = userDAO.saveOrUpdate(user);
		if (null == user) {
			return new ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User validUser = userDAO.login(user);
		if (validUser == null) {
			Error error = new Error("Invalid credentials.. please enter valid username and password");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		} else {
			session.setAttribute("user", validUser);

			System.out.println(validUser.getEmail());
			System.out.println(validUser.getUsername());
			User user1 = (User) session.getAttribute("user");
			System.out.println(user1.getRole());
			System.out.println(user1.getContact());
			return new ResponseEntity<User>(validUser, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error("Unauthorized user.. Please Login..");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		} else {
			// user.setOnline(false);
			userDAO.saveOrUpdate(user);
			session.removeAttribute("user");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
}
}
