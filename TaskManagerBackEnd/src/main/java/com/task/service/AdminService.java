package com.task.service;

import java.util.List;

import com.task.exception.AdminException;
import com.task.exception.AuthorizeException;
import com.task.exception.SprintException;
import com.task.exception.TaskException;
import com.task.model.Admin;
import com.task.model.LoginDTO;
import com.task.model.Sprint;
import com.task.model.Task;
import com.task.model.TaskStatus;

public interface AdminService {
	public Admin registerAdmin(Admin admin) throws AdminException;
	
	public Admin loginAdmin(LoginDTO dto) throws AdminException,AuthorizeException;
	
	public Sprint createSprint(Sprint sprint, String uId) throws AdminException,AuthorizeException;
	
	public Task addTaskToSprint(Integer sprintId, String uId, Task task) throws AdminException,SprintException,AuthorizeException;

    public Task changeTaskAssignee(Integer taskId, String uId, Integer userId) throws AdminException, TaskException, AuthorizeException;

    public Task changeTaskStatus(Integer taskId, String uId, TaskStatus status) throws TaskException, AdminException, AuthorizeException;

    public List<Task> getAllTasksInSprint(Integer sprintId, String uId) throws TaskException,AdminException,AuthorizeException,SprintException;

    public List<Task> getAllTasksAssignedToUser(Integer userId, String uId) throws TaskException, AdminException, AuthorizeException;

    public String logoutAdmin(String uId) throws AuthorizeException,AdminException;
}
