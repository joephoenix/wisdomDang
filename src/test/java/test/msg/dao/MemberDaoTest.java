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
		pm.setUname("kelaien");
		pm.setPword("123456");
		pm.setPartyType(3);    //如果输入3表示是党员，则需要输入党龄，不是党员，则党龄为0
		pm.setPartyAge(9);
		pm.setPionts(0);   //分数初始化为0
		int rls = pmemberDAO.addNewMember(pm);
		System.out.println("add " + rls + " record successfully!");
	}

}
