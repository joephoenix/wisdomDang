package com.mgs.dao;

import java.util.List;

import com.mgs.entity.PResource;

public interface PResourceDAO {

	PResource getResourceDetailByid(String id);

	List<PResource> queryResourceByName(String name);

	List<PResource> queryResourceByType(Integer fileTye);

	List<PResource> queryEntireResource();

	List<PResource> queryAbandonResource();

	int generateNewResource(PResource resource);

	int adjustResourceParameters(PResource resource);

	int removeChooseResource(String id);

	int reviseChooseResource(String id);

}
