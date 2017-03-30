package com.csc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name="FRESHER")
public class Fresher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="FRESHER_ID")
	private int fresherId;
	
	@Column(name="FULLNAME")
	private String fullName;
	
	@Column(name="EMAIl")
	private String email;
	
	@Column(name="PROFESSION")
	private String profession;
	
	
	public int getFresherId() {
		return fresherId;
	}
	public void setFresherId(int fresherId) {
		this.fresherId = fresherId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Override
	public String toString() {
		return "Fresher [fresherId=" + fresherId + ", fullName=" + fullName + ", email=" + email + ", profession="
				+ profession + "]";
	}
	
	
}
