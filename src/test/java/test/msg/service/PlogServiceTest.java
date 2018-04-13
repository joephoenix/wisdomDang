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
	public void initAttendLogOfMembers() {
		plogService.initLogsForMembers();
	}

	@Test
	public void testQueryAllLogsInDays() {
		List<Plog> logs = plogService.queryEntireLogsOfDaily();
		if (logs.size() > 0) {
			for (Plog lg : logs) {
				if (null == lg) {
					System.out.println("there are no log data!");
				} else {
					System.out.println("at " + lg.getRecordtm() + " is at dongjing " + lg.getLongitude() + " beiwei "
							+ lg.getLatitude());
				}
			}
		} else {
			System.out.println("there are no data!");
		}
	}
}
