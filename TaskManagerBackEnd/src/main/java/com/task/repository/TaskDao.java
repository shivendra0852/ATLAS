package com.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.Sprint;
import com.task.model.Task;
import com.task.model.User;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer>{
	public List<Task> findBySprint(Sprint sprint);
	
	public List<Task> findByAssignee(User assignee);
}
