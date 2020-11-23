package com.icia.thenale.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CompanyDTO {

	private String c_id;
	private String c_password;
	private String c_name;
	private String c_photo;
	private String c_contents;
	private String c_address;
	private String c_y;
	private String c_x;
	private String c_sort;
	private String c_ceo_tel;
	private String c_ceo;
	private String c_area;
	private String c_auth;
	
	private MultipartFile cfile;
}
