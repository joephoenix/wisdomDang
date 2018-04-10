package test.msg.service;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mgs.entity.Plog;
import com.mgs.service.PlogService;

public class PlogServiceTest {

	@Autowired
	private static PlogService plogService;

	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		plogService = (PlogService) context.getBean("plogServiceImpl");
	}

	@Test
	public void testQueryAllLogsInDays() {
		List<Plog> logs = plogService.queryAllLogsInDays();
		if (logs.size() > 0) {
			for (Plog lg : logs) {
				System.out.println("at " + lg.getRecordtime() + "is at dongjing " + lg.getLongitude() + " beiwei "
						+ lg.getLatitude());
			}
		} else {
			System.out.println("there are no data!");
		}
	}
}
