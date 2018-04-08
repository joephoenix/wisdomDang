package com.mgs.entity;

import java.util.Date;

public class Pmember {

	private String id;

	private String uname;

	private String rname;

	private String pword;

	private int isParty;

	private Date ctime;

	private Date mtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUmane() {
		return uname;
	}

	public void setUmane(String uname) {
		this.uname = uname;
	}

	public String getRmane() {
		return rname;
	}

	public void setRmane(String rname) {
		this.rname = rname;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	public int getIsParty() {
		return isParty;
	}

	public void setIsParty(int isParty) {
		this.isParty = isParty;
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
