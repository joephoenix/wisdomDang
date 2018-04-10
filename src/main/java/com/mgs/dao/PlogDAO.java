package com.mgs.dao;

import java.util.Date;
import java.util.List;

import com.mgs.entity.Plog;

public interface PlogDAO {

	Plog getlogByPrimaryKey(String id);

	List<Plog> queryLogsByMember(String mid);

	List<Plog> queryLogsBetweenTimes(Date btime, Date etime);

	/**
	 * find the last action log for a member
	 * 
	 * @param mid
	 * @return
	 */
	Plog getLastLogOfMember(String mid);

	/**
	 * add a log for a member
	 * 
	 * @param plog
	 * @return
	 */
	int addLogForMember(Plog plog);

}
