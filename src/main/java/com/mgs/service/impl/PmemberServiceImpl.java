package com.mgs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.PlogDAO;
import com.mgs.dao.PmemberDAO;
import com.mgs.entity.Plog;
import com.mgs.entity.Pmember;
import com.mgs.service.PmemberService;

@Service
public class PmemberServiceImpl implements PmemberService {

	@Resource
	private PmemberDAO pmemberDAO;

	@Resource
	private PlogDAO plogDAO;

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

	@Override
	public String addNewMember(Pmember npm) {
		int rlt = pmemberDAO.addNewMember(npm);
		if (rlt > 0) {
			return npm.getId();
		} else {
			return "generate new member failed";
		}
	}

	@Override
	public Map<String, Object> checkUserState(String username) {
		Map<String, Object> rm = new HashMap<String, Object>();
		List<Pmember> memList = pmemberDAO.queryEntireMembers();
		if (null == memList || 0 == memList.size()) {
			rm.put("code", "UserNotExist");
			return rm;
		} else if (memList.size() > 1) {
			rm.put("code", "UserNotLegal");
			return rm;
		} else {
			rm.put("code", "OK");
			rm.put("member", memList.get(0));
			return rm;
		}
	}

	@Override
	public String isLoginState(Pmember pmb) {
		String memID = pmb.getId();
		Plog lastLog = plogDAO.getLastLogOfMember(memID);
		Date now = new Date();
		int seconds = (int) (now.getTime() - lastLog.getRecordtm().getTime() / 1000 / 60);
		if (seconds > 30) {
			return "NotLogin";
		} else {
			return memID;
		}
	}

}
