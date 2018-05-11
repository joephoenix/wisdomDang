package com.mgs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.csource.common.NameValuePair;
import org.springframework.stereotype.Service;

import com.mgs.dao.MessageRecordDAO;
import com.mgs.dao.ResourceFileDAO;
import com.mgs.entity.MessageRecord;
import com.mgs.entity.ResourceFile;
import com.mgs.fastdfs.FastDFSFile;
import com.mgs.fastdfs.FileManager;
import com.mgs.service.ResourceFileService;

@Service
public class ResourceFileServiceImpl implements ResourceFileService {

	@Resource
	private ResourceFileDAO resourceFileDAO;

	@Resource
	private MessageRecordDAO messageRecordDAO;

	@Override
	public String generateSavefileRecord(ResourceFile file) {
		int rlt = resourceFileDAO.addNewSavefileRecord(file);
		if (rlt > 0) {
			return file.getId();
		} else {
			return "save a file failed";
		}
	}

	@Override
	public ResourceFile getSaveFileInformation(String id) {
		return resourceFileDAO.getFileInfoByPrimarykey(id);
	}

	@Override
	public List<ResourceFile> querySavefileList() {
		return resourceFileDAO.queryEntireSaveFiles();
	}

	@Override
	public List<ResourceFile> searchChooseName(String name) {
		return resourceFileDAO.queryFilesWithNane(name);
	}

	@Override
	public List<ResourceFile> filterSavefileByExtname(String ext) {
		return resourceFileDAO.queryFileByExtname(ext);
	}

	@Override
	public int removeChooseSavefile(String id) {
		return resourceFileDAO.removeSavefileRecord(id);
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
		ResourceFile insertInfo = new ResourceFile();
		insertInfo.setFname(fdf.getName());
		insertInfo.setFext(fdf.getExt());
		insertInfo.setFpath(filePath);
		insertInfo.setCreater(fdf.getAuthor());
		insertInfo.setFlength(fdf.getLength());
		insertInfo.setFtype(fdf.getFileType());
		insertInfo.setMainid(fdf.getMainId());
		int rlt = resourceFileDAO.addNewSavefileRecord(insertInfo);
		if (rlt > 0) {
			return insertInfo.getId();
		} else {
			return "save a file failed";
		}
	}

	@Override
	public List<ResourceFile> findNotice4Member(String mid) {
		List<ResourceFile> rltRF = new ArrayList<ResourceFile>();
		List<MessageRecord> lstMR = messageRecordDAO.queryMessage4member(mid);
		if (null == lstMR || 0 == lstMR.size()) {
			return null;
		} else {
			for (MessageRecord mr : lstMR) {
				ResourceFile rf = resourceFileDAO.getFileInfoByPrimarykey(mr.getRsid());
				rltRF.add(rf);
			}
			return rltRF;
		}
	}

}
