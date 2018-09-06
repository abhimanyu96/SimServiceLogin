package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RestController
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment env;

	public UserServiceImpl() {
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userRepository.saveAndFlush(user);
	}

	@Override
	public void removeUser(String email) {
		// TODO Auto-generated method stub

	}

	public String findUserPlanService(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserPlan(email);
	}

	@Override
	public User findUser(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public User checkCredentials(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public void updateUserAdd(String state, String pincode, String email) {
		// TODO Auto-generated method stub
		userRepository.updateAddress(state, pincode, email);
	}

	@Override
	public void updateSimStat(String status, String email) {
		// TODO Auto-generated method stub
		userRepository.updateSimStatus(status, email);
	}

	public String getSim(String email) {

		User u = findUser(email);
		if (u.getSim() == null)
			return null;
		return u.getSim().getServiceNumber();
	}

	public int authenticateLogin(String email, String password) {
		User user = findUser(email);
		int i = 0;
		System.out.println(user);

		if (user == null) {
			System.out.println(env.getProperty("ExistingUser.NO_EMAIL"));
			i = 2;
			return i;
		} else if (!(user.getPassword().equals(password))) {
			System.out.println(env.getProperty("ExistingUser.INCORRECT_PASSWORD"));
			i = 1;
			return i;

		}
		System.out.println(env.getProperty("ExistingUser.SUCCESSFUL_LOGIN"));

		return i;
	}

	@RequestMapping(value = "RegisterAPI/{email}", method = RequestMethod.GET)
	public User getUser(@PathVariable String email) throws Exception {

		return findUser(email);
	}

	@RequestMapping(value = "SigninAPI/{email}/{password}", method = RequestMethod.GET)
	public String signIn(@PathVariable("email") String email, @PathVariable("password") String password)
			throws Exception {

		int i = authenticateLogin(email, password);
		if (i == 0)
			return env.getProperty("ExistingUser.SUCCESSFUL_LOGIN");
		else if (i == 1)
			return env.getProperty("ExistingUser.INCORRECT_PASSWORD");
		else if (i == 2)
			return env.getProperty("ExistingUser.NO_EMAIL");
		else
			return "Server Error";
	}

	@RequestMapping(value = "RegisterAPI", method = RequestMethod.POST)
	public ResponseEntity<Boolean> saveUser(@RequestBody User user) throws Exception {

		Boolean result = true;
		try {
			insertUser(user);
		} catch (Exception e) {
			result = false;
		}

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
