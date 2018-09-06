package com.example.demo.functions;


import org.springframework.stereotype.Component;



@Component
public class Utils {

	
	
	public Utils() {
		
		

	}


/*
	public boolean isLoggedIn(HttpServletRequest request) {
		if (request.getSession().getAttribute("email") != null)
			return true;
		return false;
	}
	*/

	public long simGen() {
		while (true) {
			long numb = (long) (Math.random() * 100000000 * 1000000);
																		
			if (String.valueOf(numb).length() == 13)
				return numb;
		}
	}
	
	public long serviceGen() {
		while (true) {
			long numb = (long) (Math.random() * 100000 * 1000000);
																		
			if (String.valueOf(numb).length() == 10)
				return numb;
		}
	}
	
	
}
