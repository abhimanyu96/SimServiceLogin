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

import com.example.demo.domain.Plan;
import com.example.demo.domain.Sim;
import com.example.demo.domain.User;
import com.example.demo.functions.Utils;


@Controller
@RequestMapping("/plans.*")

public class PlansController {
	

	
	

	@Autowired
	private Environment environment;
	private final RestTemplate restTemplate;
	
	
	public PlansController(RestTemplateBuilder restTemplateBuilder) {
    	this.restTemplate = restTemplateBuilder.build();
    }
	@Autowired
	Utils ut;

	@RequestMapping(method = RequestMethod.GET)
	public String planInit(ModelMap m, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		return "plans";
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public String planDetails(HttpServletRequest request,
			HttpServletResponse response,
			 ModelMap m) {
		
		String email = (String) request.getSession().getAttribute("email");
		User u= restTemplate.getForObject( environment.getProperty("RestUrl") + "/RegisterAPI/" + email, User.class);
	//	System.out.println(u);
//		System.out.println("Working_2");
		if (u != null) {
			Plan p;
			
			
			if (request.getParameter("plan") != null)
				p = restTemplate.getForObject( environment.getProperty("RestUrl") + "/PlanAPI/" +request.getParameter("plan") , Plan.class);
			
			else
				
				p = restTemplate.getForObject( environment.getProperty("RestUrl") + "/PlanAPI/" +request.getSession().getAttribute("plan") , Plan.class);
			
							
			
			Sim s;
			if (u.getSim() == null) {
				s = new Sim();
				s.setSimNumber(String.valueOf(ut.simGen()));
				s.setServiceNumber(String.valueOf(ut.serviceGen()));
			} else {
				s = u.getSim();
			}
			
			if(u.getSim()==null || u.getSim().getStatus()== null){m.addAttribute("simNumber", s.getSimNumber());
			m.addAttribute("msg","Service Number: "+ s.getServiceNumber()+ " your sim is still not activated, please activate sim number");
			}
			else{ m.addAttribute("simNumber","You already have a sim with Sim Number: "+ s.getSimNumber());
			m.addAttribute("msg"," ");
						}
			s.setPlan(p);
			u.setSim(s);
			

			
			String URI=environment.getProperty("RestUrl")+"/RegisterAPI/";
					Boolean res= restTemplate.postForObject( URI, u, Boolean.class );
					if(!res)return "plans";
		
			
			
			String name =  u.getFname();
			Sim sim =  u.getSim();
			String serviceNumber;
			if (name.equals("")) name ="User";
			if (sim == null) serviceNumber =" ";
			else serviceNumber= sim.getServiceNumber();
			m.addAttribute("name",name);
			m.addAttribute("sim",serviceNumber);
			m.addAttribute("state", u.getState());
			m.addAttribute("pincode", u.getPincode());
			m.addAttribute("plandesc", p.getDescription());
			m.addAttribute("planamount", p.getAmount());
			
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("plan", String.valueOf(p.getId()));
			return "confirmation";
		}
		return "redirect:/existinguser.htm";

	}
}