package test.msg.service;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mgs.entity.Porganize;
import com.mgs.service.PorganizeService;

public class POrgServiceTest {

	@Autowired
	private static PorganizeService porganizeService;

	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		porganizeService = (PorganizeService) context.getBean("porganizeServiceImpl");
	}

	@Test
	@Ignore
	public void testCreateOrganize() {
		Porganize org = new Porganize();
		org.setOname("下渚湖街道支部");
		org.setFid("f3bf5b49-3f2c-11e8-b744-525400368cb9");
		org.setOorder(4);
		org.setCreater("a8080f4c-3c7c-11e8-b744-525400368cb9");
		porganizeService.addNewOrganizeAndInit(org);
	}

	@Test
	public void testQueryOrganizes() {
		List<Porganize> rootOrg = porganizeService.ergodicSubOrganizes("0");
		if (rootOrg.size() > 0) {
			Porganize root = rootOrg.get(0);
			List<Porganize> l1Orgs = porganizeService.ergodicSubOrganizes(root.getId());
			if(l1Orgs.size() > 0) {
				for(Porganize l1o : l1Orgs) {
					System.out.println(l1o.getOname() + " is NO." + l1o.getOorder() + " dang Organize in the level 1");
				}
			}else {
				System.out.println("can't find the organize of leve 1");
			}
		} else {
			System.out.println("can't find the root organize");
		}
	}
}
