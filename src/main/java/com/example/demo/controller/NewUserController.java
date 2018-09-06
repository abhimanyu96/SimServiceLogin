package com.example.demo.controller;


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

import com.example.demo.domain.User;

@Controller
@RequestMapping("/newuser.*")

public class NewUserController {
	
	@Autowired
	private Environment environment;
	private final RestTemplate restTemplate;
	
	
	public NewUserController(RestTemplateBuilder restTemplateBuilder) {
    	this.restTemplate = restTemplateBuilder.build();
    }
    

	@Autowired
	private Validator validator1;
	

	public Validator getValidator1() {
		return validator1;
	}

	public void setValidator1(Validator validator1) {
		this.validator1 = validator1;
	}

	@InitBinder
	public void intitBinder(WebDataBinder binder) {
		
		binder.setValidator(validator1);
	}

	@RequestMapping(name = "/newuser.*", method = RequestMethod.POST)
	public String newUser(ModelMap m, @ModelAttribute @Valid User user,
			BindingResult result) {

		
		String view= null;


		if (result.hasErrors()) {
			
			m.put(BindingResult.class.getName() + ".user", result);
		
			view= "newuser";
		}
		

			String restUrl=environment.getProperty("RestUrl");
		
		
		User currentUser= restTemplate.getForObject( restUrl + "/RegisterAPI/" + user.getEmail(), User.class);
	
		
		
		if(currentUser != null)
		{
		
			view ="redirect:/newuser.htm?msg=The+Email+already+exists";
		}
		
		else  if (currentUser == null) {
			
			
			try
			{
				
				String URI=restUrl+"/RegisterAPI/";
				
				
			Boolean res= restTemplate.postForObject( URI, user , Boolean.class );
			
			if ( res)	
				 view= "redirect:/existinguser.htm";	
			}
			
			catch(Exception e)
			{view= "redirect:/newuser.htm?msg=Your+registration+failed+,+Sorry+for+inconvenience";
				e.printStackTrace();
			}
			 
			 
			
		}
		
		return view;
	
	}

	@RequestMapping(name = "/newuser.*", method = RequestMethod.GET)
	public ModelAndView redirectnewUser(ModelMap m) {

		
		
		User user = new User();
		ModelAndView mv=new ModelAndView("newuser", "message", "");
		try {
		
		m.addAttribute("user", user);
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return mv;
	}
	

}
