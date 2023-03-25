package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Admin;
import com.task.model.LoginDTO;
import com.task.model.Sprint;
import com.task.model.Task;
import com.task.model.TaskStatus;
import com.task.model.User;
import com.task.service.AdminService;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/registerAdmin")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin){
        Admin newAdmin = adminService.registerAdmin(admin);
        return new ResponseEntity<Admin>(newAdmin,HttpStatus.CREATED);
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<Admin> loginAdmin(@RequestBody LoginDTO dto){
        Admin admin = adminService.loginAdmin(dto);
        return new ResponseEntity<Admin>(admin,HttpStatus.OK);
    }

    @PostMapping("/sprint")
    public ResponseEntity<Sprint> createSprint(@RequestBody Sprint sprint, @RequestParam String uId){
        Sprint newSprint = adminService.createSprint(sprint, uId);
        return new ResponseEntity<Sprint>(newSprint,HttpStatus.OK);
    }

    @PostMapping("/task/{sprintId}")
    public ResponseEntity<Task> addTaskToSprint(@PathVariable Integer sprintId, @RequestParam String uId, @RequestBody Task task){
        Task newTask = adminService.addTaskToSprint(sprintId, uId, task);
        return new ResponseEntity<Task>(newTask,HttpStatus.OK);
    }

    @PutMapping("/task/assignee/{taskId}")
    public ResponseEntity<Task> changeTaskAssignee(@PathVariable Integer taskId, @RequestParam String uId, @RequestParam Integer userId){
        Task updatedTask = adminService.changeTaskAssignee(taskId, uId, userId);
        return new ResponseEntity<Task>(updatedTask,HttpStatus.OK);
    }

    @PutMapping("/task/status/{taskId}")
    public ResponseEntity<Task> changeTaskStatus(@PathVariable Integer taskId, @RequestParam String uId, @RequestParam TaskStatus status){
        Task updatedTask = adminService.changeTaskStatus(taskId, uId, status);
        return new ResponseEntity<Task>(updatedTask,HttpStatus.OK);
    }

    @GetMapping("/tasks/sprint/{sprintId}")
    public ResponseEntity<List<Task>> getAllTasksInSprint(@PathVariable Integer sprintId, @RequestParam String uId){
        List<Task> tasks = adminService.getAllTasksInSprint(sprintId, uId);
        return new ResponseEntity<List<Task>>(tasks,HttpStatus.OK);
    }

    @GetMapping("/tasks/user/{userId}")
    public ResponseEntity<List<Task>> getAllTasksAssignedToUser(@PathVariable Integer userId, @RequestParam String uId){
        List<Task> tasks = adminService.getAllTasksAssignedToUser(userId, uId);
        return new ResponseEntity<List<Task>>(tasks,HttpStatus.OK);
    }

    @PostMapping("/logoutAdmin")
    public ResponseEntity<String> logoutAdmin(@RequestParam String uId){
        String res = adminService.logoutAdmin(uId);
        return new ResponseEntity<String>(res,HttpStatus.OK);
    }
	
}
