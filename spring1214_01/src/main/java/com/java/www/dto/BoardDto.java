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
public class BoardDto {

	private int bno=0;
	private String btitle;
	private String bcontent;
	private Timestamp bdate;
	private String id;
	private int bgroup;
	private int bstep;
	private int bindent;
	private int bhit;
	private String bfile;
	
	public void setBno(String bno) {
		if(bno==null || bno.equals("")) {
			this.bno=0;
		} else {
			this.bno= Integer.parseInt(bno);
		}
	}
	
}
