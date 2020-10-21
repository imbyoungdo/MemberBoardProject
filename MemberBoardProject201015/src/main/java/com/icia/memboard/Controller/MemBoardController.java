package com.icia.memboard.Controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memboard.api.KakaoJoinApi;
import com.icia.memboard.api.NaverJoinApi;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.memboard.DTO.BoardDTO;
import com.icia.memboard.DTO.MemberDTO;
import com.icia.memboard.Service.MemBoardService;
import com.icia.memboard.api.KakaoLoginApi;
import com.icia.memboard.api.NaverLoginApi;

@Controller
public class MemBoardController {

	@Autowired
	private KakaoJoinApi kakaoJoinApi;

	@Autowired
	private KakaoLoginApi kakaoLoginApi;

	@Autowired
	private NaverJoinApi naverJoinApi;

	@Autowired
	private NaverLoginApi naverLoginApi;

	@Autowired
	private HttpSession session;

	@Autowired
	private MemBoardService memboardService;

	private ModelAndView mav;

	// 홈 화면
	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}

	// 회원가입 화면
	@RequestMapping(value = "/joinform")
	public String join() {
		return "membov/joinform";
	}

	// 로그인 화면
	@RequestMapping(value = "/loginform")
	public String login() {
		return "membov/login";
	}

	// 회원가입
	@RequestMapping(value = "/memberJoin")
	public ModelAndView signform(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		mav = memboardService.memberSign(member);
		return mav;
	}

	// 아이디 중복확인

	@RequestMapping(value = "/idoverlap")
	public @ResponseBody String idOverlap(@RequestParam("mid") String mid) {
		System.out.println("전달받은 값 : " + mid);
		String resultMsg = memboardService.idOverlap(mid);
		return resultMsg;
	}

	// 로그인
	@RequestMapping(value = "/login")
	public ModelAndView login(@ModelAttribute MemberDTO member) {
		mav = memboardService.memberLogin(member);
		return mav;
	}

	// 회원정보 조회
	@RequestMapping(value = "/memberview")
	public ModelAndView memberView(@RequestParam("mid") String mid) {
		mav = memboardService.memberView(mid);

		return mav;
	}

	// 회원 정보 수정 - 1
	@RequestMapping(value = "/memberupdate")
	public ModelAndView memberUpdate(@RequestParam("mid") String mid) {
		mav = memboardService.membeUpdate(mid);

		return mav;
	}

	// 회원 정보 수정 - 2

	@RequestMapping(value = "/memberUpdateProcess")
	public ModelAndView memberUpdateProcess(@ModelAttribute MemberDTO member) {
		mav = memboardService.memberupdateProcess(member);

		return mav;
	}

	// 관리자 모드(회원목록)
	@RequestMapping(value = "/memberlist")
	public ModelAndView memberlist() {
		mav = memboardService.memberList();
		return mav;
	}

	// 회원 삭제
	@RequestMapping(value = "/memberdelete")
	public ModelAndView memberDelete(@RequestParam("mid") String mid) {
		mav = memboardService.memberDelete(mid);

		return mav;
	}

	// 로그아웃
	@RequestMapping(value = "/memberlogout")
	public String memberLogout() {
		session.invalidate();

		return "membov/login";
	}

	// 카카오 서버 로그인
	@RequestMapping(value = "/kakaojoin")
	public ModelAndView kakaoJoin(HttpSession session) {
		String kakaoUrl = kakaoJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("membov/KagkaoLoin2");
		return mav;

	}

	// 카카오 서버 인증 통과 후 처리
	@RequestMapping(value = "/kakaojoinOK")
	public ModelAndView kakaoJoinOK(@RequestParam("code") String code, HttpSession session) {
		JsonNode token = kakaoJoinApi.getAccessToken(code);
		JsonNode profile = kakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
		System.out.println("profile" + profile);
		String kakaoId = profile.get("id").asText();
		mav = new ModelAndView();
		mav.addObject("kakaoId", kakaoId);
		System.out.println(kakaoId);
		mav.setViewName("membov/joinform");
		return mav;
	}

	// 카카오 로그인
	@RequestMapping(value = "/kakaologin")
	public ModelAndView kakaoLogin(HttpSession session) {
		String kakaoUrl = kakaoLoginApi.getAuthorizationUrl(session);

		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("membov/KakaoLogin");

		return mav;
	}

	// 카카오 로그인 2
	@RequestMapping(value = "/kakaologinOK")
	public ModelAndView kakaoLoginOK(@RequestParam("code") String code) {
		JsonNode token = kakaoLoginApi.getAccessToken(code);
		JsonNode profile = kakaoLoginApi.getKakaoUserInfo(token.path("access_token"));
		mav = memboardService.kakaoLogin(profile);
		return mav;
	}

	// 네이버 서버 로그인
	@RequestMapping(value = "/naverjoin")
	public ModelAndView naverJoin(HttpSession session) {
		String naverUrl = naverJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("membov/NaverLogin2");

		return mav;

	}

	// 네이버 서버인증 아이디 받아오기
	@RequestMapping(value = "/naverjoinok")
	public ModelAndView naverJoinOK(@RequestParam("code") String code, @RequestParam("state") String state,
			HttpSession session) throws IOException, ParseException {
		mav = new ModelAndView();
		OAuth2AccessToken oauthToken = naverJoinApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(profile);

		JSONObject naverUser = (JSONObject) obj;
		JSONObject userInfo = (JSONObject) naverUser.get("response");
		String naverId = (String) userInfo.get("id");

		mav.addObject("naverId", naverId);
		mav.setViewName("membov/joinform");

		return mav;
	}

	// 네이버로 로그인
	@RequestMapping(value = "/naverlogin")
	public ModelAndView naverLogin(HttpSession session) {
		String naverUrl = naverLoginApi.getAuthorizationUrl(session);

		mav = new ModelAndView();
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("membov/NaverLogin2");

		return mav;
	}

	// 네이버 로그인2
	@RequestMapping(value = "/naverloginok")
	public ModelAndView naverLoginOK(@RequestParam("code") String code, @RequestParam("state") String state,
			HttpSession session) throws IOException, ParseException {
		OAuth2AccessToken oauthToken = naverLoginApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);

		mav = memboardService.naverLogin(profile);

		return mav;

	}

	// 글쓰기(파일) 창열기
	@RequestMapping(value = "/writeform")
	public String writefile() {
		return "membov/boardwrite";
	}

	// 글쓰기
	@RequestMapping(value = "/boardwrite")
	public ModelAndView BoardWrite(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav = memboardService.boardWrite(board);
		return mav;
	}

	// 글 목록(페이징)
	@RequestMapping(value = "/boardlist")
	public ModelAndView boardlist(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		mav = memboardService.boardList(page);
		return mav;
	}

	// 상세 조회
	@RequestMapping(value = "/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		mav = memboardService.boardView(bnumber, page);

		return mav;
	}

	// 글 수정
	@RequestMapping(value = "/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		mav = memboardService.boardUpdate(bnumber);
		return mav;

	}

	// 새로운 내용 저장
	@RequestMapping(value = "/boardupdateprocess")
	public ModelAndView boardUpdateProcess(@ModelAttribute BoardDTO board) {
		mav = memboardService.boardUpdateprocess(board);
		return mav;
	}

	// 삭제
	@RequestMapping(value = "/boarddelete")
	public ModelAndView boardDelete(@RequestParam("bnumber") int bnumber) {
		mav = memboardService.boardDelete(bnumber);
		return mav;
	}
	// 검색
		@RequestMapping(value = "/boardsearch")
		public ModelAndView boardSearch(@RequestParam("searchtype") String searchtype,
				@RequestParam("keyword") String keyword) {
			mav = memboardService.boardSearch(searchtype, keyword);
			return mav;
		}

}
