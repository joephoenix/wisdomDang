package com.mgs.service;

import java.util.List;

import com.mgs.entity.RelationMO;

public interface RelationMOService {

	RelationMO getRelationByPrimarykey(String id);

	List<RelationMO> queryReltionsByMember(String mid);

	List<RelationMO> queryReltionsByOrganize(String oid);

	String addNewRelation4Member2Organize(RelationMO rmo);

	int adjustRelation4Member(String oid, String id);

	int removeMemberOrganizeRelation(String id);

	int reviseMemberOrganizeRelation(String id);

}
