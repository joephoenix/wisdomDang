package com.mgs.fastdfs;

import java.io.Serializable;

public interface FileManagerConfig extends Serializable {
	
	public static final String FILE_DEFAULT_AUTHOR = "joephoenix";
	
	public static final String PROTOCOL = "http://";
	
	public static final String SEPARATOR = "/";
	
	public static final String TRACKER_NGINX_ADDR = "123.206.128.207";
	
	public static final String TRACKER_NGINX_PORT = "8100";
	
	public static final String CLIENT_CONFIG_FILE = "fdfs_client.conf";
}
