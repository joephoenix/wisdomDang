package com.mgs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mgs.entity.FileReadLog;

public interface FileReadLogDAO {

	FileReadLog getLogDetailByPrimarykey(@Param("id") String id);

	List<FileReadLog> queryReadlogByfileId(@Param("state") Integer state, @Param("id") String fid);

	List<FileReadLog> queryReadlogByMemberid(@Param("state") Integer state, @Param("id") String mid);

	int sendSaveFileToMember(FileReadLog rlog);

	int readOrOpenSaveFile(@Param("id") String id);

	int closeAndSaveFile(@Param("id") String id);

	int updateFilereadRatio(@Param("ratio") Integer ratio, @Param("id") String id);

}
