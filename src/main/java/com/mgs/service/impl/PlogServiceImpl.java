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

	@Override
	public List<Plog> queryLogsByMember(String mid) {
		return plogDAO.queryLogsByMember(mid);
	}

	@Override
	public List<Plog> queryEntireLogsOfDaily() {
		List<Plog> rlogs = new ArrayList<Plog>();
		List<Pmember> listMem = pmemberDAO.queryEntireMembers();
		for (Pmember pm : listMem) {
			Plog log = plogDAO.getLastLogOfMember(pm.getId());
			rlogs.add(log);
		}
		return rlogs;
	}

	@Override
	public String addLogForMember(Plog plog) {
		int rlt = plogDAO.addLogForMember(plog);
		if (rlt > 0) {
			return plog.getId();
		} else {
			return "insert log failed!";
		}
	}

	@Override
	public Integer initLogsForMembers() {
		List<Pmember> listMem = pmemberDAO.queryEntireMembers();
		Integer rlts = 0;
		for (Pmember pm : listMem) {
			Plog nlg = new Plog();
			nlg.setMid(pm.getId());
			int wdMin = 300000;
			int wdMax = 450000;
			int wdtail = (int) (wdMin + (wdMax - wdMin) * Math.random());
			double wd = 30 + wdtail * 0.000001;
			nlg.setLatitude(String.valueOf(wd));
			int jdtail = (int) (500000 * Math.random());
			if (jdtail > 200000) {
				nlg.setLongitude(String.valueOf(120 + jdtail * 0.000001));
			} else {
				nlg.setLongitude(String.valueOf(120 - jdtail * 0.000001));
			}
			rlts += plogDAO.addLogForMember(nlg);
		}
		return rlts;
	}

	@Override
	public Plog getLastLog4ChooseMember(String mid) {
		return plogDAO.getLastLogOfMember(mid);
	}

}
