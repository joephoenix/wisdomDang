package com.mgs.service;

import java.util.List;

import com.mgs.entity.Power;
import com.mgs.entity.RelationMO;

public interface RelationService {

	List<Power> lstMenuByMemberId(String memId);

	List<RelationMO> findReltionsByMember(String memId);
	
	List<RelationMO> findRelationByOrganize(String orgId);

}
