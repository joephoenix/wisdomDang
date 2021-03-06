package com.mgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.mgs.entity.ResourceFile;
import com.mgs.fastdfs.FastDFSFile;
import com.mgs.service.PmemberService;
import com.mgs.service.ResourceFileService;
import com.mgs.view.FileInfo;
import com.mgs.view.Rqbody;

@Controller
public class FilemanagerController {

	@Autowired
	private ResourceFileService resourceFileService;

	@Autowired
	private PmemberService pmemberService;

	@RequestMapping(value = "/showStudyFiles", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showStudyFiles(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			List<FileInfo> rlst = new ArrayList<FileInfo>();
			List<ResourceFile> slist = resourceFileService.querySavefileList();
			for (ResourceFile sf : slist) {
				FileInfo fi = new FileInfo();
				fi.setFileName(sf.getFname());
				fi.setFilePath(sf.getFpath());
				fi.setFlieid(sf.getId());
				rlst.add(fi);
			}
			jsonObject.put("result", rlst);
			jsonObject.put("message", "GetFileList");
			jsonObject.put("status", "success");
			return jsonObject;
		} else {
			return jsonObject;
		}
	}

	@RequestMapping(value = "/showFileInfo", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showFileInfo(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		String fileId = body.getFileid();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			FileInfo fi = new FileInfo();
			ResourceFile sf = resourceFileService.getSaveFileInformation(fileId);
			fi.setFileName(sf.getFname());
			fi.setFilePath(sf.getFpath());
			fi.setFlieid(sf.getId());
			jsonObject.put("result", fi);
			jsonObject.put("message", "GetFileInformation");
			jsonObject.put("status", "success");
			return jsonObject;
		} else {
			return jsonObject;
		}
	}

	@RequestMapping(value = "/uploadSelectFile", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject uploadSelectFile(@RequestBody Rqbody body) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		MultipartFile attach = body.getAttach();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String ext = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf(".") + 1);
			String name = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf("/") + 1);
			String rname = name.substring(name.lastIndexOf(".") + 1);
			FastDFSFile ff = new FastDFSFile();
			ff.setContent(attach.getBytes());
			ff.setName(rname);
			ff.setExt(ext);
			ff.setLength(String.valueOf(attach.getSize()));
			ff.setAuthor(username);
			String sid = resourceFileService.uploadChooseFileToFastdfs(ff);
			ResourceFile sf = resourceFileService.getSaveFileInformation(sid);
			jsonObject.put("result", sf.getFpath());
			jsonObject.put("message", "uploadLocationFile");
			jsonObject.put("status", "success");
			return jsonObject;
		} else {
			return jsonObject;
		}
	}
}
