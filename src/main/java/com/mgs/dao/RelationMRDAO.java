package com.mgs.dao;

import java.util.List;

import com.mgs.entity.RelationMR;

public interface RelationMRDAO {
	
	RelationMR getRelationWithMember2Role(String id);
	
	int giveChooseRole2Member(RelationMR rmr);
	
	List<RelationMR> queryRoles4Member(String mid);
	
	List<RelationMR> queryMembersWithRole(String rid);
	
	RelationMR getRelationByMemberRole(String mid, String rid);
	
	int removeRoleFromMember(String id);
	
	int reviseRole2Member(String id);

}
