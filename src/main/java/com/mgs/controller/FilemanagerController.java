package com.mgs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
public class FilemanagerController {

	@Autowired
	private SfileService sfileService;

	@Autowired
	private PmemberService pmemberService;

	@RequestMapping("/showStudyFiles,method=RequestMethod.POST")
	@ResponseBody
	public Map<String, Object> showStudyFiles(String username) {
		Map<String, Object> mrlt = new HashMap<String, Object>();
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

	@RequestMapping("/uploadSelectFile,method=RequestMethod.POST")
	@ResponseBody
	public Map<String, Object> uploadSelectFile(String username, MultipartFile attach) throws Exception {
		Map<String, Object> mrlt = new HashMap<String, Object>();
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
