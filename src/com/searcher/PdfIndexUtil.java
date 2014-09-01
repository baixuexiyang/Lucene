package com.searcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.pdfbox.searchengine.lucene.LucenePDFDocument;

import com.util.PropertiesUtil;

public class PdfIndexUtil {

	public static String getText(String file) {
		String s = "";
		try {
			File fil = new File(file);
			if (fil.isFile() && fil.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(fil));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTXT = null;
				while ((lineTXT = bufferedReader.readLine()) != null) {
					// System.out.println(lineTXT.toString().trim());
					s += lineTXT.toString().trim();
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件！");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容操作出错");
			e.printStackTrace();
		}
		return s.substring(0, 200);
	}

	public static void pdfIndex(IndexWriter writer, String pdfDir)
			throws IOException {

		File file = new File(pdfDir);
		// 取得所有需要转换的文件数组

		if(file.isFile()){//文件
			System.out.println(file.getName());
			String fileName = file.getName();
			// 判断文件是否为pdf类型的文件
			if (fileName.substring(fileName.lastIndexOf("."))
					.equals(".pdf")) {

				// String path = pdfDir + fileName;
				Document luceneDocument = LucenePDFDocument
						.getDocument(file);
				PDDocument pdfdoc = PDDocument.load(file);
				PDFTextStripper stripper = new PDFTextStripper("GBK");
				String content = stripper.getText(pdfdoc).toString();
				// System.out.println(content);
				Field contents = luceneDocument.getField("Author");// 摘要:summary,作者:Author,关键字:Keywords,主题:Subject,内容:
				System.out.println(contents.stringValue());
				// luceneDocument.add(new Field("content", new
				// FileReader(file)));
				luceneDocument.add(new Field("content", content,
						Field.Store.YES, Field.Index.TOKENIZED));
				luceneDocument.add(new Field("filename", fileName,
						Field.Store.YES, Field.Index.TOKENIZED));
				// 写入索引
				writer.addDocument(luceneDocument);
				pdfdoc.close();

			}
		
		}else{//文件夹
			for (String path : file.list()) {
				pdfIndex(writer, pdfDir+"\\"+path);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		IndexWriter writer = new IndexWriter(PropertiesUtil.indexPath,
				new StandardAnalyzer(), true);
		pdfIndex(writer, PropertiesUtil.filePath);
		// 关闭索引文件流
		writer.close();
	}
}
