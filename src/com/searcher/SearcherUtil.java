package com.searcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import com.util.PropertiesUtil;

public class SearcherUtil {
	
	/**
	 * 根据搜索关键字，索引目录，搜索类型来搜索相关内容
	 * @param indexDir 索引目录
	 * @param searchWord 搜索关键字
	 * @param type 搜索类型
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 * @throws ParseException
	 */
	public static List<Document> searcher(final String indexDir,final String searchWord, final String type) 
			throws IOException,ParseException, ParseException {
		IndexSearcher is = new IndexSearcher(indexDir);
		QueryParser parser = new QueryParser(type, new StandardAnalyzer());
		Query query = parser.parse(searchWord);
		Hits hits = is.search(query);
		List<Document> docList = new ArrayList<Document>();
		for (int i = 0; i < hits.length(); i++) {
			Document doc = hits.doc(i);
			docList.add(doc);
		}
		is.close();
		return docList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IllegalArgumentException,
			IOException, ParseException{
		String indexDir =PropertiesUtil.indexPath;
		String q = "设计模式";
		//摘要:summary,作者:Author,关键字:Keywords,主题:Subject,内容:content
		String type="content";
		searcherOld(indexDir, q,type);
	}

	public static List<Document> searcherOld(final String indexDir, final String q,final String type) throws IOException,
			ParseException, ParseException {
		 IndexSearcher is = new IndexSearcher(indexDir);
		 QueryParser parser = new QueryParser(type,new StandardAnalyzer());
		 Query query = parser.parse(q);
		 long start = System.currentTimeMillis();
		 Hits hits = is.search(query);
		 long end = System.currentTimeMillis();
		 System.out.println("Found " + hits.length() + " document(s) (in " 
		 + (end - start) + " milliseconds) that matched query '" +
		 q + "':");
		 List<Document> docList = new ArrayList<Document>();
		 for(int i=0;i<hits.length();i++){
			 Document doc=hits.doc(i);
			 docList.add(doc);
			 System.out.println("文件："+doc.get("url"));
//			 System.out.println(doc.get("content"));
			 System.out.println(doc.get("Author"));
			 System.out.println(doc.get("Keywords"));
		 }

		 is.close();
		 return docList;
	}
}