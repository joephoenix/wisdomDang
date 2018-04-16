package com.mgs.dao;

import java.util.List;

import com.mgs.entity.Sfile;

public interface SfileDAO {
	
	Sfile getFileInfoByPrimarykey(String id);
	
	List<Sfile> queryEntireSaveFiles();
	
	List<Sfile> queryAbandonSaveFiles();
	
	List<Sfile> queryFilesWithNane(String name);
	
	List<Sfile> queryFileByExtname(String ext);
	
	int addNewSavefileRecord(Sfile sf);
	
	int removeSavefileRecord(String id);
	
	int reviseSavefileRecord(String id);

}
