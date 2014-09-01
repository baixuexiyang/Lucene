<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/common.css" rel="stylesheet" />
    <link href="css/main.css" rel="stylesheet" />
    <style>
    </style>
<title>毕业设计</title>
</head>
<body>
    <div class="wrapper">
        <div class="content">
            <p class="logo"><img src="img/ap2.png" height="150" /></p>
            <!-- <p id="nav">
                &nbsp;&nbsp;<a href="javascript:;" class="current" data-id="1">内&nbsp;容</a>
                &nbsp;&nbsp;<a href="javascript:;" data-id="2">关键字</a>
                &nbsp;&nbsp;<a href="javascript:;" data-id="3">摘&nbsp;要</a>
                &nbsp;&nbsp;<a href="javascript:;" data-id="4">作&nbsp;者</a>
                &nbsp;&nbsp;<a href="javascript:;" data-id="5">主&nbsp;题</a>
            </p> -->
            <div id="focus-area">
                <form name="do_search" action="doSearch">
                    <span class="s_ipt_wr">
                        <input type="text" name="searchWord" id="kw" maxlength="100" class="s_ipt" />
                    </span>
                    <!-- <input type="hidden" name="" id="mark" value="1"> -->
                    <span class="s_btn_wr">
                        <input type="submit" value="搜索" id="search" class="s_btn" onmousedown="this.className='s_btn s_btn_h'" 
                        onmouseout="this.className='s_btn'">
                    </span>
                    <div class="condition">
                        <ul>
                            <li><label for="content">内&nbsp;容</label><input type="checkbox" name="content" id="content" checked="checked"></li>
                            <li><label for="keyword">关键字</label><input type="checkbox" name="keyword" id="keyword"></li>
                            <li><label for="summary">摘&nbsp;要</label><input type="checkbox" name="summary" id="summary"></li>
                            <li><label for="author">作&nbsp;者</label><input type="checkbox" name="author" id="author"></li>
                            <li><label for="subject">主&nbsp;题</label><input type="checkbox" name="subject" id="subject"></li>
                        </ul>
                    </div>
                </form>
            </div>      
        </div>
        <div class="footer">
            <p class="info">Copyright ©2014 毕业设计</p>
        </div>
    </div>
	<script src="js/jquery.min.js"></script>
    <script src="js/script.js"></script>
 </body>
</html>
