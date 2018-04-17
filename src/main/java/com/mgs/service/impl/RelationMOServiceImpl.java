package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.RelationMODAO;
import com.mgs.entity.RelationMO;
import com.mgs.service.RelationMOService;

@Service
public class RelationMOServiceImpl implements RelationMOService {

	@Resource
	private RelationMODAO relationMODAO;

	@Override
	public RelationMO getRelationByPrimarykey(String id) {
		return relationMODAO.getRelationByPrimarykey(id);
	}

	@Override
	public List<RelationMO> queryReltionsByMember(String mid) {
		return relationMODAO.queryReltionsByMember(mid);
	}

	@Override
	public List<RelationMO> queryReltionsByOrganize(String oid) {
		return relationMODAO.queryReltionsByOrganize(oid);
	}

	@Override
	public String addNewRelation4Member2Organize(RelationMO rmo) {
		int rlt = relationMODAO.addNewRelation4Member2Organize(rmo);
		if (rlt > 0) {
			return rmo.getId();
		} else {
			return "create relation with member and organize failed";
		}
	}

	@Override
	public int adjustRelation4Member(String oid, String id) {
		return relationMODAO.adjustRelation4Member(oid, id);
	}

	@Override
	public int removeMemberOrganizeRelation(String id) {
		return relationMODAO.removeMemberOrganizeRelation(id);
	}

	@Override
	public int reviseMemberOrganizeRelation(String id) {
		return relationMODAO.reviseMemberOrganizeRelation(id);
	}

}
