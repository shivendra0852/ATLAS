package com.task.service;

import java.util.List;

import com.task.exception.AuthorizeException;
import com.task.exception.SprintException;
import com.task.exception.TaskException;
import com.task.exception.UserException;
import com.task.model.LoginDTO;
import com.task.model.Sprint;
import com.task.model.Task;
import com.task.model.TaskStatus;
import com.task.model.User;

public interface AdminService {
	public User registerAdmin(User user) throws UserException;
	
	public User loginAdmin(LoginDTO dto) throws UserException,AuthorizeException;
	
	public Sprint createSprint(Sprint sprint, String uId) throws UserException,AuthorizeException;
	
	public Task createTask(Task task, String uId) throws UserException,AuthorizeException;
	
	public Task addTaskToSprint(Integer sprintId, String uId, Task task) throws UserException,SprintException,AuthorizeException;

    public Task changeTaskAssignee(Integer taskId, String uId, Integer userId) throws UserException, TaskException, AuthorizeException;

    public Task changeTaskStatus(Integer taskId, String uId, TaskStatus status) throws TaskException, UserException, AuthorizeException;

    public List<Task> getAllTasksInSprint(Integer sprintId, String uId) throws TaskException,UserException,AuthorizeException,SprintException;

    public List<Task> getAllTasksAssignedToUser(Integer userId, String uId) throws TaskException, UserException, AuthorizeException;

    public String logoutAdmin(String uId) throws AuthorizeException,UserException;
}
