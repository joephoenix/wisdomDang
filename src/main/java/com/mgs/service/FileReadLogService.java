package com.mgs.service;

import java.util.List;

import com.mgs.entity.FileReadLog;

public interface FileReadLogService {

	FileReadLog getReadlogDetail(String id);

	String sendSaveFileToMember(FileReadLog frl);

	List<FileReadLog> findUnreadFilesByMember(String mid);

	List<FileReadLog> findReadedFilesByMember(String mid);

	List<FileReadLog> findClosedFilesByMember(String mid);

	List<FileReadLog> findTrashedFilesByMember(String mid);

	int readOpenSavefile(String id);

	int closeSavefile(String id);


	/**
	 * view the read log list for file that chose with id;
	 * 
	 * @param fid
	 * @return
	 */
	List<FileReadLog> findChooseFileLogs(String fid);
}
