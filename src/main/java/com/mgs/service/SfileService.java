package com.mgs.service;

import java.util.List;

import com.mgs.entity.Sfile;

public interface SfileService {

	String generateSavefileRecord(Sfile file);

	Sfile getSaveFileInformation(String id);

	List<Sfile> querySavefileList();

	List<Sfile> searchChooseName(String name);

	List<Sfile> filterSavefileByExtname(String ext);

	int removeChooseSavefile(String id);

}
