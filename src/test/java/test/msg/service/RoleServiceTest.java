package test.msg.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mgs.entity.Role;
import com.mgs.service.RoleService;

public class RoleServiceTest {

	@Autowired
	private static RoleService roleService;

	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		roleService = (RoleService) context.getBean("roleServiceImpl");
	}

	@Test
	public void testGenerateRole() {
		Role role = new Role(); 
		role.setRname("shuji");
		role.setRdesc("支部书记");
		role.setCreater("0");
		String id = roleService.generateNewRole(role);
		Role nr = roleService.getChooseRoleById(id);
		System.out.println("the new role is " + nr.getRname() + " and description is " + nr.getRdesc());
	}

}
