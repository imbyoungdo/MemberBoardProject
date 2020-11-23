package com.icia.thenale.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.thenale.dto.CompanyDTO;
import com.icia.thenale.dto.FoodDTO;

@Repository
public class FoodDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 식당업체 승인 및 식당 DB에 데이터 이동
	public int fodAdd(CompanyDTO company) {
		return sql.insert("Food.foodAdd", company);
	}
	
	// 식당 리스트 출력
	public List<FoodDTO> foodList(String f_area) {
		return sql.selectList("Food.foodList", f_area);
	}

}