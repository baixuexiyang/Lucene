package com.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.apache.lucene.document.Document;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.searcher.SearcherUtil;
import com.sun.org.apache.regexp.internal.recompile;
import com.util.PropertiesUtil;

public class SearchAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String searchWord;
	private String content;
	private String keyword;
	private String summary;
	private String author;
	private String subject;
	
	/**
	 * 根据输入的查询内容查询文件列表
	 */
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
		String indexDir = PropertiesUtil.indexPath;
		Set<Document> docSet = new HashSet<Document>();
		searchWord= CommonUtil.escapeHTMLTag(searchWord);//过滤html字符，防止注入
		if(null!=searchWord&&!searchWord.equals("")){
			List<String> typeList = getTypeList(request);
			for(String type : typeList){
				List<Document> docList = new ArrayList<Document>();
				docList = SearcherUtil.searcher(indexDir, searchWord,type);
				for(Document doc : docList){
					docSet.add(doc);//使用set存放结果集，达到合并去重的效果
				}
			}
		}
		request.setAttribute("docList", docSet);
		request.setAttribute("searchWord",URLDecoder.decode(searchWord,"utf-8"));
		
	}catch(Exception ex){
		request.setAttribute("error", ex.getMessage());
		return ERROR;
	}
		return SUCCESS;
	}
	
	/**
	 * 根据页面选择的查询类型 返回对应的索引的查询类型
	 * @return
	 */
	private List<String> getTypeList(HttpServletRequest request){
		//摘要:summary,作者:Author,关键字:Keywords,主题:Subject,内容:content
		List<String> typeList = new ArrayList<String>();
		if(null!= content&&content.equals("on")){
			typeList.add("content");
			request.setAttribute("content", "on");
		}
		if(null!=keyword&&keyword.equals("on")){
			typeList.add("keyword");
			request.setAttribute("keyword", "on");
		}
		if(null!=summary&&summary.equals("on")){
			typeList.add("summary");
			request.setAttribute("summary", "on");
		}
		if(null!=author&&author.equals("on")){
			typeList.add("Author");
			request.setAttribute("Author", "on");
		}
		if(null!=subject&&subject.equals("on")){
			typeList.add("Subject");
			request.setAttribute("Subject", "on");
		}
		return typeList;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
