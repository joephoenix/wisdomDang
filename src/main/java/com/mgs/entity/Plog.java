package com.mgs.entity;

import java.util.Date;

public class Plog {

	private String id;

	/**
	 * id of member
	 */
	private String mid;

	private String longitude;

	private String latitude;

	private Integer isAttend;

	private Date recordtm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Integer getIsAttend() {
		return isAttend;
	}

	public void setIsAttend(Integer isAttend) {
		this.isAttend = isAttend;
	}

	public Date getRecordtm() {
		return recordtm;
	}

	public void setRecordtm(Date recordtm) {
		this.recordtm = recordtm;
	}
}
