package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;

@Mapper
public interface EmpMapper {

	//사원정보 가져오기
	ArrayList<EmpDto> selectAll();

	//사원,부서정보 가져오기
	ArrayList<EmpDeptDto> selectAll2();

	//member,board 정보 가져오기
	ArrayList<EmpDeptDto> selectAll3();

}
