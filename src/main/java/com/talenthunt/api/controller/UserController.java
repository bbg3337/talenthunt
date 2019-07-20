package com.talenthunt.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.bo.Message;
import com.talenthunt.api.enums.UserType;
import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Company;
import com.talenthunt.api.model.User;
import com.talenthunt.api.repository.UserRepository;
import com.talenthunt.api.service.CommonService;

/**
 * The type User controller.
 *
 * @author Harmohan
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CommonService commonService;
  /**
   * Get all users list.
   *
   * @return the list
   */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

  /**
   * Gets users by id.
   *
   * @param userId the user id
   * @return the users by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
      throws ResourceNotFoundException {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
    return ResponseEntity.ok().body(user);
  }

  /**
   * Create user user.
   *
   * @param user the user
   * @return the user
 * @throws Exception 
   */
	@PostMapping("/users")
	public Message createUser(@Valid @RequestBody User user) throws Exception {
	  	Message msg = new Message();
		user.setPassword(CommonService.generatePassword(10));
	  	user.setUserType(UserType.Subscription);
	  	user = userRepository.save(user);
	  	if(user != null){
	  		if(commonService.isListEmpty(userRepository.getByEmailId(user.getEmail()))){
		  		msg.setStatus("Error");
		  		msg.setMessage("Duplicate Email id");
		  		return msg;
		  	}
	  		msg.setStatus("Success");
	  		try {
				commonService.sendEmail(user.getEmail(), "Access Your Login", "Your Password is : "+user.getPassword());	
			}catch(MailAuthenticationException e){
				throw new Exception("User Created but email not send due to Email credential are invelid");
			}catch (Exception e) {
				throw e;
			}
	  	}
	  	msg.setMessage("User created and password sent in email.");
		return  msg;
	}

  /**
   * Update user response entity.
   *
   * @param userId the user id
   * @param userDetails the user details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
      throws ResourceNotFoundException {

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    user.setEmail(userDetails.getEmail());
    user.setLastName(userDetails.getLastName());
    user.setFirstName(userDetails.getFirstName());
    user.setUpdatedAt(new Date());
    final User updatedUser = userRepository.save(user);
    return ResponseEntity.ok(updatedUser);
  }

  /**
   * Delete user map.
   *
   * @param userId the user id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/user/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    userRepository.delete(user);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
  
  	@RequestMapping(value = "/Login", params = { "userName", "password" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> loginMethod(@RequestParam String userName, @RequestParam String password)
			throws ResourceNotFoundException {		
  		System.out.println("UserController.loginMethod() userName : " + userName);
		boolean flag = true;	
		User user = new User();
		for (User users : userRepository.getByEmailId(userName)) {
			if (users.getEmail().equals(userName) && users.getPassword().equals(password)) {
				flag = false;
				user.setId(users.getId());
				user.setEmail(users.getEmail());				
				return ResponseEntity.ok(users);
			}
		}
		if (flag) {
			throw new ResourceNotFoundException("User not found");
		}
		return ResponseEntity.ok(user);
	}
  	
  	@RequestMapping(value = "/forgetPassword", params = { "userName"}, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Message> loginMethod(@RequestParam String userName)
			throws Exception {	
  		Message message = new Message();
		boolean flag = true;	
		//User user = new User();
		for (User users : userRepository.getByEmailId(userName)) {
			flag = false;	
			users.setPassword(CommonService.generatePassword(10));
			try {
				commonService.sendEmail(users.getEmail(), "Forget Password", "Your Password is : "+users.getPassword());
				message.setStatus("Success");
			}catch(MailAuthenticationException e){
				throw new Exception("User Created but email not send due to Email credential are invelid");
			}catch (Exception e) {
				throw e;
			}
			userRepository.save(users);
		}
		if (flag) {
			throw new ResourceNotFoundException("User not found");
		}
		message.setMessage("New password sent in users email id.");
		return ResponseEntity.ok(message);
	}
  	
  	@PostMapping("/users/createUserByCompany/{companyId}")
  	public User createUserByCompany(@Valid @RequestBody User user,@PathVariable(value = "companyId") Long companyId){
  		user.setCompanyId(new Company(companyId));
  		return userRepository.save(user);
  	}
  	
  	@GetMapping("/users/company/{companyId}")
    public List<User> getUsersByCompany(@PathVariable(value = "companyId") Long companyId)
        throws ResourceNotFoundException {
      List<User> users = userRepository.getByCompanyId(companyId);
      return users;
    }
}
