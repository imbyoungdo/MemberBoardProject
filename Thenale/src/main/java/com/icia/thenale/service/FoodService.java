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

import com.icia.thenale.dao.FoodDAO;
import com.icia.thenale.dao.HotelDAO;
import com.icia.thenale.dto.CompanyDTO;
import com.icia.thenale.dto.FoodDTO;
import com.icia.thenale.dto.HotelDTO;

@Service
public class FoodService {

	@Autowired
	private FoodDAO fDAO;
	private ModelAndView mav;
	@Autowired
	private HttpSession session;

	// 식당 권한 부여 및 정보 넘겨주기
	public ModelAndView foodAddmission(CompanyDTO company) {
		mav = new ModelAndView();
		int foodAddmitionResult = fDAO.fodAdd(company);
		if (foodAddmitionResult > 0) {
			mav.setViewName("companyv/companyview");
		} else {
			mav.setViewName("fail");
		}
		return mav;
	}
	
	// 식당 리스트 츨력
	public ModelAndView foodareaselect(String f_area) {
		mav = new ModelAndView();
		List<FoodDTO> fList = new ArrayList<FoodDTO>();
		fList = fDAO.foodList(f_area);
		mav.addObject("fList", fList);
		mav.setViewName("companyv/foodList");
		return mav;
	}

	public ModelAndView foodView(String f_name) {
		mav = new ModelAndView();
		
		return mav;
	}

}

