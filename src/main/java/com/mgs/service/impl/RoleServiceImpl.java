package com.mgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mgs.dao.RoleDAO;
import com.mgs.entity.Role;
import com.mgs.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDAO roleDAO;

	@Override
	public String generateNewRole(Role role) {
		int rlt = roleDAO.addNewRole(role);
		if (rlt > 0) {
			return role.getId();
		} else {
			return "generate new role failed";
		}
	}

	@Override
	public Role getChooseRoleById(String id) {
		return roleDAO.getRoleInfoById(id);
	}

	@Override
	public Role getRoleByNamesearch(String name) {
		List<Role> roles = roleDAO.queryRoleWithName("%" + name + "%");
		if (null == roles || 0 == roles.size()) {
			return null;
		} else {
			return roles.get(0);
		}
	}

	@Override
	public List<Role> queryEntireRoles() {
		return roleDAO.queryEntireRoles();
	}

	@Override
	public Integer removeChooseRole(String id) {
		return Integer.valueOf(roleDAO.removeRoleById(id));
	}
}
