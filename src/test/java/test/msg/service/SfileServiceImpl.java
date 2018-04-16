package test.msg.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.entity.Sfile;
import com.mgs.service.SfileService;

import test.msg.BaseUnitTest;

public class SfileServiceImpl extends BaseUnitTest {

	@Autowired
	private SfileService sfileService;

	@Test
	public void testSavefileAction() {
		Sfile file = new Sfile();
		file.setFname("testfile");
		file.setFext(".jpg");
		file.setFpath("http://ddddd/testfile.jpg");
		file.setCreater("7aef4537-3c7e-11e8-b744-525400368cb9");
		String sid = sfileService.generateSavefileRecord(file);
		if (null == sid) {
			System.out.println("fiel save id is null, so save failed");
		} else {
			Sfile rlt = sfileService.getSaveFileInformation(sid);
			if (null == rlt) {
				System.out.println("can't find the choose file");
			} else {
				System.out.println(
						"save a new file that name is " + rlt.getFname() + " and address is " + rlt.getFpath());
			}
		}

	}

}
