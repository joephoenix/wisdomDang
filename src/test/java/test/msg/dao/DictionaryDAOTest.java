package test.msg.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.dao.DictionaryDAO;
import com.mgs.entity.Dictionary;

import test.msg.BaseUnitTest;

public class DictionaryDAOTest extends BaseUnitTest {

	@Autowired
	private DictionaryDAO dictionaryDAO;

	@Test
	@Ignore
	public void testCreateDictionary() {
		Dictionary nd = new Dictionary();
		nd.setDictname("党内会议");
		nd.setDictvalue("3");
		nd.setDictkey("function");
		nd.setCreater("da482273-4220-11e8-b744-525400368cb9");
		int rlt = dictionaryDAO.generateNewDictionary(nd);
		if (rlt == 0) {
			System.out.println("init dictionary failed!");
		} else {
			System.out.println("init dictionary successfully!");
		}
	}

	@Test
	public void testQueryDictionary() {
		List<Dictionary> lstDict = new ArrayList<Dictionary>();
		String dictKey = "taskPoint";
		lstDict = dictionaryDAO.queryDictListBykey(dictKey);
		if (null == lstDict || 0 == lstDict.size()) {
			System.out.println("query dictionary failed");
		} else {
			for (Dictionary dict : lstDict) {
				System.out.println("the Dictionary " + dictKey + " had option " + dict.getDictname()
						+ " and the value is " + dict.getDictvalue());
			}
		}
	}
}
