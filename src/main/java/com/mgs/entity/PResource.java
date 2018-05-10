package com.mgs.entity;

import java.util.Date;

public class PResource {

	private String id;

	private String fileName;

	private String fileDescript;

	/**
	 * 发文的正文内容
	 * 
	 * 其中的图片和视频链接，都采用fastdfs链接格式
	 */
	private String fileContext;

	/**
	 * 1表示文件；2表示课程；3表示会议
	 * 
	 *
	 */
	private Integer fileType;

	/**
	 * 与字典表的分值数据行id关联
	 */
	private String taskPoint;

	/**
	 * 要求的阅读时长，为方便转化为毫秒
	 */
	private Integer timeLength;

	private Integer state;

	private String creater;

	private Date ctime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDescript() {
		return fileDescript;
	}

	public void setFileDescript(String fileDescript) {
		this.fileDescript = fileDescript;
	}

	public String getFileContext() {
		return fileContext;
	}

	public void setFileContext(String fileContext) {
		this.fileContext = fileContext;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public String getTaskPoint() {
		return taskPoint;
	}

	public void setTaskPoint(String taskPoint) {
		this.taskPoint = taskPoint;
	}

	public Integer getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(Integer timeLength) {
		this.timeLength = timeLength;
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

}
