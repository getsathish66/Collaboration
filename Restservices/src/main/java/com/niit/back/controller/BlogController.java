package com.niit.back.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.back.dao.BlogDAO;
import com.niit.back.model.Blog;

@RestController
public class BlogController {

	@Autowired
	BlogDAO blogDAO;

	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> Blog() {
		List<Blog> listblog = blogDAO.list();

		return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
	}

	@PostMapping("/blog")
	public ResponseEntity<Blog> insertBlog(@RequestBody Blog blog)
	{
		blog.setCreatedate(new Date());
		blog.setStatus("NA");
		blog.setLikes(0);
		
		blogDAO.insert(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value = "/blog/{blogid}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBlog(@PathVariable("blogid") int blogid) {
		blogDAO.delete(blogid);
		return new ResponseEntity<String>("Deleted Blog Successfully", HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/blog/{blogid}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("blogid") int blogid, @RequestBody Blog blog) {
		blog = blogDAO.saveOrUpdate(blog);
		if (null == blog) {
			return new ResponseEntity("No Blog found for ID " + blogid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blog, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/blog/{blogid}", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlog(@PathVariable("blogid") int blogid) {

		Blog blog = blogDAO.getAllBlog(blogid);
		if (blog == null) {
			return new ResponseEntity("No Blog found for ID " + blogid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blog, HttpStatus.OK);
	}
}
