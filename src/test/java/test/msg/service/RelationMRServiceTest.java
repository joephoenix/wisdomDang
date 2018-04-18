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
	@Ignore
	public void testGenerateRelation() {
		RelationMR rmr = new RelationMR();
		List<Pmember> mbs = pmemberService.queryMembersByCondition("Admin");
		assert null != mbs;
		assert 0 < mbs.size();
		rmr.setMid(mbs.get(0).getId());
		Role role = roleService.getRoleByNamesearch("admin");
		assert role != null;
		rmr.setRid(role.getId());
		// creater
		rmr.setCreater(mbs.get(0).getId());
		// generate
		String mrid = relationMRService.generateRelation4MemberRole(rmr);
		System.out.println("create relation successfully and Id is " + mrid);
	}

	@Test
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
