package com.mgs.entity;

import java.util.Date;

public class Power {

	private String id;

	private String pname;

	private String pcode;

	private Integer plevel;

	private Integer ptype;

	private Integer state;

	private String creater;

	private Date ctime;

	private Date mtime;

	private String pagelink;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public Integer getPlevel() {
		return plevel;
	}

	public void setPlevel(Integer plevel) {
		this.plevel = plevel;
	}

	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
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

	public String getPagelink() {
		return pagelink;
	}

	public void setPagelink(String pagelink) {
		this.pagelink = pagelink;
	}

}
