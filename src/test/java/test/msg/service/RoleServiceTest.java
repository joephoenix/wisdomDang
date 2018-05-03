package test.msg.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgs.entity.Role;
import com.mgs.service.RoleService;

import test.msg.BaseUnitTest;

public class RoleServiceTest extends BaseUnitTest {

	@Autowired
	private RoleService roleService;

	@Test
	public void testGenerateRole() {
		Role role = new Role(); 
		role.setRname("dangyuan");
		role.setRdesc("普通党员");
		role.setCreater("0");
		String id = roleService.generateNewRole(role);
		Role nr = roleService.getChooseRoleById(id);
		System.out.println("the new role is " + nr.getRname() + " and description is " + nr.getRdesc());
	}

}
