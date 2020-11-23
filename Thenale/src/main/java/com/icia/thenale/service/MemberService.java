package com.icia.thenale.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.thenale.dao.MemberDAO;
import com.icia.thenale.dto.CompanyDTO;
import com.icia.thenale.dto.MemberDTO;
import com.icia.thenale.dto.SpotDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mDAO;
	private ModelAndView mav;

	@Autowired
	private HttpSession session;

	// 회원가입
	public ModelAndView memberJoin(MemberDTO member) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile mfile = member.getMfile();
		String m_profile = mfile.getOriginalFilename();

		String savePath = "D:\\source\\Spring\\Thenale\\src\\main\\webapp\\resources\\profile\\" + m_profile;
		if (!mfile.isEmpty()) {
			mfile.transferTo(new File(savePath));
		}

		member.setM_profile(m_profile);
		int result = mDAO.memberJoin(member);
		if (result > 0)
			mav.setViewName("memberv/memberlogin");
		else
			mav.setViewName("fail");
		return mav;
	}

	// 로그인
	public ModelAndView memberLogin(MemberDTO member) {
		mav = new ModelAndView();
		String loginId = mDAO.memberLogin(member);
		if (loginId != null) {
			session.setAttribute("loginId", loginId);
			mav.setViewName("redirect:/index");
		} else {
			mav.setViewName("indexfail");
		}

		return mav;
	}

	// mypage
	public ModelAndView memberView(String m_id) {
		mav = new ModelAndView();
		MemberDTO mDTO = mDAO.memberView(m_id);
		
		mav.addObject("mDTO", mDTO);
		mav.setViewName("memberv/mypage");
		return mav;
	}

	// 회원 정보 수정(View 불러오기)
	public ModelAndView memberUpdate(String m_id) {
		mav = new ModelAndView();
		MemberDTO mDTO = mDAO.memberView(m_id);
		
		mav.addObject("mDTO", mDTO);
		mav.setViewName("memberv/memberupdate");
		return mav;
	}

	// 회원 정보 수정
	public ModelAndView memberUpdateProcess(MemberDTO member) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile mfile = member.getMfile();
		String m_profile = mfile.getOriginalFilename();
		String m_id = member.getM_id();
		String savePath = "D:\\source\\Spring\\Thenale\\src\\main\\webapp\\resources\\profile\\" + m_profile;
		if (!mfile.isEmpty()) {
			mfile.transferTo(new File(savePath));
		}

		member.setM_profile(m_profile);
		int result = mDAO.memberUpdate(member);
		if(result > 0)
			mav.setViewName("redirect:/mypage?m_id=" + m_id);
		return mav;
	}


}
