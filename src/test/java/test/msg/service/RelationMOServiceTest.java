package test.msg.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.entity.Porganize;
import com.mgs.entity.RelationMO;
import com.mgs.service.PorganizeService;
import com.mgs.service.RelationMOService;

import test.msg.BaseUnitTest;

public class RelationMOServiceTest extends BaseUnitTest {

	@Autowired
	private RelationMOService relationMOService;

	@Autowired
	private PorganizeService porganizeService;

	@Test
	public void testRelationMethod() {
		RelationMO nrmo = new RelationMO();
		nrmo.setMid("da482273-4220-11e8-b744-525400368cb9");
		nrmo.setOid("f3bf5b49-3f2c-11e8-b744-525400368cb9");
		nrmo.setCreater("da482273-4220-11e8-b744-525400368cb9");
		String rmoid = relationMOService.addNewRelation4Member2Organize(nrmo);
		//
		RelationMO grmo = relationMOService.getRelationByPrimarykey(rmoid);
		if (null == grmo) {
			System.out.println("the RelationMO generate failed");
		} else {
			System.out.println("the RelationMO of member " + grmo.getMid() + " and organize " + grmo.getOid());
		}
		//
		List<RelationMO> lsrmo = relationMOService.queryReltionsByOrganize("f3bf5b49-3f2c-11e8-b744-525400368cb9");
		Porganize org = porganizeService.getOrganizeInfo("f3bf5b49-3f2c-11e8-b744-525400368cb9");
		System.out.println("the organize " + org.getOname() + " have " + lsrmo.size() + " members");
	}

}
