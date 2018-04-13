package test.msg.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mgs.dao.PmemberDAO;
import com.mgs.entity.Pmember;

import test.msg.BaseUnitTest;

public class MemberDaoTest extends BaseUnitTest {

	@Autowired
	private static PmemberDAO pmemberDAO;

	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		pmemberDAO = (PmemberDAO) context.getBean("pmemberDAO");
	}

	@Test
	public void addNewMember() {
		Pmember pm = new Pmember();
		pm.setUname("mazhangwei");
		pm.setRname("ÂíÕÅÍþ");
		pm.setPword("123456");
		pm.setIsParty(0);
		int rls = pmemberDAO.addNewMember(pm);
		System.out.println("add " + rls + " record successfully!");
	}

}
