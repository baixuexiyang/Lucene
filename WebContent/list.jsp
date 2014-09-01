<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*"
    import="org.apache.lucene.document.Document"
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>毕业设计</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link href="css/common.css" rel="stylesheet" />
    <link href="css/core.css" rel="stylesheet" />
</head>
<body>
	<div class="effeckt-page-active" data-effeckt-page="page-1">
		<div id="common-header">
			<div class="ttt">
				<p class="logo"><img src="img/apple.png" height="150" /></p>
	            <!-- <p id="nav">
	                &nbsp;&nbsp;<a href="javascript:;" class="current" data-id="1">内&nbsp;容</a>
	                &nbsp;&nbsp;<a href="javascript:;" data-id="2">关键字</a>
	                &nbsp;&nbsp;<a href="javascript:;" data-id="3">摘&nbsp;要</a>
	                &nbsp;&nbsp;<a href="javascript:;" data-id="4">作&nbsp;者</a>
	                &nbsp;&nbsp;<a href="javascript:;" data-id="5">主&nbsp;题</a>
	            </p> -->
        	</div>
			<div id="focus-area">
                <form name="do_search" action="doSearch">
                    <span class="s_ipt_wr">
                    <% String searchWord =  (String)request.getAttribute("searchWord"); %>
                        <input type="text" name="searchWord" value="<% out.println(searchWord); %>" id="kw" maxlength="100" class="s_ipt" autocomplete="off" x-webkit-speech="">
                    </span>
                    <!-- <input type="hidden" name="" id="mark" value="3"> -->
                    <span class="s_btn_wr">
                        <input type="submit" value="搜索" id="search" class="s_btn" onmousedown="this.className='s_btn s_btn_h'" 
                        onmouseout="this.className='s_btn'">
                    </span>
                    <div class="condition">
                        <ul>
                            <li><label for="content">内&nbsp;容</label>
                            <input type="checkbox" name="content" id="content" <% if(null!=request.getAttribute("content")){ %> checked="checked" <%} %>></li>
                            <li><label for="keyword">关键字</label>
                            <input type="checkbox" name="keyword" id="keyword" <% if(null!=request.getAttribute("keyword")){ %> checked="checked" <%} %>></li>
                            <li><label for="summary">摘&nbsp;要</label>
                            <input type="checkbox" name="summary" id="summary" <% if(null!=request.getAttribute("summary")){ %> checked="checked" <%} %>></li>
                            <li><label for="author">作&nbsp;者</label>
                            <input type="checkbox" name="author" id="author" <% if(null!=request.getAttribute("author")){ %> checked="checked" <%} %>></li>
                            <li><label for="subject">主&nbsp;题</label>
                            <input type="checkbox" name="subject" id="subject" <% if(null!=request.getAttribute("subject")){ %> checked="checked" <%} %>></li>
                        </ul>
                    </div>
                </form>
            </div>
		</div>
		<div id="content">
			<div class="container">
				<div class="search-result">
				     <%   
				       Set<Document> docList = (Set<Document>)request.getAttribute("docList");
                       if(docList.size()==0){     				  
				     %>
					<div class="tips">
						对不起，在<span class="search-key"></span>中没搜索到<span class="search-content"><% out.println(searchWord);%></span>，请尝试重新搜索！
					</div>
					<%    }else{ %>
					<div class="result-list">
						<ul>
						 	 <%
							   for (Document doc : docList) {
								   String filename = doc.get("filename");
						     %>
								<li class="clearfix" data-id="1005">
									<div class="main fl">
										<div class="title">
											<a href="getContent?fileName=<% out.println(filename);%>" target="_blank">
											<em><% out.println(filename); %></em></a>
										</div>
										<div class="summary"><%  out.println(doc.get("content").substring(0, 200)+"......"); %></div>
										<div class="info">
											<span class="url"><%  out.println(doc.get("url")); %></span>
											<span class="pub-time"><% 
											String dateStr = doc.get("CreationDate").substring(0, 8);
											dateStr = dateStr.substring(0, 4)+"-"+dateStr.substring(4, 6)+"-"+dateStr.substring(6, 8);
											out.println(dateStr);
											%></span>
										</div>
									</div>
								</li>
						 <% } %>
						</ul>
					</div>
					 <% }%>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
    <script src="js/script.js"></script>
</body>
</html>