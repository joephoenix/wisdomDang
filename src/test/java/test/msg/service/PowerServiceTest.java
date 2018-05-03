package test.msg.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgs.entity.Power;
import com.mgs.service.PowerService;

import test.msg.BaseUnitTest;

public class PowerServiceTest extends BaseUnitTest {
	
	@Autowired
	private PowerService powerService;
		
	@Test
	public void testGenerateMethod() {
		Power power = new Power();
		power.setPname("文件详情");
		power.setPcode("file-03-01");
		power.setPtype(2);
		power.setPlevel(3);
		power.setCreater("244fd581-3b06-11e8-b744-525400368cb9");
		String pid = powerService.generateNewPowerReturnPrimarykey(power);
		//query new power infomation
		Power p = powerService.getPowerInfomation(pid);
		if(null == p) {
			System.out.println("generate new power failed");
		}else {
			System.out.println("the new power is " + p.getPname() + " and the code is " + p.getPcode() + " in level " + p.getPlevel());
		}
		
	}

}
