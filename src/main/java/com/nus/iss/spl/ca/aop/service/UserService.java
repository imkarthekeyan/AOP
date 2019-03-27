package com.nus.iss.spl.ca.aop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nus.iss.spl.ca.aop.aspect.LogExecutionTime;
import com.nus.iss.spl.ca.aop.dao.User;
import com.nus.iss.spl.ca.aop.exception.AuthenicationFailedException;
import com.nus.iss.spl.ca.aop.exception.UserNotFoundException;

@Service
public class UserService {

	private static final List<User> users = new ArrayList<>();	

	public User createUser(String username, String password) {

		User currentUser = new User();
		currentUser.setUsername(username);
		currentUser.setPassword(password);
		users.add(currentUser);		
		return currentUser;
	}

	@LogExecutionTime
	public List<User> getAllUsers() {
		return users;
	}

	public Boolean deleteUser(String username) {

		boolean isUserFound = false;
		for (int i = 0; i < users.size(); i++) {
			User currentUser = users.get(i);
			if (currentUser.getUsername().equals(username)) {
				users.remove(i);
				isUserFound = true;
				break;
			}
		}

		if (!isUserFound) {
			throw new UserNotFoundException("User with the name: " + username + " (Not Found)");
		}
		return isUserFound;
	}

	public Boolean authenticateUser(String username, String password) {

		boolean isCredentialValid = false;
		for (int i = 0; i < users.size(); i++) {
			User currentUser = users.get(i);
			if (currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password)) {
				isCredentialValid = true;
				break;
			}
		}

		if (!isCredentialValid) {
			throw new AuthenicationFailedException("Invalid Credentials (Username/password)");
		}
		return isCredentialValid;
	}
	
	@LogExecutionTime
	public void serve() throws InterruptedException {
		Thread.sleep(2000);
	}
}
