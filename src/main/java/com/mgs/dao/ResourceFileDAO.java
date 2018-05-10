package com.mgs.dao;

import java.util.List;

import com.mgs.entity.ResourceFile;

public interface ResourceFileDAO {

	ResourceFile getFileInfoByPrimarykey(String id);

	List<ResourceFile> queryEntireSaveFiles();

	List<ResourceFile> queryAbandonSaveFiles();

	List<ResourceFile> queryFilesWithNane(String name);

	List<ResourceFile> queryFileByExtname(String ext);

	List<ResourceFile> queryFileByMainid(String mainid);

	List<ResourceFile> queryFileByfiletype(Integer ftype);

	int addNewSavefileRecord(ResourceFile sf);

	int removeSavefileRecord(String id);

	int reviseSavefileRecord(String id);

}
