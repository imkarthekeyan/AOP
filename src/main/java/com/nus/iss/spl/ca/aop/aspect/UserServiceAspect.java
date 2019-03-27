package com.nus.iss.spl.ca.aop.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.nus.iss.spl.ca.aop.dao.User;

@Aspect
@Configuration
public class UserServiceAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceAspect.class);

	// Create User
	// @Before - Executed only when a method start to trigger. (CreateUser)
	@Before(value = "execution(* com.nus.iss.spl.ca.aop.service.UserService.createUser(..)) and args(username, password)")
	public void logBeforeCreateUser(JoinPoint joinPoint, String username, String password) {
		LOGGER.info("Before Method Execution:" + joinPoint.getSignature().getName());
		LOGGER.info("Creating User: Name - " + username + " & Password - " + password.replaceAll(".", "*"));
	}

	// @After - Executed in two situations - when a method executes successfully or it throws an exception. (CreateUser)
	@After(value = "execution(* com.nus.iss.spl.ca.aop.service.UserService.createUser(..)) and args(username, password)")
	public void afterAdvice(JoinPoint joinPoint, String username, String password) {
		LOGGER.info("After method:" + joinPoint.getSignature().getName());
		LOGGER.info("Successfully: Created User with name - " + username + " & password - " + password.replaceAll(".", "*"));
	}

	// Authenticate User
	// @Before - Executed only when a method start to trigger (AuthenticateUser)
	@Before(value = "execution(* com.nus.iss.spl.ca.aop.service.UserService.authenticateUser(..)) and args(username, password)")
	public void logbeforeAuthenticateUser(JoinPoint joinPoint, String username, String password) {
		LOGGER.info("Before Method Execution:" + joinPoint.getSignature().getName());
		LOGGER.info("Authentication User: Name - " + username);
	}

	// @AfterThrowing - Executed only when a method throws an exception (AuthenticateUser)
	@AfterThrowing ("execution(* com.nus.iss.spl.ca.aop.service.UserService.authenticateUser(..))")
	public void logAfterAuthenticateUserException() {
		LOGGER.info("AuthenicationFailedException Triggered!");
	}

	// @AfterReturning - Executed only when a method executes successfully. (AuthenticateUser)
	@AfterReturning(value = "execution(* com.nus.iss.spl.ca.aop.service.UserService.authenticateUser(..)) and args(username)")
	public void logAfterAuthenticateUserSuccess(JoinPoint joinPoint, String username) {
		LOGGER.info("After method:" + joinPoint.getSignature().getName());
		LOGGER.info("Successfully: Authenticate User with name - " + username);
	}

	// Delete User
	// @AfterThrowing - Executed only when a method throws an exception (DeleteUser)
	@AfterThrowing ("execution(* com.nus.iss.spl.ca.aop.service.UserService.deleteUser(..))")
	public void logAfterDeleteUserException() {
		LOGGER.info("UserNotFoundException Triggered!");
	}

	// @AfterReturning - Executed only when a method executes successfully. (DeleteUser)
	@AfterReturning(value = "execution(* com.nus.iss.spl.ca.aop.service.UserService.deleteUser(..)) and args(username)")
	public void logAfterDeleteUserSuccess(JoinPoint joinPoint, String username) {
		LOGGER.info("After method:" + joinPoint.getSignature().getName());
		LOGGER.info("Successfully: Deleted User with name - " + username);
	}

	// Get All Users
	// @AfterReturning - Executed only when a method executes successfully. (GetAllUsers)
	@AfterReturning(value = "execution(* com.nus.iss.spl.ca.aop.service.UserService.getAllUsers(..))", returning = "users")
	public void logGetAllUsersAfterSuccess(JoinPoint joinPoint, List<User> users) {
		LOGGER.info(" Check for user access ");
		LOGGER.info(" Allowed execution for {}", joinPoint);
		System.out.println("List of Users:");
		for (int i = 0; i < users.size(); i++) {
			User currentUser = users.get(i);
			System.out.println((i + 1) + "." +  currentUser.getUsername());
		}
		LOGGER.info("Method Execution Name:" + joinPoint.getSignature().getName());
	}
}
