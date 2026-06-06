package com.study.app.domains.aiChat;

public class EmbedDocumentDTO {
	
	private Long file_seq;
	private Long document_seq;
	private String fileName;
	private String signedUrl;
	private String mimeType;
	
	public EmbedDocumentDTO() {}
	
	public EmbedDocumentDTO(Long file_seq, Long document_seq, 
			String fileName, String signedUrl, String mimeType) {
		super();
		this.file_seq = file_seq;
		this.document_seq = document_seq;
		this.fileName = fileName;
		this.signedUrl = signedUrl;
		this.mimeType = mimeType;
	}
	
	public Long getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(Long file_seq) {
		this.file_seq = file_seq;
	}
	public Long getDocument_seq() {
		return document_seq;
	}
	public void setDocument_seq(Long document_seq) {
		this.document_seq = document_seq;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSignedUrl() {
		return signedUrl;
	}
	public void setSignedUrl(String signedUrl) {
		this.signedUrl = signedUrl;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
