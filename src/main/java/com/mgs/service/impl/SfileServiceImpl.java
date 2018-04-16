package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.SfileDAO;
import com.mgs.entity.Sfile;
import com.mgs.service.SfileService;

@Service
public class SfileServiceImpl implements SfileService {

	@Resource
	private SfileDAO sfileDAO;

	@Override
	public String generateSavefileRecord(Sfile file) {
		int rlt = sfileDAO.addNewSavefileRecord(file);
		if (rlt > 0) {
			return file.getId();
		} else {
			return "save a file failed";
		}
	}

	@Override
	public Sfile getSaveFileInformation(String id) {
		return sfileDAO.getFileInfoByPrimarykey(id);
	}

	@Override
	public List<Sfile> querySavefileList() {
		return sfileDAO.queryEntireSaveFiles();
	}

	@Override
	public List<Sfile> searchChooseName(String name) {
		return sfileDAO.queryFilesWithNane(name);
	}

	@Override
	public List<Sfile> filterSavefileByExtname(String ext) {
		return sfileDAO.queryFileByExtname(ext);
	}

	@Override
	public int removeChooseSavefile(String id) {
		return sfileDAO.removeSavefileRecord(id);
	}

}
