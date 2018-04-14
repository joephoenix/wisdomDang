package com.mgs.service;

import java.util.List;

import com.mgs.entity.Role;

public interface RoleService {

	String generateNewRole(Role role);
	
	Role getChooseRoleById(String id);
	
	List<Role> queryEntireRoles();
	
	Integer removeChooseRole(String id);
 }
