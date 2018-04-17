package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.PorganizeDAO;
import com.mgs.entity.Porganize;
import com.mgs.service.PorganizeService;

@Service
public class PorganizeServiceImpl implements PorganizeService {

	@Resource
	private PorganizeDAO porganizeDAO;

	@Override
	public Porganize getOrganizeInfo(String id) {
		return porganizeDAO.getOrgInfoById(id);
	}

	@Override
	public String addNewOrganizeAndInit(Porganize org) {
		int rlt = porganizeDAO.addNewOrganize(org);
		if (rlt > 0) {
			return org.getId();
		} else {
			return "insert organize failed!";
		}
	}

	@Override
	public List<Porganize> viewEntireOrganizes() {
		return porganizeDAO.queryEntireOrganizes();
	}

	@Override
	public List<Porganize> ergodicSubOrganizes(String fid) {
		return porganizeDAO.querySubOrganizes(fid);
	}

	@Override
	public int modifyNameForOrganize(String name, String id) {
		try {
			porganizeDAO.modifyOrganizeName(name, id);
			return 1;
		} catch (Exception ex) {
			return 0;
		}
	}

	@Override
	public int removeOrganizeById(String id) {
		try {
			porganizeDAO.moveChooseOrganize(id);
			return 1;
		} catch (Exception ex) {
			return 0;
		}
	}

}
