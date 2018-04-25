package com.mgs.view;

import java.util.Date;

public class MemberView {
	
	private String mid;
	
	private String rname;
	
	private String latitude;
	
	private String longtude;
	
	private Date recordtm;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtude() {
		return longtude;
	}

	public void setLongtude(String longtude) {
		this.longtude = longtude;
	}

	public Date getRecordtm() {
		return recordtm;
	}

	public void setRecordtm(Date recordtm) {
		this.recordtm = recordtm;
	}

}
