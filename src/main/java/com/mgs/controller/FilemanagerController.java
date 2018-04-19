package com.mgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mgs.entity.Sfile;
import com.mgs.service.SfileService;
import com.mgs.view.FileInfo;

@Controller
public class FilemanagerController {

	@Autowired
	private SfileService sfileService;
	
	public List<FileInfo> listStudyFiles(){
		List<FileInfo> rlst = new ArrayList<FileInfo>();
		List<Sfile> slist = sfileService.querySavefileList();
		for(Sfile sf : slist) {
			FileInfo fi =  new FileInfo();
			fi.setFileName(sf.getFname());
			fi.setFilePath(sf.getFpath());
			fi.setFlieid(sf.getId());
			rlst.add(fi);
		}
		return rlst;
	}
}
