package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.document.Document;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.searcher.SearcherUtil;
import com.util.PropertiesUtil;

public class ContentAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String fileName;
	
	/**
	 * 根据文件名查询文件详细内容
	 */
	public String execute()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
	
		String indexDir = PropertiesUtil.indexPath;
		List<Document> docList = new ArrayList<Document>();
		fileName= CommonUtil.escapeHTMLTag(fileName);//过滤html字符，防止注入
		if(null!=fileName&&!fileName.equals("")){
		   docList = SearcherUtil.searcher(indexDir, fileName,"filename");
		}
		if(docList.size()>0){
			request.setAttribute("doc", docList.get(0));
		}
		}catch(Exception ex){
			request.setAttribute("error", ex.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
