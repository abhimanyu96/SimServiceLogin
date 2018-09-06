package com.example.demo.controller;

import javax.security.auth.login.AppConfigurationEntry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.LoginDetails;
import com.example.demo.domain.User;


@Controller
@RequestMapping("existinguser.*")
//@RibbonClient(name="custribbon")
public class ExistingUserController {
	

	@Autowired
	private Environment environment;
	private final RestTemplate restTemplate;
	
	
	public ExistingUserController(RestTemplateBuilder restTemplateBuilder) {
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
	public void intitBinder(WebDataBinder binder){
		
		binder.setValidator(validator1);
	}
	
	

	@RequestMapping(name = "/existinguser.*", method = RequestMethod.POST)
	public ModelAndView existingUser(ModelMap m, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("loginDetails") LoginDetails loginDetails) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String sim="";
		//String value1=App
		String url= environment.getProperty("RestUrl") + "/SigninAPI/" + email+"/"+ password;
		//System.out.println(url);
		String s = restTemplate.getForObject(url, String.class);
		//String email= request.getSession().setAttribute("email", email);
		//System.out.println(s);
		
		if(s.equals("OK"))		
		{
			User u = restTemplate.getForObject( environment.getProperty("RestUrl") + "/RegisterAPI/" + email, User.class);
			String name =  u.getFname();
			if(u.getSim()!=null)
				sim = u.getSim().getServiceNumber();
			//System.out.println("Working");
			if (name.equals("")) name ="User";
			if (sim == null) sim =" ";
			m.addAttribute("name",name);
			m.addAttribute("sim",sim);
			request.getSession().setAttribute("email", email);
			return new ModelAndView("plans", "msg", "welcome");
			
		}
		
		return new ModelAndView("existinguser", "msg", s);
		//return new ModelAndView("existinguser", "msg", "Username or passowrd is incorrect");
		
		
	//	return "redirect:/existinguser.htm?msg=The+Email+or+password+is+incorrect";
		
	
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView redirectexistingUser(HttpServletRequest request, HttpServletResponse response, ModelMap m) {
	
		LoginDetails loginDetails = new LoginDetails();
		m.addAttribute("loginDetails", loginDetails);
		
		
		return new ModelAndView("existinguser", "message", "");
	}
}
