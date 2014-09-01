package com.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.struts2.ServletActionContext;

import com.searcher.PdfIndexUtil;

/**
 * 监听文件目录的变化
 * 
 */
public class FileMonitorTask implements Runnable {
	/**
	 * 监控的文件路径
	 */
	private String dir;
	/**
	 * 原有文件信息
	 */
	private Map<String, String> oldDirFileMap;
	/**
	 * 扫描间隔时间以秒为单位
	 */
	private int period;

	/**
	 * 初始化相关数据
	 */
	public FileMonitorTask(String dir, int period) {
		this.dir = dir;
		this.period = period;
		this.oldDirFileMap = new HashMap<String, String>();
	}


	/** * 得到增加的文件及文件夹,并增加到已有的文件信息中 */
	private void getAddedFile(Map<String, String> nowDirFileMap) {
		boolean isModify=false;
		for (Iterator<String> iterator = nowDirFileMap.keySet().iterator(); iterator
				.hasNext();) {
			String key = iterator.next();
			if (null == oldDirFileMap.get(key)) {
				oldDirFileMap.put(key, nowDirFileMap.get(key));
				System.out.println("新增:" + nowDirFileMap.get(key) + "---" + key);
				isModify=true;
			
			}
		}
		if(isModify) updateIndex();
		
	}

	/** * 得到删除的文件及文件夹,并删除已经不存在的文件信息 */
	private void getDeletedFile(Map<String, String> nowDirFileMap) {
		boolean isModify=false;
		for (Iterator<String> iterator = oldDirFileMap.keySet().iterator(); iterator
				.hasNext();) {
			String key = iterator.next();
			if (null == nowDirFileMap.get(key)) {
				System.out.println("删除:" + oldDirFileMap.get(key));
				iterator.remove();
				oldDirFileMap.remove(key);
				isModify=true;
			}
		}
		if(isModify) updateIndex();
	}

	/**
	 * * 递归扫描整个路径 * * @param dir *
	 * 
	 * @param ndir
	 *            *
	 * @param dirFileMap
	 *            * 存放扫描结果
	 * */
	private void totalScan(File file, Map<String, String> dirFileMap) {
		String[] fileList = file.list(); // 判断是否为空目录
		if (null != fileList) {
			for (int i = 0; i < fileList.length; i++) {
				String pname = file.getAbsolutePath() + "\\" + fileList[i];
				File tempFile = new File(pname);
				if (tempFile.isDirectory()) {
					dirFileMap.put(pname, pname);
					totalScan(tempFile, dirFileMap);
				} else {
					// 不相同的文件夹下，存放的文件可能名字相同，但是同一路径下的文件肯定不会相同，
					// 所以采用全路径做为key值
					dirFileMap.put(pname, pname);
				}
			}
		}
	}
	
	/**
	 * 更新索引
	 */
	private void updateIndex(){
		IndexWriter writer;
		try {
			writer = new IndexWriter(PropertiesUtil.indexPath,new StandardAnalyzer(), true);
	
		PdfIndexUtil.pdfIndex(writer,PropertiesUtil.filePath);
        // 关闭索引文件流
        writer.close();
		} catch (IOException e) {
			System.out.println("更新索引异常."+e.getMessage());
		}
	}

	/**
	 * * 线程的执行。对于修改文件的情况，则视为删除与增加两个操作。
	 */
	public void run() {
		// TODO Auto-generated method stub
		boolean isError = false;
		File file = new File(dir); // 初始化开始监控时的文件路径状态
		totalScan(file, oldDirFileMap); // 展示原有路径下的文件信息
		while (!isError) {
			try {
				Thread.sleep(period * 1000); // 指定时间间间隔后扫描路径
				Map<String, String> nowDirFileMap = new HashMap<String, String>();
				totalScan(file, nowDirFileMap); // 得到删除的文件及文件夹
				getDeletedFile(nowDirFileMap); // 得到新增的文件及文件夹
				getAddedFile(nowDirFileMap); // 注意：由于涉及到修改，所以一定要先检测删除的文件，然后再检测新增加的文件
			} catch (InterruptedException e) {
				System.out.println("对指定的文件路径进行监控时产生异常，异常信息为：" + e.getMessage());
				isError = true;
			}
		}
	}

	public static void main(String[] args) {
		FileMonitorTask dirMonitor = new FileMonitorTask(PropertiesUtil.filePath, Integer.valueOf(PropertiesUtil.scanDiff));
		dirMonitor.run();
	}
}