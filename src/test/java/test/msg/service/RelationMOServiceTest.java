package test.msg.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgs.entity.RelationMO;
import com.mgs.service.RelationMOService;

import test.msg.BaseUnitTest;

public class RelationMOServiceTest extends BaseUnitTest {

	@Autowired
	private RelationMOService relationMOService;

	@Test
	public void testRelationMethod() {
		RelationMO nrmo = new RelationMO();
		nrmo.setMid("a8741fa0-3c7d-11e8-b744-525400368cb9");
		nrmo.setOid("6009130b-4de7-11e8-b744-525400368cb9");
		nrmo.setCreater("da482273-4220-11e8-b744-525400368cb9");
		String rmoid = relationMOService.addNewRelation4Member2Organize(nrmo);
		//
		RelationMO grmo = relationMOService.getRelationByPrimarykey(rmoid);
		if (null == grmo) {
			System.out.println("the RelationMO generate failed");
		} else {
			System.out.println("the RelationMO of member " + grmo.getMid() + " and organize " + grmo.getOid());
		}
	}

}
