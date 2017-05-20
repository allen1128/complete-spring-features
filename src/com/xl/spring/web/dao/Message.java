package com.xl.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.xl.spring.web.validation.ValidEmail;

@Entity
@Table(name="messages")
public class Message implements Serializable{
	private static final long serialVersionUID = -825455180662805687L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=5, max=100)
	private String subject;
	
	@Size(min=5, max=1000)
	private String content;
	
	@ValidEmail
	private String email;
	
	@Size(min=6, max=60)
	private String name; //sender name
	private String username;
	
	/*@ManyToOne
	@JoinColumn(name="username")
	private User user;*/
	
	public Message() {
	}
	
	public Message(String subject, String content, String email, String name, String username) {
		super();
		this.subject = subject;
		this.content = content;
		this.email = email;
		this.name = name;
		this.username = username;
		//this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + ", content=" + content + ", email=" + email + ", name="
				+ name + ", username=" + username + "]";
	}
	
	
}
