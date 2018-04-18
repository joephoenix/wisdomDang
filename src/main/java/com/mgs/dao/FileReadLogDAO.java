package com.mgs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mgs.entity.FileReadLog;

public interface FileReadLogDAO {

	FileReadLog getLogDetailByPrimarykey(@Param("id") String id);

	List<FileReadLog> queryReadlogByfileId(@Param("rst") Integer rst, @Param("id") String fid);

	List<FileReadLog> queryReadlogByMemberid(@Param("rst") Integer rst, @Param("id") String mid);

	int sendSaveFileToMember(FileReadLog rlog);

	int readOrOpenSaveFile(@Param("id") String id);

	int closeSaveFile(@Param("id") String id);

	int trashSaveFile(@Param("id") String id);

}
