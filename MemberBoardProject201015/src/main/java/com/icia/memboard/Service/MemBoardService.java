package com.icia.memboard.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.memboard.DTO.PageDTO;
import com.icia.memboard.DAO.CommentDAO;
import com.icia.memboard.DAO.MemBoardDAO;
import com.icia.memboard.DTO.BoardDTO;
import com.icia.memboard.DTO.CommentDTO;
import com.icia.memboard.DTO.MemberDTO;

@Service
public class MemBoardService {

	@Autowired
	private MemBoardDAO mbDAO;

	@Autowired
	private HttpSession session;

	@Autowired
	private CommentDAO cDAO;

	private ModelAndView mav;

	private static final int PAGE_LIMIT = 3;
	private static final int BLOCK_LIMIT = 5;

	//회원가입
	public ModelAndView memberSign(MemberDTO member) throws IllegalStateException, IOException {

		mav = new ModelAndView();

		MultipartFile mphoto = member.getMphoto();
		String mphotoname = mphoto.getOriginalFilename();
		String savePath = "D:\\source\\Spring\\MemberBoardProject201015\\src\\main\\webapp\\resources\\uploadfile\\"
				+ mphotoname;

		if (!mphoto.isEmpty()) {
			mphoto.transferTo(new File(savePath));
		}

		member.setMphotoname(mphotoname);

		int joinResult = mbDAO.memberJoin(member);

		if (joinResult > 0) {
			mav.setViewName("membov/login");
		} else {
			mav.setViewName("loginfail");
		}
		return mav;
	}
	
	//아이디 중복확인(ajax)
	public String idOverlap(String mid) {
		String checkResult = mbDAO.idOverlap(mid);
		String resultMsg = null;

		if (checkResult == null) {
			resultMsg = "OK";
		} else {
			resultMsg = "NO";
		}
		return resultMsg;
	}

	//로그인
	public ModelAndView memberLogin(MemberDTO member) {
		mav = new ModelAndView();

		String loginId = mbDAO.memberLogin(member);
		if (loginId != null) {

			session.setAttribute("loginId", loginId);
			mav.setViewName("redirect:/boardlist");
		} else {

			mav.setViewName("LoginFail");
		}
		return mav;
	}

	//회원 정보 조회
	public ModelAndView memberView(String mid) {
		mav = new ModelAndView();
		MemberDTO memberView = mbDAO.memberView(mid);
		mav.addObject("memberView", memberView);
		mav.setViewName("membov/MemberView");
		return mav;
	}
	
	//회원 정보 수정 - 1
	public ModelAndView membeUpdate(String mid) {

		mav = new ModelAndView();
		MemberDTO memberUpdate = mbDAO.memberView(mid);
		mav.addObject("memberUpdate", memberUpdate);
		mav.setViewName("membov/memberUpdate");
		return mav;
	}
	
	//회원 정보수정 - 2
	public ModelAndView memberupdateProcess(MemberDTO member) {
		mav = new ModelAndView();
		int updateResult = mbDAO.memberUpdateProcess(member);

		if (updateResult > 0) {
			mav.setViewName("redirect:/boardlist");
		} else {
			mav.setViewName("memberUpdateFail");
		}
		return mav;
	}

	//회원 목록
	public ModelAndView memberList() {
		mav = new ModelAndView();
		List<MemberDTO> memberList = mbDAO.memberList();

		mav.addObject("memberList", memberList);
		mav.setViewName("membov/MemBoardMain");

		return mav;
	}

	//회원 삭제
	public ModelAndView memberDelete(String mid) {

		mav = new ModelAndView();

		int deleteResult = mbDAO.memberDelete(mid);
		if (deleteResult > 0) {
			mav.setViewName("redirect:/memberlist");
		} else {

		}

		return mav;
	}

	//카카오 로그인
	public ModelAndView kakaoLogin(JsonNode profile) {
		mav = new ModelAndView();

		String kakaoId = profile.get("id").asText();
		String loginId = mbDAO.kakaoLogin(kakaoId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("membov/MemBoardMain");
		return mav;
	}

	//네이버 로그인
	public ModelAndView naverLogin(String profile) throws ParseException {
		mav = new ModelAndView();

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);

		JSONObject naverUser = (JSONObject) obj;
		JSONObject userInfo = (JSONObject) naverUser.get("response");

		String naverId = (String) userInfo.get("id");
		String loginId = mbDAO.naverLogin(naverId);

		session.setAttribute("loginId", loginId);

		mav.setViewName("MemberMain");

		return mav;
	}

	//게시글 작성
	public ModelAndView boardWrite(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();

		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		String savePath = "D:\\source\\Spring\\MemberBoardProject201015\\src\\main\\webapp\\resources\\uploadfile\\"
				+ bfilename;

		if (!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int writeResult = mbDAO.boardWrite(board);
		if (writeResult > 0) {
			mav.setViewName("redirect:/boardlist");
		} else {
			mav.setViewName("redirect:/boardWriteFail");
		}
		return mav;
	}

	//글 목록
	public ModelAndView boardList(int page) {
		mav = new ModelAndView();

		int listCount = mbDAO.listcount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;

		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = mbDAO.boardListPaging(paging);

		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);

		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("membov/MemBoardMain");

		return mav;
	}

	//글 조회
	public ModelAndView boardView(int bnumber, int page) {
		mav = new ModelAndView();

		mbDAO.boardHits(bnumber);
		BoardDTO boardView = mbDAO.boardView(bnumber);

		List<CommentDTO> commentView = cDAO.commentView(bnumber);
		mav.addObject("commentView", commentView);

		mav.addObject("page", page);
		mav.addObject("boardView", boardView);
		mav.setViewName("membov/boardView");
		return mav;
	}

	//글 수정 - 1
	public ModelAndView boardUpdate(int bnumber) {
		mav = new ModelAndView();

		BoardDTO boardUpdate = mbDAO.boardView(bnumber);
		mav.addObject("boardUpdate", boardUpdate);
		mav.setViewName("membov/boardUpdate");

		return mav;
	}

	//글 수정 - 2
	public ModelAndView boardUpdateprocess(BoardDTO board) {
		mav = new ModelAndView();

		int updateResult = mbDAO.boardUpdate(board);
		if (updateResult > 0) {
			mav.setViewName("redirect:/boardview?bnumber=" + board.getBnumber());

		} else {
			mav.setViewName("membov/BoardUpdateFail");
		}

		return mav;
	}

	//글 삭제
	public ModelAndView boardDelete(int bnumber) {
		mav = new ModelAndView();
		int deleteReault = mbDAO.boardDelete(bnumber);

		if (deleteReault > 0) {
			mav.setViewName("redirect:/boardlist");
		} else {

			mav.setViewName("membov/boardDeleteFail");
		}

		return mav;
	}

	//검색
	public ModelAndView boardSearch(String searchtype, String keyword) {
		mav = new ModelAndView();

		List<BoardDTO> searchlist = mbDAO.boardSearch(searchtype, keyword);
		mav.addObject("boardList", searchlist);
		mav.setViewName("membov/MemBoardMain");

		return mav;
	}

}
