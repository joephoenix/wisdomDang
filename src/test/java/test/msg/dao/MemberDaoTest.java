package test.msg.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgs.dao.PmemberDAO;
import com.mgs.entity.Pmember;

import test.msg.BaseUnitTest;

public class MemberDaoTest extends BaseUnitTest {

	@Autowired
	private PmemberDAO pmemberDAO;

	@Test
	public void addNewMember() {
		Pmember pm = new Pmember();
		pm.setUname("mazhangwei");
		pm.setRname("马张威");
		pm.setPword("123456");
		pm.setIsParty(0);
		int rls = pmemberDAO.addNewMember(pm);
		System.out.println("add " + rls + " record successfully!");
	}

}
