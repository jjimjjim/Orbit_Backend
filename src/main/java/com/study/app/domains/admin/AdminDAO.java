package com.study.app.domains.admin;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.app.domains.departments.DepartmentsDTO;

@Repository
public class AdminDAO {

	@Autowired
	private SqlSessionTemplate batis;
	
	public void addDept(DepartmentsDTO dto) {
		batis.insert("Admin.addDept", dto);
	}
}
