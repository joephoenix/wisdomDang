package com.mgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgs.entity.Plog;
import com.mgs.entity.Pmember;
import com.mgs.service.PlogService;
import com.mgs.service.PmemberService;

@Controller
public class MemberController {

	@Autowired
	private PmemberService pmemberService;

	@Autowired
	private PlogService plogService;

	@RequestMapping("/login,method=RequestMethod.POST")
	@ResponseBody
	public String loginAction(String username, String password) {
		List<Pmember> lstMember = pmemberService.queryMembersByCondition(username);
		if (null == lstMember || 0 < lstMember.size()) {
			return "MemberNotExist";
		} else {
			Pmember mem = lstMember.get(0);
			String cpwd = mem.getPword();
			if (!cpwd.equals(password)) {
				return "MemberNotExist";
			} else {
				return "successful";
			}
		}
	}

	@RequestMapping("/")
	@ResponseBody
	public List<Pmember> viewALL() {
		return pmemberService.queryMembersByCondition("");
	}

	@RequestMapping("/aixes")
	@ResponseBody
	public List<Plog> viewAixes() {
		return plogService.queryEntireLogsOfDaily();
	}

}
