package com.icia.memboard.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardDTO {
	private int bnumber;
	private String bid;
	private String bpassword;
	private String btitle;
	private String bcontents;
	private String bdate;
	private String bhits;

	private MultipartFile bfile;
	private String bfilename;

}
