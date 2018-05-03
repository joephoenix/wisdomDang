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
	public void testDangyuanPower4() {
		String[] dyPid = { "863c0602-4de4-11e8-b744-525400368cb9", "fd52d09f-4de4-11e8-b744-525400368cb9",
				"c6d4f601-4de5-11e8-b744-525400368cb9", "df48442d-4de5-11e8-b744-525400368cb9",
				"fa4ea844-4de5-11e8-b744-525400368cb9" };
		RelationPR np2r = new RelationPR();
		Role role = roleService.getRoleByNamesearch("dangyuan");
		assert role != null;
		List<Pmember> mbs = pmemberService.queryMembersByCondition("Admin");
		assert mbs != null;
		assert mbs.size() > 0;
		for(String pid : dyPid) {
			np2r.setRid(role.getId());
			np2r.setPid(pid);
			np2r.setCreater(mbs.get(0).getId());
			String prid = relationPRService.givePower2Role(np2r);
			assert prid != null;
			System.out.println("give power to role successfully! the id is " + prid);
		}
	}

	/**
	 * 测试方法，把角色赋给用户
	 */
	@Test
	@Ignore
	public void testGivePower2Role() {
		RelationPR np2r = new RelationPR();
		Role role = roleService.getRoleByNamesearch("shuji");
		assert role != null;
		List<Power> powers = powerService.queryEntirePowers();
		assert powers != null;
		assert powers.size() > 0;
		List<Pmember> mbs = pmemberService.queryMembersByCondition("Admin");
		assert mbs != null;
		assert mbs.size() > 0;
		for (Power pw : powers) {
			np2r.setRid(role.getId());
			np2r.setPid(pw.getId());
			np2r.setCreater(mbs.get(0).getId());
			String prid = relationPRService.givePower2Role(np2r);
			assert prid != null;
			System.out.println("give power to role successfully! the id is " + prid);
		}

	}

	@Test
	@Ignore
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
