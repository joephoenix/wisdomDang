package com.mgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.mgs.entity.Sfile;
import com.mgs.fastdfs.FastDFSFile;
import com.mgs.service.SfileService;
import com.mgs.view.FileInfo;

@Controller
public class FilemanagerController {

	@Autowired
	private SfileService sfileService;

	@RequestMapping("/flist")
	@ResponseBody
	public List<FileInfo> listStudyFiles() {
		List<FileInfo> rlst = new ArrayList<FileInfo>();
		List<Sfile> slist = sfileService.querySavefileList();
		for (Sfile sf : slist) {
			FileInfo fi = new FileInfo();
			fi.setFileName(sf.getFname());
			fi.setFilePath(sf.getFpath());
			fi.setFlieid(sf.getId());
			rlst.add(fi);
		}
		return rlst;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartFile attach) throws Exception {
		String username = "admin"; // get info from session
		String ext = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf(".") + 1);
		String name = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf("/") + 1);
		String rname = name.substring(name.lastIndexOf(".") + 1);
		FastDFSFile ff = new FastDFSFile();
		ff.setContent(attach.getBytes());
		ff.setName(rname);
		ff.setExt(ext);
		ff.setLength(String.valueOf(attach.getSize()));
		ff.setAuthor(username);
		String sid = sfileService.uploadChooseFileToFastdfs(ff);
		Sfile sf = sfileService.getSaveFileInformation(sid);
		return sf.getFpath();
	}
}
