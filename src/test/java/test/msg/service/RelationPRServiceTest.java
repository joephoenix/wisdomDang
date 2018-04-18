package test.msg.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.entity.Pmember;
import com.mgs.entity.Power;
import com.mgs.entity.RelationPR;
import com.mgs.entity.Role;
import com.mgs.service.PmemberService;
import com.mgs.service.PowerService;
import com.mgs.service.RelationPRService;
import com.mgs.service.RoleService;

import test.msg.BaseUnitTest;

public class RelationPRServiceTest extends BaseUnitTest {

	@Autowired
	private RelationPRService relationPRService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PowerService powerService;

	@Autowired
	private PmemberService pmemberService;

	@Test
	@Ignore
	public void testGivePower2Role() {
		RelationPR np2r = new RelationPR();
		Role role = roleService.getRoleByNamesearch("admin");
		assert role != null;
		np2r.setRid(role.getId());
		List<Power> powers = powerService.findPowerListWithName("indexPageVisit");
		assert powers != null;
		assert powers.size() > 0;
		np2r.setPid(powers.get(0).getId());
		List<Pmember> mbs = pmemberService.queryMembersByCondition("Admin");
		assert mbs != null;
		assert mbs.size() > 0;
		np2r.setCreater(mbs.get(0).getId());
		String prid = relationPRService.givePower2Role(np2r);
		assert prid != null;
		System.out.println("give power to role successfully! the id is " + prid);
	}

	@Test
	public void testGetPowerList() {
		Role role = roleService.getRoleByNamesearch("admin");
		assert role != null;
		List<RelationPR> listpr = relationPRService.listPowersFromRole(role.getId());
		assert null != listpr;
		assert 0 < listpr.size();
		System.out.println("the role " + role.getRdesc() + " has had powers");
		for (RelationPR rpr : listpr) {
			Power p = powerService.getPowerInfomation(rpr.getPid());
			assert null != p;
			System.out.println("     " + p.getPname());
		}

	}
}
