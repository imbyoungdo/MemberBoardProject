package com.icia.thenale.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.thenale.dao.HotelDAO;
import com.icia.thenale.dto.CompanyDTO;
import com.icia.thenale.dto.HotelDTO;

@Service
public class HotelService {

	@Autowired
	private HotelDAO hDAO;
	private ModelAndView mav;
	@Autowired
	private HttpSession session;

	// 호텔 권한 부여 및 정보 넘겨주기
	public ModelAndView hotelAddmission(CompanyDTO company) {
		mav = new ModelAndView();
		int hotelAddmitionResult = hDAO.hotelAddmission(company);
		if (hotelAddmitionResult > 0) {
			mav.setViewName("companyv/companyview");
		} else {
			mav.setViewName("fail");
		}
		return mav;
	}
	
	// 호텔 리스트 츨력
	public ModelAndView hotelareaselect(String h_area) {
		mav = new ModelAndView();
		List<HotelDTO> hList = new ArrayList<HotelDTO>();
		hList = hDAO.hotelList(h_area);
		mav.addObject("hList", hList);
		mav.setViewName("companyv/hotellist");
		return mav;
	}

}
