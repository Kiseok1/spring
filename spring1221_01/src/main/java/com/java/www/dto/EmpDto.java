package com.java.www.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpDto {

	private String employee_id;
	private String emp_name;
	private String email;
	private String phone_number;
	private Timestamp hire_date;
	private int salary;
	private int manager_id;
	private int commission_pct;
	private Timestamp retire_date;
	private int department_id;
	private String job_id;
	private Timestamp create_date;
	private Timestamp update_date;
}
