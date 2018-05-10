package test.msg.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgs.entity.ResourceFile;
import com.mgs.fastdfs.FastDFSFile;
import com.mgs.service.ResourceFileService;

import test.msg.BaseUnitTest;

public class ResourceFileServiceTest extends BaseUnitTest {

	@Autowired
	private ResourceFileService sfileService;

	/***
	 * test save file to fastdfs server
	 * when need to annotata the varie "classPath" in the class FileManager.
	 */
	@Test
	public void testSavefileAction() {
		File fin;
		String fileName = "0304";
		String fileExt = "jpg";
		String fileFolder = "E:\\MyProjects\\";
		String sid = null;
		BufferedInputStream in = null;
		try {
			String filepath = fileFolder + fileName + "." + fileExt;
			fin = new File(filepath);
			if (!fin.exists()) {
				System.out.println("File Not Found with " + filepath);
			}
			long flen = fin.length();
			ByteArrayOutputStream bos = new ByteArrayOutputStream((int) flen);
			in = new BufferedInputStream(new FileInputStream(fin));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			FastDFSFile fdfs = new FastDFSFile();
			fdfs.setName(fileName);
			fdfs.setExt(fileExt);
			fdfs.setAuthor("admin");
			fdfs.setContent(bos.toByteArray());
			fdfs.setLength(String.valueOf(flen));
			sid = sfileService.uploadChooseFileToFastdfs(fdfs);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null == sid) {
			System.out.println("file save id is null, so save failed");
		} else {
			ResourceFile rlt = sfileService.getSaveFileInformation(sid);
			if (null == rlt) {
				System.out.println("can't find the choose file");
			} else {
				System.out.println("----------Save a new file that name is " + rlt.getFname() + " and address  is  "
						+ rlt.getFpath() + "------------");
			}
		}

	}

}
