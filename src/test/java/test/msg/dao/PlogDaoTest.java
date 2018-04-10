package test.msg.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mgs.dao.PlogDAO;
import com.mgs.entity.Plog;

import test.msg.BaseUnitTest;

public class PlogDaoTest extends BaseUnitTest {

	@Autowired
	private static PlogDAO plogDAO;

	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		plogDAO = (PlogDAO) context.getBean("plogDAO");
	}

	@Test
	public void addPlog() {
		Plog log = new Plog();
		log.setMid("244fd581-3b06-11e8-b744-525400368cb9");
		log.setLongitude("120.203081");
		log.setLatitude("30.600326");
		int rls = plogDAO.addLogForMember(log);
		System.out.println("add " + rls + " log successfully!");
	}
}
