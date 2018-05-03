package com.mgs.service;

import java.util.List;

import com.mgs.entity.Porganize;

public interface PorganizeService {

	Porganize getOrganizeInfo(String id);

	String addNewOrganizeAndInit(Porganize org);

	List<Porganize> viewEntireOrganizes();

	void ergodicSubOrganizes(String fid, List<Porganize> result);

	int modifyNameForOrganize(String name, String id);

	int removeOrganizeById(String id);

}
