package com.example.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@Email
	@NotEmpty(message = "Email can't be empty")
	private String email;

	@NotEmpty(message="Password can't be empty")
	private String password;

	@NotEmpty(message = "DOB can't be empty")
	@Pattern(regexp="^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$" , message ="The date should be in dd/MM/YYYY format")
	
	private String dob;

	@Pattern(regexp = "[a-z A-Z]*" ,message="Firstname should contain only Alphabets ")
	private String fname;

	@Pattern(regexp = "[a-z A-Z]*" ,message="Lastname should contain only Alphabets ")
	private String lname;

	private String ids;

	private int idnumber;

	private String state;

	@Pattern(regexp="[0-9]*" , message="Please enter only digits")
	@Size(min=6, max=6, message="Pincode should be of 6 digits")
	private String pincode;

	@OneToOne(cascade = CascadeType.ALL)
	private Sim sim;

	public Sim getSim() {
		return sim;
	}

	public void setSim(Sim sim) {
		this.sim = sim;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(int idnumber) {
		this.idnumber = idnumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
