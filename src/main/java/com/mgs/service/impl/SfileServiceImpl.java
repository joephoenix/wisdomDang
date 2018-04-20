package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.csource.common.NameValuePair;
import org.springframework.stereotype.Service;
import com.mgs.dao.SfileDAO;
import com.mgs.entity.Sfile;
import com.mgs.fastdfs.FastDFSFile;
import com.mgs.fastdfs.FileManager;
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

	/**
	 * FastDFSFile 是指fastdfs文件的属性，其中Author存放memberId
	 */
	@Override
	public String uploadChooseFileToFastdfs(FastDFSFile fdf) throws Exception {
		NameValuePair[] meta_list = new NameValuePair[4];
		meta_list[0] = new NameValuePair("fileName", fdf.getName());
		meta_list[1] = new NameValuePair("fileLength", fdf.getLength());
		meta_list[2] = new NameValuePair("fileExt", fdf.getExt());
		meta_list[3] = new NameValuePair("fileAuthor", fdf.getAuthor());
		// upload
		String filePath = FileManager.upload(fdf.getContent(), fdf.getExt(), meta_list);
		Sfile insertInfo = new Sfile();
		insertInfo.setFname(fdf.getName());
		insertInfo.setFext(fdf.getExt());
		insertInfo.setFpath(filePath);
		insertInfo.setCreater(fdf.getAuthor());
		insertInfo.setFlength(fdf.getLength());
		int rlt = sfileDAO.addNewSavefileRecord(insertInfo);
		if (rlt > 0) {
			return insertInfo.getId();
		} else {
			return "save a file failed";
		}
	}

}
