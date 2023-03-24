package com.task.service;

import java.util.List;

import com.task.exception.AuthorizeException;
import com.task.exception.UserException;
import com.task.model.LoginDTO;
import com.task.model.Task;
import com.task.model.User;

public interface UserService {
	public User registerUser(User user) throws UserException;
	
	public User loginUser(LoginDTO dto) throws AuthorizeException, UserException;
	
	public List<Task> getAssignedTask(String uId) throws AuthorizeException;
	
	public String logoutUser(String uId) throws AuthorizeException;
}
