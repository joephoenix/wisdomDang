package com.mgs.service;

import java.util.List;

import com.mgs.entity.Sfile;
import com.mgs.fastdfs.FastDFSFile;

public interface SfileService {

	String generateSavefileRecord(Sfile file);

	Sfile getSaveFileInformation(String id);

	List<Sfile> querySavefileList();

	List<Sfile> searchChooseName(String name);

	List<Sfile> filterSavefileByExtname(String ext);

	int removeChooseSavefile(String id);

	String uploadChooseFileToFastdfs(FastDFSFile ff) throws Exception;

}
