package com.mgs.dao;

import java.util.List;

import com.mgs.entity.PointRecord;

public interface PointRecordDAO {

	PointRecord getPointRecordDetail(String id);

	List<PointRecord> queryRecordsByMember(String mid);

	List<PointRecord> queryRecordsByTypeMember(String mid, Integer type);

	int generateNewPointRecord(PointRecord pointRecord);

}
