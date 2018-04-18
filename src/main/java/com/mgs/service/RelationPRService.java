package com.mgs.service;

import java.util.List;

import com.mgs.entity.RelationPR;

public interface RelationPRService {

	List<RelationPR> listPowersFromRole(String rid);

	List<RelationPR> listRolesWithPower(String pid);

	String givePower2Role(RelationPR rpr);

	int removePowerRoleRelation(String id);

}
