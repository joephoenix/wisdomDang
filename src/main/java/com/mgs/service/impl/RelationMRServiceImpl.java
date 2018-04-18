package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.RelationMRDAO;
import com.mgs.entity.RelationMR;
import com.mgs.service.RelationMRService;

@Service
public class RelationMRServiceImpl implements RelationMRService {

	@Resource
	private RelationMRDAO relationMRDAO;

	@Override
	public String generateRelation4MemberRole(RelationMR rmr) {
		int rlt = relationMRDAO.giveChooseRole2Member(rmr);
		if (rlt > 0) {
			return rmr.getId();
		} else {
			return "create relation for member and role failed";
		}
	}

	@Override
	public List<RelationMR> findEntireRoles4Member(String mid) {
		return relationMRDAO.queryRoles4Member(mid);
	}

	@Override
	public List<RelationMR> findEntireMembers4Role(String rid) {
		return relationMRDAO.queryMembersWithRole(rid);
	}

	@Override
	public RelationMR getRelationByMemberRole(String mid, String rid) {
		return relationMRDAO.getRelationByMemberRole(mid, rid);
	}

	@Override
	public boolean switchStateOfRelation(RelationMR rmr) {
		if (null == rmr) {
			return false;
		} else {
			if (null == rmr.getId() || null == rmr.getState()) {
				return false;
			} else {
				if (0 == rmr.getState()) {
					int rlt = relationMRDAO.reviseRole2Member(rmr.getId());
					if (rlt > 0) {
						return true;
					} else {
						return false;
					}
				} else {
					int rlt = relationMRDAO.removeRoleFromMember(rmr.getId());
					if (rlt > 0) {
						return true;
					} else {
						return false;
					}
				}
			}
		}

	}

}
