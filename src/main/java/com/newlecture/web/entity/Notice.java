/* Notice.java */
package com.newlecture.web.entity;

import java.util.Date;

/* Notice 클래스에 속성을 정의 Notice라는 Entity객체 */
public class Notice {
	private int id; //id도 추가
	private String title;
	private String writerId;
	private Date regdate;
	private String hit;
	private String files; 
	private String content;
	
	/* 기본생성자 */
	public Notice() {
		
	}
	
	/* 오버로드 생성자 추가 */
	public Notice(int id, String title, String writerId, Date regdate, String hit, String files, String content) {
		
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.regdate = regdate;
		this.hit = hit;
		this.files = files;
		this.content = content;
	}

	/* Getters and Setters 추가 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/* ToString 생성 */
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writerId=" + writerId + ", regdate=" + regdate + ", hit="
				+ hit + ", files=" + files + ", content=" + content + "]";
	}
}



