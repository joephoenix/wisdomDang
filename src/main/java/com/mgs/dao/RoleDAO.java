package com.mgs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mgs.entity.Role;

public interface RoleDAO {

	Role getRoleInfoById(@Param("id") String id);

	List<Role> queryEntireRoles();

	List<Role> queryAbandonRoles();

	List<Role> queryRoleWithName(@Param("name") String name);

	List<Role> queryRoleWithDesc(@Param("desc") String desc);

	int addNewRole(Role role);

	int modifyRoleNameById(@Param("name") String name, @Param("id") String id);

	int modifyRoleDescById(@Param("desc") String desc, @Param("id") String id);

	int removeRoleById(@Param("id") String id);

	int reviseRoleById(@Param("id") String id);

}
