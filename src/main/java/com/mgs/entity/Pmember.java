package com.mgs.entity;

import java.util.Date;

public class Pmember {

	private String id;

	private String uname;

	private String pword;

	private Integer isParty;

	private Integer partyAge;

	private Integer pionts;

	private Integer state;

	private Date ctime;

	private Date mtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	public Integer getIsParty() {
		return isParty;
	}

	public void setIsParty(Integer isParty) {
		this.isParty = isParty;
	}

	public Integer getPartyAge() {
		return partyAge;
	}

	public void setPartyAge(Integer partyAge) {
		this.partyAge = partyAge;
	}

	public Integer getPionts() {
		return pionts;
	}

	public void setPionts(Integer pionts) {
		this.pionts = pionts;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
}
