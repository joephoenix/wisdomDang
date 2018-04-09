package com.mgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgs.entity.Pmember;
import com.mgs.service.PmemberService;

@Controller
public class MemberController {
	
	@Autowired
	private PmemberService pmemberService;
	
	@RequestMapping("/")
	@ResponseBody
	public List<Pmember> viewALL(){
		return pmemberService.getMembersByCondition("");
	}

}
