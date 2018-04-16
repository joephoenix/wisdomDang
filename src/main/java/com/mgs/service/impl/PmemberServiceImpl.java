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

	@Override
	public List<Pmember> queryMembersByCondition(String condition) {
		if ("" == condition || null == condition) {
			return pmemberDAO.queryEntireMembers();
		} else {
			return pmemberDAO.queryMembersByUsername(condition);
		}
	}

	@Override
	public Pmember getMemberInformation(String id) {
		return pmemberDAO.getMemInfoByPrimaryKey(id);
	}

}
