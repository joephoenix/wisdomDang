package test.msg.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.dao.MessageRecordDAO;
import com.mgs.entity.MessageRecord;

import test.msg.BaseUnitTest;

public class MessageRecordDAOTest extends BaseUnitTest {

	@Autowired
	private MessageRecordDAO messageRecordDAO;

	@Test
	public void testGenerateMessageRecord() {
		MessageRecord mr = new MessageRecord();
		mr.setMid("07cfaeef-3c7d-11e8-b744-525400368cb9");
		mr.setRsid("43981c41-54f0-11e8-b744-525400368cb9");
		int rlt = messageRecordDAO.generateMessageRecord(mr);
		if (rlt > 0) {
			System.out.println("successfully");
		} else {
			System.out.println("failed");
		}
	}

}
