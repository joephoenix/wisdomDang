package com.mgs.fastdfs;

public class FastDFSFile implements FileManagerConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024860944892728535L;

	// entity
	private byte[] content;

	private String name;

	private String ext;

	private String length;

	private String author = FILE_DEFAULT_AUTHOR;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
