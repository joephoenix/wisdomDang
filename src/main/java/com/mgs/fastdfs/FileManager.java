package com.mgs.fastdfs;

import java.io.File;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

public class FileManager implements FileManagerConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7702530858823626504L;

	private static TrackerClient trackerClient;

	private static TrackerServer trackerServer;

	private static StorageServer storageServer;

	private static StorageClient storageClient;

	static {
		try {
			// test environment
			// String classPath = "E:\\MyProjects\\wisdomDang\\target\\classes";
			// run environment
			String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
			// run environment
			String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
			ClientGlobal.init(fdfsClientConfigFilePath);
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, storageServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String upload(byte[] context, String ext, NameValuePair[] valuePairs) throws Exception {

		String[] uploadResults = null;
		uploadResults = storageClient.upload_file(context, ext, valuePairs);
		String groupName = uploadResults[0];
		String remoteFileName = uploadResults[1];
		String fileAbsolutePath = PROTOCOL + TRACKER_NGINX_ADDR + ":" + TRACKER_NGINX_PORT + SEPARATOR + groupName
				+ SEPARATOR + remoteFileName;
		return fileAbsolutePath;
	}

}
