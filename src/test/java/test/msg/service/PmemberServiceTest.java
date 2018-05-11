package test.msg.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgs.entity.Pmember;
import com.mgs.service.PmemberService;

import test.msg.BaseUnitTest;

public class PmemberServiceTest extends BaseUnitTest {

	@Autowired
	private PmemberService pmemberService;

	@Test
	@Ignore
	public void testAddNewMember() {
		Pmember npm = new Pmember();
		npm.setUname("Admin");
		npm.setPartyType(9);
		npm.setPword("123456");
		String mid = pmemberService.addNewMember(npm);
		// get new member
		Pmember extpm = pmemberService.getMemberInformation(mid);
		if (null == extpm) {
			System.out.println("the member generate failed");
		} else {
			System.out.println("the member realname is " + " and username " + extpm.getUname());
		}

	}

	@Test
	@Ignore
	public void testGetMembers() {
		List<Pmember> ms = pmemberService.queryMembersByCondition("");
		if (ms.size() > 0) {
			for (Pmember pm : ms) {
				String dang = "dang yuan";
				if (pm.getPartyType() == 0) {
					dang = "qun zhong";
				}
				System.out.println(pm.getUname() + " is " + dang);
			}
		} else {
			System.out.println("there are no data!");
		}
	}

	@Test
	public void showMenus() {

	}
}
