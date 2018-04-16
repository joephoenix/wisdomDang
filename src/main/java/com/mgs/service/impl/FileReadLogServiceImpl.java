package com.mgs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.FileReadLogDAO;
import com.mgs.entity.FileReadLog;
import com.mgs.service.FileReadLogService;

@Service
public class FileReadLogServiceImpl implements FileReadLogService {

	@Resource
	private FileReadLogDAO fileReadLogDAO;

	@Override
	public FileReadLog getReadlogDetail(String id) {
		return fileReadLogDAO.getLogDetailByPrimarykey(id);
	}

	@Override
	public String sendSaveFileToMember(FileReadLog frl) {
		int rlt = fileReadLogDAO.sendSaveFileToMember(frl);
		if (rlt > 0) {
			return frl.getId();
		} else {
			return "send the file to member that id is " + frl.getMid() + " failed";
		}
	}

	@Override
	public List<FileReadLog> findUnreadFilesByMember(String mid) {
		return fileReadLogDAO.queryReadlogByMemberid(1, mid);
	}

	@Override
	public List<FileReadLog> findReadedFilesByMember(String mid) {
		return fileReadLogDAO.queryReadlogByMemberid(2, mid);
	}

	@Override
	public List<FileReadLog> findClosedFilesByMember(String mid) {
		return fileReadLogDAO.queryReadlogByMemberid(3, mid);
	}

	@Override
	public List<FileReadLog> findTrashedFilesByMember(String mid) {
		return fileReadLogDAO.queryReadlogByMemberid(0, mid);
	}

	@Override
	public int readOpenSavefile(String id) {
		return fileReadLogDAO.readOrOpenSaveFile(id);
	}

	@Override
	public int closeSavefile(String id) {
		return fileReadLogDAO.closeSaveFile(id);
	}

	@Override
	public int trashSavefile(String id) {
		return fileReadLogDAO.trashSaveFile(id);
	}

	@Override
	public List<FileReadLog> findChooseFileLogs(String fid) {
		List<FileReadLog> rList = new ArrayList<FileReadLog>();
		List<FileReadLog> list1 = fileReadLogDAO.queryReadlogByfileId(1, fid);
		rList.addAll(list1);
		List<FileReadLog> list2 = fileReadLogDAO.queryReadlogByfileId(2, fid);
		rList.addAll(list2);
		List<FileReadLog> list3 = fileReadLogDAO.queryReadlogByfileId(3, fid);
		rList.addAll(list3);
		List<FileReadLog> list0 = fileReadLogDAO.queryReadlogByfileId(0, fid);
		rList.addAll(list0);
		return rList;
	}

}
