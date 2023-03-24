package com.task.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrentAdminSession {
	@Id
	@Column(unique = true)
	private Integer id;
	private String uniqueId;
	private LocalDateTime localDateTime;
}
