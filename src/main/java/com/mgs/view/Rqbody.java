package com.mgs.view;

import org.springframework.web.multipart.MultipartFile;

public class Rqbody {

	String username;

	String memid;

	String password;

	MultipartFile attach;

	String longtude;

	String latitude;

	String fOrgid;

	String fileid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MultipartFile getAttach() {
		return attach;
	}

	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}

	public String getLongtude() {
		return longtude;
	}

	public void setLongtude(String longtude) {
		this.longtude = longtude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getfOrgid() {
		return fOrgid;
	}

	public void setfOrgid(String fOrgid) {
		this.fOrgid = fOrgid;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

}
