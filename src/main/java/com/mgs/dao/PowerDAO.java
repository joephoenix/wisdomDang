package com.mgs.dao;

import java.util.List;

import com.mgs.entity.Power;

public interface PowerDAO {

	Power getPowerInfoByPrimaryKey(String id);

	List<Power> queryEntierPowers();

	List<Power> queryAbandonPowers();

	List<Power> queryPowersWithName(String name);

	Power getPowerBycode(String code);

	List<Power> queryPowersByLevel(Integer level);

	List<Power> queryPowersByType(Integer type);

	int addNewPower(Power power);

	int modifyPowerNameById(String name, String id);

	int modifyPowerLevelById(Integer level, String id);

	int modifyPowerTypeById(Integer type, String id);

	int removePowerByPrimaryKey(String id);

	int revisePowerByPrimaryKey(String id);

}
