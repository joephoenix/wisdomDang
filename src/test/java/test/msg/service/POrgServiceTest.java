package test.msg.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgs.entity.Porganize;
import com.mgs.service.PorganizeService;

import test.msg.BaseUnitTest;

public class POrgServiceTest extends BaseUnitTest {

	@Autowired
	private PorganizeService porganizeService;

	@Test
	@Ignore
	public void testCreateOrganize() {
		Porganize org = new Porganize();
		org.setOname("二都支部");
		org.setFid("9f7fb65e-3f2f-11e8-b744-525400368cb9");
		org.setOorder(1);
		org.setCreater("a8080f4c-3c7c-11e8-b744-525400368cb9");
		porganizeService.addNewOrganizeAndInit(org);
	}

	@Test
	public void testQueryOrganizes() {
		List<Porganize> rootOrg = new ArrayList<Porganize>();
		porganizeService.ergodicSubOrganizes("0", rootOrg);
		if (rootOrg.size() > 0) {
			for (Porganize po : rootOrg) {
				if (!po.getFid().equals("0")) {
					String forgname = porganizeService.getOrganizeInfo(po.getFid()).getOname();
					System.out.println(po.getOname() + " is NO." + po.getOorder() + " part in the " + forgname);
				} else {
					System.out.println(po.getOname() + " is root Organize!");
				}

			}
		} else {
			System.out.println("can't find the root organize");
		}
	}
}
