package com.study.app.domains.notifications;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.app.domains.approval.ApprovalDAO;
import com.study.app.domains.projects.KanbanTaskDAO;

@Service
public class NotificationsService {

	@Autowired
	private SimpMessagingTemplate stomp;

	@Autowired
	private NotificationsDAO notiDao;

	@Autowired
	private KanbanTaskDAO kanbanDao;

	@Autowired
	private ApprovalDAO appDao;

	@Transactional
	public void sendToAuthGroup(String auth_group, String loginId, Long ref_seq,
			String noti_type, String content, String ref_type) {
		
		List<String> authAdminIds = notiDao.findAuthAdminIds(auth_group);
		
		for(String authAdminId : authAdminIds) {
			
			if(authAdminId.equals(loginId)) {
				continue;
			}
			
			NotificationsDTO noti = new NotificationsDTO();
			
			noti.setUsers_id(authAdminId);
			noti.setRef_seq(ref_seq);
			noti.setNoti_type(noti_type);
			noti.setContent(content);
			noti.setRef_type(ref_type);
			noti.setRead_yn("N");
			noti.setCreated_at(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			insertNoti(noti);
		}
	}
	
	@Transactional
	public void sendToUser(String users_id, Long ref_seq, 
			String noti_type, String content, String ref_type) {
		
		NotificationsDTO noti = new NotificationsDTO();
		
		noti.setRef_seq(ref_seq);
		noti.setUsers_id(users_id);
		noti.setNoti_type(noti_type);
		noti.setContent(content);
		noti.setRef_type(ref_type);
		noti.setRead_yn("N");
		noti.setCreated_at(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		insertNoti(noti);
	}
	
	public void insertNoti(NotificationsDTO dto) {
		notiDao.insertNoti(dto);
		stomp.convertAndSend("/sub/notification/" + dto.getUsers_id(), dto);
	}

	public void deleteNotiByRsvnList(List<Long> rsvnList) {
		if (rsvnList == null || rsvnList.isEmpty()) { 
			return; 
		}
		List<NotificationsDTO> deleteTarget = notiDao.findNotiByRsvnList(rsvnList);
		notiDao.deleteNotiByRsvnList(rsvnList);

		for(NotificationsDTO dto : deleteTarget) {
			sendDeleteEvent(dto);
		}
	}

	public void deleteProjectNotiBySeq(Long project_seq) {
		List<NotificationsDTO> deleteTarget = notiDao.findProjectNotiBySeq(project_seq);
		notiDao.deleteProjectNotiBySeq(project_seq);

		for(NotificationsDTO dto : deleteTarget) {
			sendDeleteEvent(dto);
		}
	}

	public void deleteNotiBySeqAndId(Long rsvn_seq, String users_id) {
		List<NotificationsDTO> deleteTarget = notiDao.findNotiBySeqAndId(rsvn_seq, users_id);
		notiDao.deleteNotiBySeqAndId(rsvn_seq, users_id);

		for(NotificationsDTO dto : deleteTarget) {
			sendDeleteEvent(dto);
		}
	}

	public void deleteMeetingNotiBySeq(Long rsvn_seq) {
		List<NotificationsDTO> deleteTarget = notiDao.findMeetingNotiBySeq(rsvn_seq);
		notiDao.deleteMeetingNotiBySeq(rsvn_seq);

		for(NotificationsDTO dto : deleteTarget) {
			sendDeleteEvent(dto);
		}
	}

	public void deleteApprovalNotiBySeq(Long doc_seq) {
		List<NotificationsDTO> deleteTarget = notiDao.findApprovalNotiBySeq(doc_seq);
		notiDao.deleteApprovalNotiBySeq(doc_seq);

		for(NotificationsDTO dto : deleteTarget) {
			sendDeleteEvent(dto);
		}
	}

	public void deleteTaskNotiBySeq(Long task_seq) {
		List<NotificationsDTO> deleteTarget = notiDao.findTaskNotiBySeq(task_seq);
	    notiDao.deleteTaskNotiBySeq(task_seq);

	    for (NotificationsDTO dto : deleteTarget) {
	        sendDeleteEvent(dto);
	    }
	}
	
	public List<NotificationsDTO> getMyNotiListByLoginId(String loginId) {
		return notiDao.getMyNotiListByLoginId(loginId);
	}

	public Long getNotiProjectSeq(Long ref_seq) {
		return kanbanDao.getNotiProjectSeq(ref_seq);
	}

	public String getNotiDocType(Long ref_seq) {
		return appDao.getNotiDocType(ref_seq);
	}

	public void updateReadNoti(Long noti_seq) {
		notiDao.updateReadNoti(noti_seq);
	}
	
	public void deleteSupplyNotiBySeq(Long ref_seq) {
		List<NotificationsDTO> deleteTarget = notiDao.findSupplyNotiBySeq(ref_seq);
		notiDao.deleteSupplyNotiBySeq(ref_seq);
		
		for(NotificationsDTO dto : deleteTarget) {
			sendDeleteEvent(dto);
		}
	}

	private void sendDeleteEvent(NotificationsDTO dto) {
		NotificationsDeleteEventDTO deleteDto = new NotificationsDeleteEventDTO (
				"DELETE", dto.getNoti_seq(), dto.getUsers_id(), dto.getRef_type(), dto.getRef_seq());

		stomp.convertAndSend("/sub/notification/" + dto.getUsers_id(), deleteDto);
	}
}
