package com.mgs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(value = "/showStudyFiles", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showStudyFiles(@RequestBody Rqbody body) {
		Map<String, Object> mrlt = new HashMap<String, Object>();
		String username = body.getUsername();
		if (null == username) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			mrlt.put("code", memst.get("code"));
			return mrlt;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				mrlt.put("code", "PermissionBanished");
				return mrlt;
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
				mrlt.put("code", "GetFileListSuccess");
				mrlt.put("flist", rlst);
				return mrlt;
			}
		}
	}

	@RequestMapping(value = "/showFileInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showFileInfo(@RequestBody Rqbody body) {
		Map<String, Object> mrlt = new HashMap<String, Object>();
		String username = body.getUsername();
		String fileId = body.getFileid();
		if (null == username) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			mrlt.put("code", memst.get("code"));
			return mrlt;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				mrlt.put("code", "PermissionBanished");
				return mrlt;
			} else {
				FileInfo fi = new FileInfo();
				Sfile sf = sfileService.getSaveFileInformation(fileId);
				fi.setFileName(sf.getFname());
				fi.setFilePath(sf.getFpath());
				fi.setFlieid(sf.getId());
				mrlt.put("code", "GetFileListSuccess");
				mrlt.put("fInfo", fi);
				return mrlt;
			}
		}
	}

	@RequestMapping(value = "/uploadSelectFile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadSelectFile(@RequestBody Rqbody body) throws Exception {
		Map<String, Object> mrlt = new HashMap<String, Object>();
		String username = body.getUsername();
		MultipartFile attach = body.getAttach();
		if (null == username) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			mrlt.put("code", memst.get("code"));
			return mrlt;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				mrlt.put("code", "PermissionBanished");
				return mrlt;
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
				mrlt.put("code", "uploadSuccess");
				mrlt.put("path", sf.getFpath());
				return mrlt;
			}
		}
	}
}
