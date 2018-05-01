package com.mgs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.PowerDAO;
import com.mgs.dao.RelationMODAO;
import com.mgs.dao.RelationMRDAO;
import com.mgs.dao.RelationPRDAO;
import com.mgs.entity.Power;
import com.mgs.entity.RelationMO;
import com.mgs.entity.RelationMR;
import com.mgs.entity.RelationPR;
import com.mgs.service.RelationService;

@Service
public class RelationServiceImpl implements RelationService {
	
	@Resource
	private RelationMODAO relationMODAO;

	@Resource
	private RelationMRDAO relationMRDAO;

	@Resource
	private RelationPRDAO relationPRDAO;

	@Resource
	private PowerDAO powerDAO;

	@Override
	public List<Power> lstMenuByMemberId(String memId) {
		List<Power> rplist = new ArrayList<Power>();
		List<RelationMR> RMList = relationMRDAO.queryRoles4Member(memId);
		if (null == RMList || 0 == RMList.size()) {
			return null;
		} else {
			for (RelationMR rmr : RMList) {
				String rid = rmr.getRid();
				List<RelationPR> PRList = relationPRDAO.queryPowers4Role(rid);
				if (null != PRList) {
					for (RelationPR rpr : PRList) {
						String pid = rpr.getPid();
						Power pi = powerDAO.getPowerInfoByPrimaryKey(pid);
						rplist.add(pi);
					}
				}
			}
			return rplist;
		}
	}

	@Override
	public List<RelationMO> findReltionsByMember(String memId) {
		return relationMODAO.queryReltionsByMember(memId);
	}

	@Override
	public List<RelationMO> findRelationByOrganize(String orgId) {
		return relationMODAO.queryReltionsByOrganize(orgId);
	}

}
