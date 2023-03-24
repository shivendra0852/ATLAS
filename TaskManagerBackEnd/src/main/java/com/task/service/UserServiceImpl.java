package com.task.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.exception.AuthorizeException;
import com.task.exception.TaskException;
import com.task.exception.UserException;
import com.task.model.CurrentUserSession;
import com.task.model.LoginDTO;
import com.task.model.Task;
import com.task.model.User;
import com.task.model.UserRole;
import com.task.repository.TaskDao;
import com.task.repository.UserDao;
import com.task.repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Autowired
	private TaskDao taskDao;

	@Override
	public User registerUser(User user) throws UserException {
		User registeredUser = userDao.findByMobileNo(user.getMobileNo());
		
		if(registeredUser!=null) {
			throw new UserException("User already registered with this number!");
		}
		
		user.setRole(UserRole.USER);
		return userDao.save(user);
	}

	@Override
	public User loginUser(LoginDTO dto) throws AuthorizeException, UserException {
		User existingUser = userDao.findByMobileNo(dto.getMobileNo());
	    
	    if(existingUser == null) {
	        throw new AuthorizeException("Please register your self first!");
	    }
	    
	    UserRole role = existingUser.getRole();
	    if(role != UserRole.USER) {
	        throw new AuthorizeException("This login method is only for user not for admin!");
	    }
	    
	    Optional<CurrentUserSession> validUser = userSessionDao.findById(existingUser.getId());
	    
	    if(validUser.isPresent()) {
	        throw new AuthorizeException("User already logged in!");
	    }
	    
	    if(existingUser.getPassword().equals(dto.getPassword())) {
	        
	        String key = RandomString.make(6);
	        
	        CurrentUserSession userCurrentSession = new CurrentUserSession(existingUser.getId(), key, LocalDateTime.now());
	        
	        userSessionDao.save(userCurrentSession);
	        
	        return existingUser;
	    }
	    else {
	        throw new AuthorizeException("Please enter a valid password!");
	    }
	}

	@Override
	public List<Task> getAssignedTask(String uId) throws AuthorizeException {
		CurrentUserSession loggedInUser = userSessionDao.findByUniqueId(uId);
		
		if(loggedInUser==null) {
			throw new AuthorizeException("Something went wrong!");
		}
		
		List<Task> tasks = taskDao.findByAssignee(userDao.findById(loggedInUser.getId()).get());
		
		if(tasks.size()==0) {
			throw new TaskException("No tasks available!");
		}
		
		return tasks;
	}

	@Override
	public String logoutUser(String uId) throws AuthorizeException {
		CurrentUserSession loggedInUser = userSessionDao.findByUniqueId(uId);
		
		if(loggedInUser==null) {
			throw new AuthorizeException("Something went wrong!");
		}
		
		userSessionDao.delete(loggedInUser);
		return "Logged out successfully!";
	}

}
