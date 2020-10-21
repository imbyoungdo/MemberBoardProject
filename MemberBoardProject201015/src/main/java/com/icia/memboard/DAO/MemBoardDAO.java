package com.icia.memboard.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.memboard.DTO.BoardDTO;
import com.icia.memboard.DTO.MemberDTO;
import com.icia.memboard.DTO.PageDTO;

@Repository
public class MemBoardDAO {

	@Autowired
	private SqlSessionTemplate sql;

	//회원가입
	public int memberJoin(MemberDTO member) {

		if (member.getKakaoId() != null) {
			return sql.insert("MemBoard.kakaoMemberJoin", member);
		} else if (member.getNaverId() != null) {
			return sql.insert("MemBoard.naverMemberJoin", member);
		} else {
			return sql.insert("MemBoard.memberJoin", member);
		}
	}

	//아이디 중복확인
	public String idOverlap(String mid) {
		return sql.selectOne("MemBoard.idOverlap", mid);
	}

	//로그인
	public String memberLogin(MemberDTO member) {
		return sql.selectOne("MemBoard.memberLogin", member);
	}

	//회원 정보 조회
	public MemberDTO memberView(String mid) {
		return sql.selectOne("MemBoard.memberView", mid);
	}

	//회원 정보 수정
	public int memberUpdateProcess(MemberDTO member) {
		return sql.update("MemBoard.memberUpdate", member);
	}

	//회원 목록
	public List<MemberDTO> memberList() {
		return sql.selectList("MemBoard.memberList");
	}

	//회원 삭제
	public int memberDelete(String mid) {
		return sql.delete("MemBoard.memberDelete", mid);
	}

	//카카오 로그인
	public String kakaoLogin(String kakaoId) {
		return sql.selectOne("MemBoard.kakaoLogin", kakaoId);
	}

	//네이버 로그인
	public String naverLogin(String naverId) {
		return sql.selectOne("MemBoard.naverLogin", naverId);
	}

	//글 작성
	public int boardWrite(BoardDTO board) {
		return sql.insert("MemBoard.boardWrite", board);
	}

	//페이징 처리
	public int listcount() {
		return sql.selectOne("MemBoard.boardListCount");
	}

	//글 목록
	public List<BoardDTO> boardListPaging(PageDTO paging) {
		return sql.selectList("MemBoard.boardList", paging);
	}

	//글 상세 조회
	public BoardDTO boardView(int bnumber) {

		return sql.selectOne("MemBoard.boardView", bnumber);
	}

	//조회수
	public int boardHits(int bnumber) {
		return sql.update("MemBoard.boardHits", bnumber);

	}

	//글 수정
	public int boardUpdate(BoardDTO board) {
		return sql.update("MemBoard.boardUpdate", board);
	}

	//글 삭제
	public int boardDelete(int bnumber) {
		return sql.delete("MemBoard.boardDelete", bnumber);
	}

	//검색
	public List<BoardDTO> boardSearch(String searchtype, String keyword) {
		Map<String, String> searchMap = new HashMap<String, String>();

		searchMap.put("type", searchtype);
		searchMap.put("word", keyword);

		return sql.selectList("MemBoard.boardSearch", searchMap);
	}
}
