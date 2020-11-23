package com.icia.thenale.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FoodDTO {
	private int f_num;
	private String f_c_id;
	private String f_name;
	private String f_contents;
	private String f_photo;
	private String f_x;
	private String f_y;
	private String f_area;
	private String f_address;
	
	private MultipartFile cfile;

}
