package test.msg.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.entity.FileReadLog;
import com.mgs.entity.Pmember;
import com.mgs.entity.ResourceFile;
import com.mgs.service.FileReadLogService;
import com.mgs.service.PmemberService;
import com.mgs.service.ResourceFileService;
import test.msg.BaseUnitTest;

public class FileReadLogServiceTest extends BaseUnitTest {

	@Autowired
	private FileReadLogService fileReadLogService;

	@Autowired
	private ResourceFileService sfileService;

	@Autowired
	private PmemberService pmemberService;

	@Test
	@Ignore
	public void testFileReadMethod() {
		// find the file list
		List<ResourceFile> filelist = sfileService.querySavefileList();
		boolean iscontinue = false;
		List<String> frlids = new ArrayList<String>();
		if (null == filelist) {
			System.out.println("There are no files!");
		} else {
			for (ResourceFile sf : filelist) {
				String fid = sf.getId();
				List<Pmember> memberlist = pmemberService.queryMembersByCondition("");
				if (null == filelist) {
					System.out.println("There are no members!");
				} else {
					for (Pmember pm : memberlist) {
						String mid = pm.getId();
						FileReadLog frl = new FileReadLog();
						frl.setFid(fid);
						frl.setMid(mid);
						String logid = fileReadLogService.sendSaveFileToMember(frl);
						frlids.add(logid);
					}
					iscontinue = true;
				}
			}
		}
		if (iscontinue) {
			if (0 == frlids.size()) {
				System.out.println("send file to member failed");
			} else {
				for (String id : frlids) {
					FileReadLog dt = fileReadLogService.getReadlogDetail(id);
					if (null == dt) {
						System.out.println("there is no object");
					} else {
						String fname = sfileService.getSaveFileInformation(dt.getFid()).getFname();
						String mname = pmemberService.getMemberInformation(dt.getMid()).getUname();
						System.out.println("the log detail is file that named " + fname + " send to member " + mname);
					}
				}
			}
		}
	}

	@Test
	public void testFindChooseFileLogs() {
		List<FileReadLog> listfs = fileReadLogService.findChooseFileLogs("5661f12a-414b-11e8-b744-525400368cb9");
		if (null == listfs) {
			System.out.println("there is no object");
		} else {
			for (FileReadLog frl : listfs) {
				String fname = sfileService.getSaveFileInformation(frl.getFid()).getFname();
				String mname = pmemberService.getMemberInformation(frl.getMid()).getUname();
				Integer s = frl.getState();
				System.out.println(
						"the log detail is file that named " + fname + " send to member " + mname + " state is " + s);
			}
		}
	}
}
