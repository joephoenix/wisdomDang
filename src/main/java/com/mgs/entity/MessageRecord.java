package com.mgs.entity;

import java.util.Date;

public class MessageRecord {
	
	private String id;
	
	private String mid;
	
	private String rsid;
	
	/**
	 * 信息记录的状态：1，表示未读；2，表示已读；3，表示废弃；0，表示删除。
	 */
	private Integer state;
	
	private Date rdtime;

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

	public String getRsid() {
		return rsid;
	}

	public void setRsid(String rsid) {
		this.rsid = rsid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getRdtime() {
		return rdtime;
	}

	public void setRdtime(Date rdtime) {
		this.rdtime = rdtime;
	}

}
