package com.icia.memboard.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {

	private String mid;
	private String mpassword;
	private String mname;
	private String mdate;
	private String memail;
	private String madress;
	private String mphone;

	private MultipartFile mphoto;
	private String mphotoname;

	private String kakaoId;
	private String naverId;

}
