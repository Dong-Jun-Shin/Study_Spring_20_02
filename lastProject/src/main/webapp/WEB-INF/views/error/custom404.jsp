<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />      	

		<title>Insert title here</title>
		
		<!-- 모바일 웹 페이지 설정 - 이미지 경로 위치는 각자 변경 -->
		<link rel="shortcut icon" href="/resources/images/icon.png" />
		<link rel="apple-touch-icon" href="/resources/images/icon.png" />
		
		<!-- 부트스트랩 -->
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap-theme.css" />
		
		<!-- 모바일 웹 페이지 설정 끝 -->

		<!--[if lt IE 9] IE9라면 실행>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		
		<script type="text/javascript" src="/resources/include/js/jquery-3.3.1.min.js"></script>
		<!-- 부트스트랩 -->
		<script type="text/javascript" src="/resources/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function(){
				//close 인스턴스 메서드가 호출되는 즉시 실행
				$("errorAlert").on("closed.bs.alert", function(){
					location.href = "/";
				});
				$("#main").click(function(){
					location.href = "/";
				})
			})
		</script>
	</head>
	<body>
		<%-- 이 부분은 개발 당시에는 사용 --%>
<%-- 		<h4><c:out value="${exception.getMessage() }"></c:out></h4> --%>
<!-- 		<ul> -->
<%-- 			<c:forEach  var="stack" items="${exception.getStackTrace() }"> --%>
<%-- 				<li><c:out value="${stack }"></c:out></li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
	
		<%-- 이 부분은 개발 완료 후 사용 --%>
		<div class="alert alert-warning alert-dismissible fade in" role="alert" id="errorAlert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4>잘못된 URL을 요청하셨습니다.</h4>
			<p>
				해당 URL은 존재하지 않습니다. 잠시 후 다시 접근해 주세요. <br />
				감사합니다. <br />
			</p>
			<p>
				<button type="button" class="btn btn-warning" id="main">홈으로</button>
			</p>
		</div>
	</body>
</html>
