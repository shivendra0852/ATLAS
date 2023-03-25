package com.task.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.exception.AdminException;
import com.task.exception.AuthorizeException;
import com.task.exception.SprintException;
import com.task.exception.TaskException;
import com.task.exception.UserException;
import com.task.model.Admin;
import com.task.model.CurrentAdminSession;
import com.task.model.LoginDTO;
import com.task.model.Sprint;
import com.task.model.Task;
import com.task.model.TaskStatus;
import com.task.model.User;
import com.task.repository.AdminDao;
import com.task.repository.AdminSessionDao;
import com.task.repository.SprintDao;
import com.task.repository.TaskDao;
import com.task.repository.UserDao;

import net.bytebuddy.utility.RandomString;


@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Autowired
	private SprintDao sprintDao;
	
	@Autowired
	private TaskDao taskDao;

	@Override
	public Admin registerAdmin(Admin admin) throws UserException {
		Admin registeredAdmin = adminDao.findByMobileNo(admin.getMobileNo());
		
		if(registeredAdmin!=null) {
			throw new AdminException("Admin already registered with this number!");
		}
		
		return adminDao.save(admin);
	}

	@Override
	public Admin loginAdmin(LoginDTO dto) throws AdminException, AuthorizeException {
		Admin existingUser = adminDao.findByMobileNo(dto.getMobileNo());
	    
	    if(existingUser == null) {
	        throw new AuthorizeException("Please register your self first!");
	    }
	    
	    Optional<CurrentAdminSession> validAdmin = adminSessionDao.findById(existingUser.getId());
	    
	    if(validAdmin.isPresent()) {
	        throw new AuthorizeException("Admin already logged in!");
	    }
	    
	    if(existingUser.getPassword().equals(dto.getPassword())) {
	        
	        String key = RandomString.make(6);
	        
	        CurrentAdminSession adminCurrentSession = new CurrentAdminSession(existingUser.getId(), key, LocalDateTime.now());
	        
	        adminSessionDao.save(adminCurrentSession);
	        
	        return existingUser;
	    }
	    else {
	        throw new AuthorizeException("Please enter a valid password!");
	    }

	}

	@Override
	public Sprint createSprint(Sprint sprint, String uId) throws AdminException, AuthorizeException {
		CurrentAdminSession loggedInAdmin = adminSessionDao.findByUniqueId(uId);
	    if (loggedInAdmin == null) {
	        throw new AuthorizeException("User not authorized to create a Sprint");
	    }

	    return sprintDao.save(sprint);
	}


	@Override
	public Task addTaskToSprint(Integer sprintId, String uId, Task task)
			throws AdminException, SprintException, AuthorizeException {
		CurrentAdminSession loggedInAdmin = adminSessionDao.findByUniqueId(uId);
		
		if(loggedInAdmin==null) {
			throw new AuthorizeException("Please login first!");
		}
		
		Optional<Sprint> sprint = sprintDao.findById(sprintId);
		
		if(sprint.isEmpty()) {
			throw new SprintException("No sprint found with this id!");
		}
		
		task.setSprint(sprint.get());
		
		task.setCreatedBy(adminDao.findById(loggedInAdmin.getId()).get());
		
		return taskDao.save(task);
	}

	@Override
	public Task changeTaskAssignee(Integer taskId, String uId, Integer userId)
			throws AdminException, TaskException, AuthorizeException {
		
		Optional<User> user = userDao.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserException("User not found with this id!");
		}
		
		Optional<Task> task = taskDao.findById(taskId);
		
		if(task.isEmpty()) {
			throw new TaskException("Task not found with this id!");
		}
		
		CurrentAdminSession loggedInAdmin = adminSessionDao.findByUniqueId(uId);
		
		if(loggedInAdmin==null || loggedInAdmin.getId()!=task.get().getCreatedBy().getId()) {
			throw new AuthorizeException("You are not authorized to do this operation!");
		}
		
		task.get().setAssignee(user.get());
		
		return taskDao.save(task.get());
	}

	@Override
	public Task changeTaskStatus(Integer taskId, String uId, TaskStatus status)
			throws TaskException, AdminException, AuthorizeException {
		Optional<Task> task = taskDao.findById(taskId);
		
		if(task.isEmpty()) {
			throw new TaskException("Task not found with this id!");
		}
		
		CurrentAdminSession loggedInAdmin = adminSessionDao.findByUniqueId(uId);
		
		if(loggedInAdmin==null || loggedInAdmin.getId()!=task.get().getCreatedBy().getId()) {
			throw new AuthorizeException("You are not authorized to do this operation!");
		}
		task.get().setStatus(status);
		
		return taskDao.save(task.get());
	}

	@Override
	public List<Task> getAllTasksInSprint(Integer sprintId, String uId)
			throws TaskException, AdminException, AuthorizeException, SprintException {
		Optional<Sprint> sprint = sprintDao.findById(sprintId);
		
		if(sprint.isEmpty()) {
			throw new SprintException("No sprint found with this id!");
		}
		
		CurrentAdminSession loggedInAdmin = adminSessionDao.findByUniqueId(uId);
		
		if(loggedInAdmin==null) {
			throw new AuthorizeException("You are not authorized to do this operation!");
		}
		
		List<Task> tasks = taskDao.findBySprint(sprint.get());
		
		if(tasks.size()==0) {
			throw new TaskException("No tasks available!");
		}
		
		else return tasks;
	}

	@Override
	public List<Task> getAllTasksAssignedToUser(Integer userId, String uId)
			throws TaskException, AdminException, AuthorizeException {
		CurrentAdminSession loggedInAdmin = adminSessionDao.findByUniqueId(uId);
		
		if(loggedInAdmin==null) {
			throw new AuthorizeException("You are not authorized to do this operation!");
		}
		
		Optional<User> user = userDao.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserException("No user found with this id!");
		}
		
		List<Task> tasks = taskDao.findByAssignee(user.get());
		
		if(tasks.size()==0) {
			throw new TaskException("No tasks available!");
		}
		
		else return tasks;
	}

	@Override
	public String logoutAdmin(String uId) throws AuthorizeException, AdminException {
		
		CurrentAdminSession loggedInAdmin = adminSessionDao.findByUniqueId(uId);
		
		if(loggedInAdmin==null) {
			throw new AuthorizeException("Something went wrong!");
		}
		
		adminSessionDao.delete(loggedInAdmin);
		return "Logged out successfully!";
	}

}
