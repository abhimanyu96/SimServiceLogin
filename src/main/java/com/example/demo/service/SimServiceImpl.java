package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Sim;
import com.example.demo.repository.SimRepository;

@RestController
public class SimServiceImpl implements SimService{

	public SimServiceImpl() {}
	@Autowired
	private SimRepository simRepository;
	
	@Override
	public Sim findBySimNumberService(String simNumber) {
		// TODO Auto-generated method stub
		return simRepository.findBySimNumber(simNumber);
	}

	@Override
	public Sim findByServiceNumberService(String serviceNumber) {
		// TODO Auto-generated method stub
		return simRepository.findByServiceNumber(serviceNumber);
	}

	@Override
	public Sim findBySimAndServiceNumberService(String simNumber,
			String serviceNumber) {
		// TODO Auto-generated method stub
		return simRepository.findBySimNumberAndServiceNumber(simNumber, serviceNumber);
	}
	
	public void activateSim( String simNumber)
	{
		simRepository.activate(simNumber);
	}
	

	@RequestMapping(value = "ActivateAPI/{email}/{simN}/{serviceN}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getUser(@PathVariable String email ,@PathVariable String simN,@PathVariable String serviceN) throws Exception{
        
      Boolean result = true;
      try {
    	  
    	  if(findBySimAndServiceNumberService(simN, serviceN)!=null)    	{  activateSim(simN);
    	  
    	 
    	  }
    	  
    	  else {result = false;}
      }
      
      catch(Exception e)
      {
    	  result = false;
      }
      
      return new ResponseEntity<Boolean>(result,HttpStatus.OK);
    }
	
	

}
