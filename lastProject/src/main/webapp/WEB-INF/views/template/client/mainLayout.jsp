<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- 모바일 웹 페이지 설정 - 이미지 경로 위치는 각자 변경 -->
	<link rel="shortcut icon" href="/resources/images/icon.png" />
	<link rel="apple-touch-icon" href="/resources/images/icon.png" />

    <title><tiles:getAsString name="title" /></title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap-theme.css" />
		
    <!-- Custom styles for this template -->
    <link href="/resources/include/css/sticky-footer-navbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/resources/include/dist/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
    	<tiles:insertAttribute name="header" />
    </nav>

    <!-- Begin page content -->
    <div class="container">
      <div class="page-header">
        <div class="jumbotron">
		  <h1>(☞ﾟヮﾟ)☞ (☞ﾟヮﾟ)☞</h1>
          <p>혼자 왔어요! ☜(ﾟヮﾟ☜)</p>   
          <p>둘이 왔어요! ☜(ﾟヮﾟ☜) ☜(ﾟヮﾟ☜)</p>
          <p>셋이 왔어요! ☜(ﾟヮﾟ☜) ☜(ﾟヮﾟ☜) ☜(ﾟヮﾟ☜)</p>
		</div>
      </div>
      <div class="row">
		<div class="col-sm-6 col-md-4">
	      <h2>게시판 관리</h2>
	      <p>입력/수정/삭제 및 댓글 작성까지 처리 완료.</p>
	      <p><a href="/board/boardList" class="btn btn-default" role="button">View details &raquo;</a></p>
	    </div>
	    <div class="col-sm-6 col-md-4">
	      <h2>갤러리 게시판 관리</h2>
	      <p>썸네일의 입력/수정/삭제 처리 완료. lightbox 처리</p>
	      <p><a href="/gallery/galleryList" class="btn btn-default" role="button">View details &raquo;</a></p>
	    </div>
	    <div class="col-sm-6 col-md-4">
	      <h2>로그인 및 회원 관리</h2>
	      <p>...</p>
	      <p><a href="/member" class="btn btn-default" role="button">View details &raquo;</a></p>
	    </div>
	    <div class="col-sm-6 col-md-4">
	      <h2>게시판 관리(다중 첨부파일)</h2>
	      <p>...</p>
	      <p><a href="/multiple" class="btn btn-default" role="button">View details &raquo;</a></p>
	    </div>
	  </div>
    </div>

    <footer class="footer">
    	<tiles:insertAttribute name="footer" />
    </footer>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
    <script src="/resources/include/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/resources/include/dist/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
