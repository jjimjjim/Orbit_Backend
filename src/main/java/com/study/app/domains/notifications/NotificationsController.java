package com.study.app.domains.notifications;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noti")
public class NotificationsController {

	@Autowired
	private NotificationsService noServ;
	
}
