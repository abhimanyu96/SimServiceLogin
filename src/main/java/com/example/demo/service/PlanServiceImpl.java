package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Plan;

import com.example.demo.repository.PlanRepository;


@RestController
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository planRepository;
	
	public PlanServiceImpl() {}
	
	@Override
	public Plan getPlanFromId(int id) {
		// TODO Auto-generated method stub
		return planRepository.findById(id);
	}
	@Override
	public void insertPlan(Plan p) {
		// TODO Auto-generated method stub
		planRepository.saveAndFlush(p);
	}
	

	@RequestMapping(value = "PlanAPI/{id}", method = RequestMethod.GET)
    public Plan getUser(@PathVariable String id ) throws Exception{
       
        return getPlanFromId(Integer.parseInt(id));
    }
	

}
