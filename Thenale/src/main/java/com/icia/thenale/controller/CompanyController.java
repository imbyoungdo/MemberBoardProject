package com.icia.thenale.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.thenale.api.KakaoJoinApi;
import com.icia.thenale.api.KakaoLoginApi;
import com.icia.thenale.api.NaverJoinApi;
import com.icia.thenale.api.NaverLoginApi;
import com.icia.thenale.dto.CompanyDTO;
import com.icia.thenale.dto.FoodDTO;
import com.icia.thenale.dto.HotelDTO;
import com.icia.thenale.service.CompanyService;
import com.icia.thenale.service.FoodService;
import com.icia.thenale.service.HotelService;

@Controller
public class CompanyController {

	@Autowired
	private KakaoJoinApi kakaoJoinApi;

	@Autowired
	private KakaoLoginApi kakaoLoginApi;

	/*
	 * @Autowired private NaverJoinApi naverJoinApi;
	 * 
	 * @Autowired private NaverLoginApi naverLoginApi;
	 */

	@Autowired
	private CompanyService cService;
	@Autowired
	private FoodService fService;
	@Autowired
	private HotelService hService;
	private ModelAndView mav;
	private FoodDTO food;
	private HotelDTO hotel;

	// �뾽泥� �쉶�썝媛��엯 �럹�씠吏� �씠�룞
	@RequestMapping(value = "/comjoin")
	public String comjoin() {
		return "companyv/companyjoin";
	}

	// �뾽泥� �쉶�썝 濡쒓렇�씤 �럹�씠吏� �씠�룞
	@RequestMapping(value = "/comlogin")
	public String comlogin() {
		return "companyv/companylogin";
	}

	// �뾽泥� �쉶�썝媛��엯
	@RequestMapping(value = "/comjoinform")
	public ModelAndView companyJoin(@ModelAttribute CompanyDTO company) throws IllegalStateException, IOException {
		mav = cService.companyJoin(company);
		return mav;
	}

	// �뾽泥� 濡쒓렇�씤
	@RequestMapping(value = "/companylogin")
	public ModelAndView companyLogin(@ModelAttribute CompanyDTO company) {
		mav = cService.companyLogin(company);
		return mav;
	}

	// admin 愿�由ъ옄 �럹�씠吏�
	@RequestMapping(value = "/manager")
	public ModelAndView CompanyList(@ModelAttribute CompanyDTO company) {
		mav = cService.companyList(company);
		return mav;
	}

	// �뾽泥� �떊泥��꽌 �긽�꽭議고쉶
	@RequestMapping(value = "/companyview")
	public ModelAndView companyView(@RequestParam("c_id") String c_id) {
		mav = cService.companyView(c_id);
		return mav;
	}

	// �뾽泥� �떊泥� �듅�씤
	@RequestMapping(value = "/admission")
	public ModelAndView companyAdminssion(@ModelAttribute CompanyDTO company) {
		String sort = company.getC_sort();
		if (sort.equals("�떇�떦")) {
			mav = fService.foodAddmission(company);
		} else if (sort.equals("�닕諛�")) {
			mav = hService.hotelAddmission(company);
		}
		mav = cService.companyAdminssion(company);
		return mav;
	}

	// �뾽泥� �궘�젣
	@RequestMapping(value = "/companyDelete")
	public ModelAndView companyDelete(@RequestParam(value = "c_id", required = false) String c_id) {
		mav = cService.companyDelete(c_id);
		return mav;
	}

	@RequestMapping(value = "/kakaojoin")
	public ModelAndView kakaoJoin(HttpSession session) {
		String kakaoUrl = kakaoJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("memberv/KakaoLogin2");
		return mav;

	}

	@RequestMapping(value = "/kakaojoinOK")
	public ModelAndView kakaoJoinOK(@RequestParam("code") String code, HttpSession session) {
		JsonNode token = kakaoJoinApi.getAccessToken(code);
		JsonNode profile = kakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
		System.out.println("profile" + profile);
		String m_k_id = profile.get("id").asText();
		mav = new ModelAndView();
		mav.addObject("m_k_id", m_k_id);
		System.out.println(m_k_id);
		mav.setViewName("memberv/memberjoin");
		return mav;
	}

}