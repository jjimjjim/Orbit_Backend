package com.study.app.domains.defaultApprovalLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultApprovalLineService {
	
	@Autowired
	private DefaultApprovalLineDAO dao;
}
