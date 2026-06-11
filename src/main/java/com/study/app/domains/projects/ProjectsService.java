package com.study.app.domains.projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.app.domains.users.UsersDAO;
import com.study.app.domains.users.UsersDTO;

@Service
public class ProjectsService {
	
	@Autowired
	private ProjectsDAO projectsDao;
	
	@Autowired
	private KanbanTaskDAO kanbanDao;
	
	@Autowired
	private UsersDAO usersDao;
	
	public List<UsersDTO> allEmployee() {
		return usersDao.getAllEmployees();
	}
	
	@Transactional
	public void insertProjectAndMembers(String loginId, ProjectsDTO dto) {
		projectsDao.insertProject(loginId, dto);
		
		Long projectSeq = dto.getProject_seq();
		System.out.println(projectSeq);
		
		ProjectMembersDTO memDto = new ProjectMembersDTO();
		memDto.setProject_seq(projectSeq);
		projectsDao.insertProjectMembers(memDto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
