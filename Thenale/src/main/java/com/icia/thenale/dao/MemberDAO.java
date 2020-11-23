package com.icia.thenale.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.thenale.dto.MemberDTO;
import com.icia.thenale.dto.SpotDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 회원가입
	public int memberJoin(MemberDTO member) {
		return sql.insert("Member.memberJoin", member);
	}

	// 로그인
	public String memberLogin(MemberDTO member) {
		return sql.selectOne("Member.memberLogin", member);
	}

	// 내 정보 mypage 
	public MemberDTO memberView(String m_id) {
		return sql.selectOne("Member.memberView", m_id);
	}

	// 멤버 수정
	public int memberUpdate(MemberDTO member) {
		return sql.update("Member.memberUpdate", member);
	}
	



}
