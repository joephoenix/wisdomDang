package test.msg.service;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mgs.entity.Pmember;
import com.mgs.service.PmemberService;

public class PmemberServiceTest {

	@Autowired
	private static PmemberService pmemberService;

	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		pmemberService = (PmemberService) context.getBean("pmemberServiceImpl");
	}

	@Test
	public void testGetMembers() {
		List<Pmember> ms = pmemberService.getMembersByCondition("");
		if(ms.size() > 0) {
			for(Pmember pm : ms){
				String dang = "dang yuan";
				if(pm.getIsParty() == 0) {
					dang = "qun zhong";
				}
				System.out.println(pm.getRmane() + " is " + dang);
			}
		}else {
			System.out.println("there are no data!");
		}
	}
}
