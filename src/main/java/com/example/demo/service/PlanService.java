package com.example.demo.service;

import com.example.demo.domain.Plan;


public interface PlanService {

	public Plan getPlanFromId(int id);
	public void insertPlan(Plan p) ;
}
