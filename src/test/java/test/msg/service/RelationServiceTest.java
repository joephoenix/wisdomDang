package test.msg.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.entity.Power;
import com.mgs.service.RelationService;

import test.msg.BaseUnitTest;

public class RelationServiceTest extends BaseUnitTest {

	@Autowired
	private RelationService relationService;

	/**
	 * 测试方法，读取一个用户具有的权限
	 */
	@Test
	public void testShowMenus() {
		List<Power> rplist = relationService.lstMenuByMemberId("da482273-4220-11e8-b744-525400368cb9");
		for (Power pr : rplist) {
			System.out.println("the power is  " + pr.getPname() + " and the level is " + pr.getPlevel()
					+ " and the type is " + pr.getPtype());
		}
	}

}
