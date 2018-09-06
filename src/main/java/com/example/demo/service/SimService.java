package com.example.demo.service;

import com.example.demo.domain.Sim;

public interface SimService {

	public Sim findBySimNumberService(String simNumber);

	public Sim findByServiceNumberService(String serviceNumber);

	public Sim findBySimAndServiceNumberService(String simNumber, String serviceNumber);
	
	public void activateSim(String simNumber);
}
