package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.RelationPRDAO;
import com.mgs.entity.RelationPR;
import com.mgs.service.RelationPRService;

@Service
public class RelationPRServiceImpl implements RelationPRService {

	@Resource
	private RelationPRDAO relationPRDAO;

	@Override
	public List<RelationPR> listPowersFromRole(String rid) {
		return relationPRDAO.queryPowers4Role(rid);
	}

	@Override
	public List<RelationPR> listRolesWithPower(String pid) {
		return relationPRDAO.queryRoles4Power(pid);
	}

	@Override
	public String givePower2Role(RelationPR rpr) {
		int rlt = relationPRDAO.givePower4Role(rpr);
		if (rlt > 0) {
			return rpr.getId();
		} else {
			return "give power to role failed";
		}
	}

	@Override
	public int removePowerRoleRelation(String id) {
		return relationPRDAO.removePowerOutRole(id);
	}

}
