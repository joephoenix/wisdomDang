package test.msg.service;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mgs.entity.Pmember;
import com.mgs.service.PmemberService;

import test.msg.BaseUnitTest;

public class PmemberServiceTest extends BaseUnitTest {

	@Autowired
	private PmemberService pmemberService;

	@Test
	public void testAddNewMember() {
		Pmember npm = new Pmember();
		npm.setUname("Admin");
		npm.setRname("管理员");
		npm.setIsParty(0);
		npm.setPword("123456");
		String mid = pmemberService.addNewMember(npm);
		// get new member
		Pmember extpm = pmemberService.getMemberInformation(mid);
		if (null == extpm) {
			System.out.println("the member generate failed");
		} else {
			System.out.println("the member realname is " + extpm.getRname() + " and username " + extpm.getUname());
		}

	}

	@Test
	public void testGetMembers() {
		List<Pmember> ms = pmemberService.queryMembersByCondition("");
		if (ms.size() > 0) {
			for (Pmember pm : ms) {
				String dang = "dang yuan";
				if (pm.getIsParty() == 0) {
					dang = "qun zhong";
				}
				System.out.println(pm.getRname() + " is " + dang);
			}
		} else {
			System.out.println("there are no data!");
		}
	}
}
