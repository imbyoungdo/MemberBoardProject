package com.icia.thenale.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.thenale.dto.MemberDTO;
import com.icia.thenale.dto.SpotDTO;
import com.icia.thenale.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService mService;
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;

	// index 화면 불러오기
	@RequestMapping(value = "/")
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/index")
	public String gohome() {
		return "index";
	}

	// 가입 페이지 이동
	@RequestMapping(value = "/memberjoinform")
	public String join() {
		return "memberv/memberjoin";
	}

	// 회원가입
	@RequestMapping(value = "/memberjoin")
	public ModelAndView memberJoin(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		mav = mService.memberJoin(member);
		return mav;
	}

	// 로그인 페이지 이동
	@RequestMapping(value = "/memberloginform")
	public String login() {
		return "memberv/memberlogin";
	}

	// 로그인
	@RequestMapping(value = "/memberlogin")
	public ModelAndView memberLogin(@ModelAttribute MemberDTO member) {
		mav = mService.memberLogin(member);
		return mav;
	}

	// 내 정보 보러가지
	@RequestMapping(value = "/mypage")
	public ModelAndView memberView(@RequestParam("m_id") String m_id) {
		mav = mService.memberView(m_id);
		return mav;
	}

	// 정보 수정하기
	@RequestMapping(value = "/updateform")
	public ModelAndView memberUpdate(@RequestParam("m_id") String m_id) {
		mav = mService.memberUpdate(m_id);
		return mav;
	}

	// 멤버 수정 목록
	@RequestMapping(value = "/memberupdateprocess")
	public ModelAndView memberUpdateProcess(@ModelAttribute MemberDTO member)
			throws IllegalStateException, IOException {
		mav = mService.memberUpdateProcess(member);
		return mav;
	}
	
	// 로그아웃
	@RequestMapping(value = "/memberlogout")
	public String memberLogout() {
		session.invalidate();
		return "index";
	}



}
