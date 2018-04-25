package com.mgs.service;

import java.util.List;

import com.mgs.entity.Plog;

public interface PlogService {
	
	List<Plog> queryLogsByMember (String mid);
	
	List<Plog> queryEntireLogsOfDaily();
	
	Plog getLastLog4ChooseMember(String mid);
	
	String addLogForMember(Plog plog);
	
	Integer initLogsForMembers();

}
