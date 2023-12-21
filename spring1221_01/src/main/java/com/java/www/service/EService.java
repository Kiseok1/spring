package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;

public interface EService {

	ArrayList<EmpDto> selectAll();

	ArrayList<EmpDeptDto> selectAll2();

	ArrayList<EmpDeptDto> selectAll3();

}
