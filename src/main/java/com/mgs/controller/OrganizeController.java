package com.mgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mgs.service.PmemberService;
import com.mgs.service.PorganizeService;

@Controller
public class OrganizeController {
	
	@Autowired
	private PmemberService pmemberService;
	
	@Autowired
	private PorganizeService porganizeService;
	
	

}
