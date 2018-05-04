package com.mgs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.mgs.entity.Pmember;
import com.mgs.entity.Sfile;
import com.mgs.fastdfs.FastDFSFile;
import com.mgs.service.PmemberService;
import com.mgs.service.SfileService;
import com.mgs.view.FileInfo;
import com.mgs.view.Rqbody;

@Controller
public class FilemanagerController {

	@Autowired
	private SfileService sfileService;

	@Autowired
	private PmemberService pmemberService;

	@RequestMapping(value = "/showStudyFiles", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showStudyFiles(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		if (null == username) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				jsonObject.put("message", "PermissionBanished");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
				List<FileInfo> rlst = new ArrayList<FileInfo>();
				List<Sfile> slist = sfileService.querySavefileList();
				for (Sfile sf : slist) {
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
			}
		}
	}

	@RequestMapping(value = "/showFileInfo", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showFileInfo(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		String fileId = body.getFileid();
		if (null == username) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				jsonObject.put("message", "PermissionBanished");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
				FileInfo fi = new FileInfo();
				Sfile sf = sfileService.getSaveFileInformation(fileId);
				fi.setFileName(sf.getFname());
				fi.setFilePath(sf.getFpath());
				fi.setFlieid(sf.getId());
				jsonObject.put("result", fi);
				jsonObject.put("message", "GetFileInformation");
				jsonObject.put("status", "success");
				return jsonObject;
			}
		}
	}

	@RequestMapping(value = "/uploadSelectFile", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject uploadSelectFile(@RequestBody Rqbody body) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		MultipartFile attach = body.getAttach();
		if (null == username) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				jsonObject.put("message", "PermissionBanished");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
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
				jsonObject.put("result", sf.getFpath());
				jsonObject.put("message", "uploadLocationFile");
				jsonObject.put("status", "success");
				return jsonObject;
			}
		}
	}
}
