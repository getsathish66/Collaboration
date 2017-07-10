package com.niit.back.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="forum")
public class Forum {

	@Id
	@GeneratedValue
	private int forumid;
	private String forumname;
	private String forumcontent;
	private int userid;
	private Date createdate;
	private String status;
	
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	public String getForumname() {
		return forumname;
	}
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getForumcontent() {
		return forumcontent;
	}
	public void setForumcontent(String forumcontent) {
		this.forumcontent = forumcontent;
	}
	
	
}
