package com.mgs.service;

import java.util.List;

import com.mgs.entity.Plog;

public interface PlogService {
	
	List<Plog> queryLogsByMember (String mid);
	
	List<Plog> queryEntireLogsOfDaily();
	
	String addLogForMember(Plog plog);
	
	Integer initLogsForMembers();

}
