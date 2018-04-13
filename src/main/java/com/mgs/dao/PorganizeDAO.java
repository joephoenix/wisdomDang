package com.mgs.dao;

import java.util.List;

import com.mgs.entity.Porganize;

public interface PorganizeDAO {

	Porganize getOrgInfoById(String id);

	List<Porganize> queryEntireOrganizes();

	List<Porganize> querySubOrganizes(String fid);

	List<Porganize> queryAbandonOrganizes();
	
	List<Porganize> queryOrganizesWithOrgname(String oname);

	void addNewOrganize(Porganize org);

	void adjustOrganizeChief(String fid, String id);

	void adjustOrganizeOrder(Integer order, String id);

	void modifyOrganizeName(String name, String id);

	void moveChooseOrganize(String id);

	void reviseChooseOrg(String id);

}
