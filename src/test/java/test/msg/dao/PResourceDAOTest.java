package test.msg.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.dao.PResourceDAO;
import com.mgs.entity.PResource;

import test.msg.BaseUnitTest;

public class PResourceDAOTest extends BaseUnitTest {

	@Autowired
	private PResourceDAO pResourceDAO;

	@Test	
	public void testGenerateResource() {
		PResource pr = new PResource();
		pr.setFileName("2018年第8次党内民主生活会");
		pr.setFileDescript("2018年4月12日早上10：00召开党内生活会");
		pr.setFileContext("本次生活会，党员们积极参加，反向很好");
		pr.setFileType(2);
		pr.setTaskPoint("6208763d-542d-11e8-b744-525400368cb9");
		pr.setTimeLength(30000);
		pr.setCreater("da482273-4220-11e8-b744-525400368cb9");
		int rlt = pResourceDAO.generateNewResource(pr);
		if (rlt > 0) {
			System.out.println("init study Resource successfully!");
		} else {
			System.out.println("init study Resource failly!");
		}
	}

	@Test
	@Ignore
	public void testQueryResource() {
		List<PResource> rlst = pResourceDAO.queryResourceByType(2);
		if (null == rlst || 0 == rlst.size()) {
			System.out.println("read study Resource failly!");
		} else {
			for (PResource res : rlst) {
				System.out.println("there is a " + res.getFileName() + " that context is " + res.getFileContext());
			}
		}
	}
}
