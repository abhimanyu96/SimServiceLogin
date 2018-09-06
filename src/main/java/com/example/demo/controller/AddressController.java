package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.User;

@Controller
@RequestMapping("/editAddress.*")
public class AddressController {

	
	
	
	@Autowired
	private Environment environment;
	private final RestTemplate restTemplate;
	
	
	public AddressController(RestTemplateBuilder restTemplateBuilder) {
    	this.restTemplate = restTemplateBuilder.build();
    }
    
	
	@RequestMapping(method=RequestMethod.GET)
	public String editAddress(HttpServletRequest request)
	{ 
		
		return "editAddress";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String editedAddress(HttpServletRequest request,ModelMap m)
	{ 
		String email=(String)request.getSession().getAttribute("email");
		
		User currentUser= restTemplate.getForObject( environment.getProperty("RestUrl") + "/RegisterAPI/" + email, User.class);
	
		
		
		String name =  currentUser.getFname();
		String sim =  currentUser.getSim()==null?" ":currentUser.getSim().getServiceNumber();
		if (name.equals("")) name ="User";
	
		m.addAttribute("name",name);
		m.addAttribute("sim",sim);
		
		

		
		currentUser.setPincode( request.getParameter("pincode"));
		currentUser.setState( request.getParameter("state"));
		String URI=environment.getProperty("RestUrl")+"/RegisterAPI/";
		Boolean res= restTemplate.postForObject( URI, currentUser , Boolean.class );
		
		if(!res)return  "editAddress";
		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("plan", (String)request.getSession().getAttribute("plan"));
		return "forward:/plans.htm";
	}
}
