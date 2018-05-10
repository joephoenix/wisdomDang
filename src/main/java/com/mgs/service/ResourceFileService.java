package com.mgs.service;

import java.util.List;

import com.mgs.entity.ResourceFile;
import com.mgs.fastdfs.FastDFSFile;

public interface ResourceFileService {

	String generateSavefileRecord(ResourceFile file);

	ResourceFile getSaveFileInformation(String id);

	List<ResourceFile> querySavefileList();

	List<ResourceFile> searchChooseName(String name);

	List<ResourceFile> filterSavefileByExtname(String ext);

	int removeChooseSavefile(String id);

	String uploadChooseFileToFastdfs(FastDFSFile ff) throws Exception;

}
