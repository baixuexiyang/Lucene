package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.searcher.PdfIndexUtil;
import com.util.PropertiesUtil;

/**
 * 初始化类 创建索引
 * 
 */
public class IndexAction extends ActionSupport {

	private static boolean isFirst = true;

	public String execute() {
		if (isFirst) {
			IndexWriter writer;
			HttpServletRequest request = ServletActionContext.getRequest();
			try {
				writer = new IndexWriter(PropertiesUtil.indexPath,
						new StandardAnalyzer(), true);

				PdfIndexUtil.pdfIndex(writer, PropertiesUtil.filePath);
				// 关闭索引文件流
				writer.close();
			} catch (IOException e) {
				request.setAttribute("error", e.getMessage());
				return ERROR;
			}
			isFirst=false;
		}
		return SUCCESS;
	}
}
