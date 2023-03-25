package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.LoginDTO;
import com.task.model.Task;
import com.task.model.User;
import com.task.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User newUser = userService.registerUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<User> loginUser(@RequestBody LoginDTO dto){
        User user = userService.loginUser(dto);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAssignedTask(@RequestParam String uId){
        List<Task> tasks = userService.getAssignedTask(uId);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<String> logoutUser(@RequestParam String uId){
        String res = userService.logoutUser(uId);
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }
}
