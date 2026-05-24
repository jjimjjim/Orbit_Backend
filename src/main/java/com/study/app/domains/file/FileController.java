package com.study.app.domains.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService FileServ;
	
	@GetMapping("/profile/view")
	public ResponseEntity<byte[]> profileImage(@RequestParam("sysname") String sysname) {
		System.out.println(sysname);
		byte[] imgByte = FileServ.profileImage(sysname);
		
		if(imgByte == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_PNG)
				.body(imgByte);
		// -> 다운로드가 아닌 출력만 하는 이미지를 알려주기 위해 contentType을 고정하여 발송.
	}
}
