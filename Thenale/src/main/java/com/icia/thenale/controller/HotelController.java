package com.icia.thenale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.thenale.dto.HotelDTO;
import com.icia.thenale.service.HotelService;

@Controller
public class HotelController {

	@Autowired
	private HotelService hService;
	private ModelAndView mav;
	private HotelDTO hotel;

	// 호텔메인 화면 출력
	@RequestMapping(value = "/hotelmain")
	public String hotelmain() {
		return "companyv/hotelmain";
	}

	// 호텔 지역 선택
	@RequestMapping(value = "/hotelareaselect")
	public ModelAndView hotelareaselect(@RequestParam("h_area") String h_area) {
		mav = hService.hotelareaselect(h_area);
		return mav;
	}

}