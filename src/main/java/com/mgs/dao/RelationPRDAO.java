package com.mgs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mgs.entity.RelationPR;

public interface RelationPRDAO {

	RelationPR getRelationOfPower2Role(@Param("id") String id);

	List<RelationPR> queryPowers4Role(@Param("rid") String rid);

	List<RelationPR> queryRoles4Power(@Param("pid") String pid);

	int givePower4Role(RelationPR rpr);

	int adjustPower4OtherRole(@Param("rid") String rid, @Param("id") String id);

	int removePowerOutRole(@Param("id") String id);

	int reviesePower4Role(@Param("id") String id);
}
