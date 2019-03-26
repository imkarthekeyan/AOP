package com.nus.iss.spl.ca.aop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nus.iss.spl.ca.aop.dao.User;
import com.nus.iss.spl.ca.aop.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		User currentUser = userService.createUser(username, password);
		return currentUser != null ? ResponseEntity.status(HttpStatus.OK).body(currentUser) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users != null ? ResponseEntity.status(HttpStatus.OK).body(users) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUser(@RequestParam("username") String username) {
		Boolean isUserDeleted =  userService.deleteUser(username);
		return isUserDeleted ? ResponseEntity.status(HttpStatus.OK).body(isUserDeleted) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<Boolean> authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		Boolean isAuthenticated = userService.authenticateUser(username, password);
		return isAuthenticated ? ResponseEntity.status(HttpStatus.OK).body(isAuthenticated) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
