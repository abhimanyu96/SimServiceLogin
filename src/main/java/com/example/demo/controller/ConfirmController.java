package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/confirm.*")
public class ConfirmController {
	
	
	
	
	@Autowired
	private Environment environment;
	private final RestTemplate restTemplate;
	
	
	public ConfirmController(RestTemplateBuilder restTemplateBuilder) {
    	this.restTemplate = restTemplateBuilder.build();
    }
   
	@RequestMapping(method=RequestMethod.GET)
	public String confirm(HttpServletRequest request,
			HttpServletResponse response,ModelMap m)
	{
		String email=(String)request.getSession().getAttribute("email");

		User currentUser= restTemplate.getForObject( environment.getProperty("RestUrl") + "/RegisterAPI/" + email, User.class);
			
		String name =  currentUser.getFname();
		String sim =  currentUser.getSim()==null?" ":currentUser.getSim().getServiceNumber();
		if (name.equals("")) name ="User";
	
		m.addAttribute("name",name);
		m.addAttribute("sim",sim);
		
		request.getSession().setAttribute("email", email);
		return "plans";
	}
}
