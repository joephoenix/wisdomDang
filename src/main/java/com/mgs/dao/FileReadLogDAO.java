package com.mgs.dao;

import java.util.List;

import com.mgs.entity.FileReadLog;

public interface FileReadLogDAO {

	FileReadLog getLogDetailByPrimarykey(String id);

	List<FileReadLog> queryReadlogByfileId(Integer rst, String fid);

	List<FileReadLog> queryReadlogByMemberid(Integer rst, String mid);

	int sendSaveFileToMember(FileReadLog rlog);

	int readOrOpenSaveFile(String id);

	int closeSaveFile(String id);
	
	int trashSaveFile(String id);

}
