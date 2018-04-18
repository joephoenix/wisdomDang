package com.mgs.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mgs.entity.Role;

public interface RoleService {

	String generateNewRole(Role role);

	Role getRoleByNamesearch(@Param("name") String name);

	Role getChooseRoleById(@Param("id") String id);

	List<Role> queryEntireRoles();

	Integer removeChooseRole(@Param("id") String id);
}
