package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.Sprint;

@Repository
public interface SprintDao extends JpaRepository<Sprint, Integer>{

}
