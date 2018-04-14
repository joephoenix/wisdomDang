package com.mgs.dao;

import java.util.List;

import com.mgs.entity.Porganize;

public interface PorganizeDAO {

	Porganize getOrgInfoById(String id);

	List<Porganize> queryEntireOrganizes();

	List<Porganize> querySubOrganizes(String fid);

	List<Porganize> queryAbandonOrganizes();
	
	List<Porganize> queryOrganizesWithOrgname(String oname);

	int addNewOrganize(Porganize org);

	int adjustOrganizeChief(String fid, String id);

	int adjustOrganizeOrder(Integer order, String id);

	int modifyOrganizeName(String name, String id);

	int moveChooseOrganize(String id);

	int reviseChooseOrg(String id);

}
