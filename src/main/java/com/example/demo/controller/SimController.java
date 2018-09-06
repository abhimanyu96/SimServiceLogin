package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Sim;
import com.example.demo.domain.User;


@Controller
@RequestMapping("/activation.*")

public class SimController {
	
	
	@Autowired
	private Environment environment;
	private final RestTemplate restTemplate;
	
	
	public SimController(RestTemplateBuilder restTemplateBuilder) {
    	this.restTemplate = restTemplateBuilder.build();
    }

	@Autowired
	private Validator validator1;
	

	//@Autowired
	//Utils ut;
	
	public Validator getValidator1() {
		return validator1;
	}

	public void setValidator1(Validator validator1) {
		this.validator1 = validator1;
	}
	
	@InitBinder
	public void intitBinder(WebDataBinder binder){
		
		binder.setValidator(validator1);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String gotoActivation(ModelMap m, HttpServletRequest request){
		
		
		Sim sim1 = new Sim();
		m.addAttribute("sim", sim1);
		
		return "verification";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getStatus(@ModelAttribute @Valid Sim sim1,BindingResult result , ModelMap m, HttpServletRequest request,
																	HttpServletResponse response){
		
		
		
		if(result.hasErrors())return new ModelAndView("verification", "msg", " ");
		
		String email=(String)request.getSession().getAttribute("email");
		

		User currentUser= restTemplate.getForObject( environment.getProperty("RestUrl") + "/RegisterAPI/" + email, User.class);
	
		
		
		String name =  currentUser.getFname();
		String sim =  currentUser.getSim()==null?" ":currentUser.getSim().getServiceNumber();
		if (name.equals("")) name ="User";
		
		m.addAttribute("name",name);
		m.addAttribute("sim",sim);
		
		
		request.getSession().setAttribute("email", email);
		String url = environment.getProperty("RestUrl")+"/ActivateAPI/"+email+"/"+sim1.getSimNumber()+"/"+sim1.getServiceNumber();
		
		Boolean res = restTemplate.getForObject(url, Boolean.class);
		
		if(res)
		{
	
			return new ModelAndView("verificationSuccess","message","successful");  
		}
		return new ModelAndView("verification","message","invalid");
		
	}
	
	
}