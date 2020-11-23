package com.icia.thenale.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.thenale.dto.CompanyDTO;
import com.icia.thenale.dto.FoodDTO;
import com.icia.thenale.dto.HotelDTO;

@Repository
public class CompanyDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 업체 회원가입
	public int companyJoin(CompanyDTO company) {
		return sql.insert("Company.companyJoin", company);
	}

	// 업체 로그인
	public String companyLogin(CompanyDTO company) {
		return sql.selectOne("Company.companyLogin", company);
	}

	// 업체 리스트
	public List<CompanyDTO> compnayList() {
		return sql.selectList("Company.companyList");
	}

	// 업체 등록 상세 조회
	public CompanyDTO companyView(String c_id) {
		return sql.selectOne("Company.companyView", c_id);
	}

	// 업체 신청 허가
	public int companyAdminssion(CompanyDTO company) {
		return sql.update("Company.companyAdmission", company);
	}

	// 식당업체 정보 저장
	public int foodAddmission(FoodDTO food) {
		return sql.insert("Company.foodAdmission", food);
	}

	// 호텔업체 정보 저장
	public int hotelAddmission(HotelDTO hotel) {
		return sql.insert("Company.hotelAdmission", hotel);
	}

	// 업체 삭제
	public int companyDelete(String c_id) {
		return sql.delete("Company.companyDelete", c_id);
	}
}