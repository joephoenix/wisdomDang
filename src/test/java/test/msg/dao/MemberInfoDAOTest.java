package test.msg.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.dao.MemberInfoDAO;
import com.mgs.entity.MemberInfo;

import test.msg.BaseUnitTest;

public class MemberInfoDAOTest  extends BaseUnitTest{
	
	@Autowired
	private MemberInfoDAO memberInfoDAO;
	
	@Test
	public void testGenerateMemberInfo() {
		MemberInfo nmi = new MemberInfo();
		String realname = "超级管理员";
		nmi.setMid("da482273-4220-11e8-b744-525400368cb9");
		nmi.setName(realname);
		nmi.setCardno("不设置");
		nmi.setSex(1);
		nmi.setAge(30);
		nmi.setCreater("da482273-4220-11e8-b744-525400368cb9");
		nmi.setEducation("4");
		nmi.setPosition("管理员");
		nmi.setUnit("德清党群管理中心");
		nmi.setUniversity("没有大学");		
		int rlt = memberInfoDAO.generateMemberInformation(nmi);
		if(rlt > 0) {
			System.out.println("generate information for Member " + realname + " successfully.");
		}else {
			System.out.println("generate information for member failed.");
		}
	}

}
