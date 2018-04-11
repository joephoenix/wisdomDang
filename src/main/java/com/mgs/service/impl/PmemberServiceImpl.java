package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.PmemberDAO;
import com.mgs.entity.Pmember;
import com.mgs.service.PmemberService;

@Service
public class PmemberServiceImpl implements PmemberService {

	@Resource
	private PmemberDAO pmemberDAO;


	public List<Pmember> queryMembersByCondition(String condition) {
		if (condition == "") {
			return pmemberDAO.selectAllMembers();
		} else {
			return pmemberDAO.selectMembersByuname("condition");
		}
	}

	public Pmember getMemberInformation(String id) {
		return pmemberDAO.selectByPrimaryKey(id);
	}

}
