<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />      	

		<title>글상세 보기</title>
		
		<!-- 모바일 웹 페이지 설정 - 이미지 경로 위치는 각자 변경 -->
		<link rel="shortcut icon" href="/resources/images/icon.png" />
		<link rel="apple-touch-icon" href="/resources/images/icon.png" />
		
		<!-- 부트스트랩 -->
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap-theme.css" />
		<link rel="stylesheet" type="text/css" href="/resources/include/css/default.css" />
		
		<!-- 모바일 웹 페이지 설정 끝 -->

		<!--[if lt IE 9] IE9라면 실행>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		
		<script type="text/javascript" src="/resources/include/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="/resources/include/js/common.js"></script>
		<!-- 부트스트랩 -->
		<script type="text/javascript" src="/resources/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<div class="contentContainer container">
			<div class="contentTit page-header"><h3 class="text-center">게시판 상세보기</h3></div>
			
			<div class="contentTB text-center">
				<table class="table table-bordered">
					<colgroup>
						<col width="17%" />
						<col width="33%" />
						<col width="17%" />
						<col width="33%" />
					</colgroup>
					<tbody>
						<tr>
							<td>작성자</td>
							<td class="text-left">${detail.b_name }</td>
							<td>작성일</td>
							<td class="text-left">${detail.b_date }</td>
						</tr>
						<tr>
							<td>제 목</td>
							<td colspan="3" class="text-left">${detail.b_title }</td>
						</tr>
						<tr class="table-height">
							<td>내 용</td>
							<td colspan="3" class="text-left">${detail.b_content }</td>
						</tr>
					</tbody>
				</table>
			</div>
<!-- 			<div style="width: 90%; margin: 0px auto;"> -->
<%-- 				<c:import url="/WEB-INF/board_comment/getCommentList.jsp" /> --%>
<!-- 			</div> -->
		</div>
	</body>
</html>
