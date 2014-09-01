<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="org.apache.lucene.document.Document"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>毕业设计</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link href="css/common.css" rel="stylesheet" />
    <link href="css/core.css" rel="stylesheet" />
</head>
<body>
	<div class="effeckt-page-active" data-effeckt-page="page-1">
		<%   
	     Document doc = (Document)request.getAttribute("doc"); 
		 if(null!=doc){
			 String fileName =  doc.get("filename").substring(0,doc.get("filename").indexOf("."));
			 %>
				<div id="common-header">
					<div class="ttt">
						<p class="logo" style="text-align: center;"><img src="img/apple.png" height="150" /></p>
		        	</div>
					<div id="focus-area" style="text-align: center;font-family: 微软雅黑;font-size: 20px;">
		                <% out.println(fileName); %>
		            </div>
				</div>
				<div id="content">
					<div class="container">
						<div class="search-result"><% out.println(doc.get("content")); %></div>
					</div>
				</div>
		<%}else{%>
				<div class="tips" style="width:800px;margin:200px auto;">对不起， 无效的文件名，请尝试重新搜索！</div>	 
		<%} %>
	</div>
	<script src="js/jquery.min.js"></script>
    <script src="js/script.js"></script>
</body>
</html>