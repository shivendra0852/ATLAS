package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.CurrentAdminSession;


@Repository
public interface AdminSessionDao extends JpaRepository<CurrentAdminSession, Integer>{
	public CurrentAdminSession findByUniqueId(String uId);
}
