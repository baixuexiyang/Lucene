package com.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	/**文件路径*/
	public static final String filePath=getProperties("file.path");
	
	/**索引路径*/
	public static final String indexPath=getProperties("index.path");
	
	/**监听文件变化时间间隔*/
	public static final String scanDiff=getProperties("task.scan.diff");
	
	public static String getProperties(String key) {
		String path = PropertiesUtil.class.getResource("/").getPath();
		InputStream in;
		try {
			in = new BufferedInputStream(new FileInputStream(path
					+ "lucene.properties"));
			Properties p = new Properties();

			p.load(in);
			return p.getProperty(key);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		System.out.print(filePath);
	}

}
