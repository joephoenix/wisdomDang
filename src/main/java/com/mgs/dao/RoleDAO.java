package com.mgs.dao;

import java.util.List;

import com.mgs.entity.Role;

public interface RoleDAO {

	Role getRoleInfoById(String id);

	List<Role> queryEntireRoles();

	List<Role> queryAbandonRoles();

	List<Role> queryRoleWithName(String name);

	List<Role> queryRoleWithDesc(String desc);

	int addNewRole(Role role);

	int modifyRoleNameById(String name, String id);

	int modifyRoleDescById(String desc, String id);

	int removeRoleById(String id);

	int reviseRoleById(String id);

}
