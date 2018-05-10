package com.mgs.entity;

import java.util.Date;

public class ResourceFile {

	private String id;

	private String mainid;

	/**
	 * 类型描述：1是辅助图片，2是正文图片，3是视频，4是音频
	 */
	private Integer ftype;

	private String fname;

	private String fext;

	private String fpath;

	private String flength;

	private Integer state;

	private String creater;

	private Date ctime;

	private Date mtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public Integer getFtype() {
		return ftype;
	}

	public void setFtype(Integer ftype) {
		this.ftype = ftype;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFext() {
		return fext;
	}

	public void setFext(String fext) {
		this.fext = fext;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	public String getFlength() {
		return flength;
	}

	public void setFlength(String flength) {
		this.flength = flength;
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
}
