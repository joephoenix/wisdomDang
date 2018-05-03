package com.mgs.service;

import java.util.List;

import com.mgs.entity.Power;

public interface PowerService {

	Power getPowerInfomation(String id);
	
	List<Power> queryEntirePowers();

	List<Power> findPowerListWithName(String name);

	List<Power> findPowerListEqualLevel(Integer level);

	List<Power> findPowerListEqualType(Integer type);

	String generateNewPowerReturnPrimarykey(Power power);

}
