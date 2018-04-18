package com.mgs.service;

import java.util.List;

import com.mgs.entity.RelationMR;

public interface RelationMRService {
	
	String generateRelation4MemberRole(RelationMR rmr);
	
	List<RelationMR> findEntireRoles4Member(String mid);
	
	List<RelationMR> findEntireMembers4Role(String rid);
	
	RelationMR getRelationByMemberRole(String mid, String rid);
	
	boolean switchStateOfRelation(RelationMR rmr);

}
