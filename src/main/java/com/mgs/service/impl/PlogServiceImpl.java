package com.mgs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.PlogDAO;
import com.mgs.dao.PmemberDAO;
import com.mgs.entity.Plog;
import com.mgs.entity.Pmember;
import com.mgs.service.PlogService;

@Service
public class PlogServiceImpl implements PlogService {

	@Resource
	private PlogDAO plogDAO;

	@Resource
	private PmemberDAO pmemberDAO;

	public List<Plog> queryLogsByMember(String mid) {
		return plogDAO.queryLogsByMember(mid);
	}

	public List<Plog> queryAllLogsInDays() {
		List<Plog> rlogs = new ArrayList<Plog>();
		List<Pmember> listMem = pmemberDAO.queryEntireMembers();
		for (Pmember pm : listMem) {
			Plog log = plogDAO.getLastLogOfMember(pm.getId());
			rlogs.add(log);
		}
		return rlogs;
	}

	public void addLogForMember(Plog plog) {
		plogDAO.addLogForMember(plog);
	}

}
