package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.PowerDAO;
import com.mgs.entity.Power;
import com.mgs.service.PowerService;

@Service
public class PowerServiceImpl implements PowerService {

	@Resource
	private PowerDAO powerDAO;

	@Override
	public Power getPowerInfomation(String id) {
		return powerDAO.getPowerInfoByPrimaryKey(id);
	}

	@Override
	public List<Power> queryEntirePowers() {
		return powerDAO.queryEntierPowers();
	}

	@Override
	public List<Power> findPowerListWithName(String name) {
		return powerDAO.queryPowersWithName(name);
	}

	@Override
	public List<Power> findPowerListEqualLevel(Integer level) {
		return powerDAO.queryPowersByLevel(level);
	}

	@Override
	public List<Power> findPowerListEqualType(Integer type) {
		return powerDAO.queryPowersByType(type);
	}

	@Override
	public String generateNewPowerReturnPrimarykey(Power power) {
		Integer rlt = powerDAO.addNewPower(power);
		if (rlt > 0) {
			return power.getId();
		} else {
			return "generate new power failed";
		}
	}
}
