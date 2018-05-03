package test.msg.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.entity.Pmember;
import com.mgs.entity.RelationMR;
import com.mgs.entity.Role;
import com.mgs.service.PmemberService;
import com.mgs.service.RelationMRService;
import com.mgs.service.RoleService;

import test.msg.BaseUnitTest;

public class RelationMRServiceTest extends BaseUnitTest {

	@Autowired
	private RelationMRService relationMRService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PmemberService pmemberService;

	@Test
	public void testGenerateRelation() {
		RelationMR rmr = new RelationMR();
		List<Pmember> mbs = pmemberService.queryMembersByCondition("Admin");
		assert null != mbs;
		assert 0 < mbs.size();
		Role role = roleService.getRoleByNamesearch("shuji");
		assert role != null;
		// dangyuan list
		String[] dyMid = { "244fd581-3b06-11e8-b744-525400368cb9", "7aef4537-3c7e-11e8-b744-525400368cb9",
				"790c05a9-3c81-11e8-b744-525400368cb9", "9b8e88a3-3d6c-11e8-b744-525400368cb9",
				"a8741fa0-3c7d-11e8-b744-525400368cb9" };
		String[] shujiMid = { "07cfaeef-3c7d-11e8-b744-525400368cb9", "433b7866-3c7d-11e8-b744-525400368cb9",
				"a8080f4c-3c7c-11e8-b744-525400368cb9", "831115a1-3c7c-11e8-b744-525400368cb9" };
		for (String mid : shujiMid) {
			rmr.setMid(mid);
			// role
			rmr.setRid(role.getId());
			// creater
			rmr.setCreater(mbs.get(0).getId());
			// generate
			String mrid = relationMRService.generateRelation4MemberRole(rmr);
			System.out.println("create relation successfully and Id is " + mrid);
		}
	}

	@Test
	@Ignore
	public void tesGetRoleFromMember() {
		List<Pmember> mbs = pmemberService.queryMembersByCondition("Admin");
		assert null != mbs;
		assert 0 < mbs.size();
		String memberID = mbs.get(0).getId();
		String memberName = mbs.get(0).getUname();
		List<RelationMR> listRMR = relationMRService.findEntireRoles4Member(memberID);
		assert null != listRMR;
		assert 0 < listRMR.size();
		for (RelationMR rmr : listRMR) {
			String roleName = roleService.getChooseRoleById(rmr.getRid()).getRname();
			System.out.println(" the member that called " + memberName + " is " + roleName + " role.");
		}

	}

}
