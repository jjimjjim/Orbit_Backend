package com.study.app.domains.defaultApprovalLine;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultApprovalLineDAO {
	
	@Autowired
	private SqlSessionTemplate myBatis;
}
