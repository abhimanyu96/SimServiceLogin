package com.example.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Sim {
	
	
	
	@Pattern(regexp="[0-9]*" , message="Please enter only digits")
	@Size(min=10, max=10, message="Service number should be of 10 digits")
	
	private String serviceNumber;
	
	@Id
	@Size(min=13, max=13, message="Service number should be of 13 digits")
	@Pattern(regexp="[0-9]*" , message="Please enter only digits")
	private String simNumber;
	 
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Plan plan;
	
	public Plan getPlan() {
		return plan;
	}
	
	
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	
	
	public String getSimNumber() {
		return simNumber;
	}
	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/*
	@Id
	@Size(min=13, max=13)
	@NotEmpty
	private String simNumber;
	
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	@Size(min=10, max=10)
	private String serviceNumber;
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Plan plan;
	
	public String getSimNumber() {
		return simNumber;
	}
	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	*/
}
