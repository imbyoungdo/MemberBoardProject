package com.icia.thenale.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.thenale.dto.CompanyDTO;
import com.icia.thenale.dto.HotelDTO;

@Repository
public class HotelDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 식당업체 승인 및 식당 DB에 데이터 이동
	public int hotelAddmission(CompanyDTO company) {
		return sql.insert("Hotel.hotelAdd", company);
	}

	// 호텔 리스트 출력
	public List<HotelDTO> hotelList(String h_area) {
		return sql.selectList("Hotel.hotelList", h_area);
	}

}