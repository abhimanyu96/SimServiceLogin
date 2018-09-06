package com.example.demo.service;


import com.example.demo.domain.User;

public interface UserService {

	public void insertUser(User user) ;
	public void removeUser(String email);
	public String findUserPlanService(String email);
	public User findUser(String email);
	
	public User checkCredentials(String email,String password);
	public void updateUserAdd(String state,String pincode,String email);
	public void updateSimStat(String status,String email);
}
