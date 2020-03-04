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

		<title>Insert title here</title>
		
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
		

<!-- 		<script type="text/javascript" src="/resources/include/js/common.js"></script> -->
		<script type="text/javascript" src="/resources/include/js/jquery-3.3.1.min.js"></script>
		<!-- 부트스트랩 -->
		<script type="text/javascript" src="/resources/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function(){
				/* 글쓰기 버튼 클릭 시, 처리 이벤트 */
				$("#insertFormBtn").click(function(){
					location.href = "/board/writeForm";
				})
				
				/* 제목 클릭 시, 상세 페이지 이동을 위한 처리 이벤트 */
				$(".goDetail").click(function(){
					var b_num = $(this).parents("tr").attr("data-num");
					$("#b_num").val(b_num);
// 					console.log("글번호 : " + b_num);
					
					$("#detailForm").attr({
						"method" : "get",
						"action" : "/board/boardDetail"
					});
					
					$("#detailForm").submit();
				})
			})
		</script>
	</head>
	<body>
		<div class="container">
			<div class="page-header"><h3 class="text-center">게시판 리스트</h3></div>
			
			<form id="detailForm">
				<input type="hidden" id="b_num" name="b_num" />
			</form>
			
			<%-- 검색기능 시작 --%>
<!-- 			<div id="boardSearch" class="text-right"></div> -->
			
			
			<%-- 리스트 시작 --%>
			<div id="boardList">
				<table summary="게시판 리스트" class="table table-striped table-hover">
					<colgroup>
						<col width="10%" />
						<col width="60%" />
						<col width="15%" />
						<col width="13%" />
					</colgroup>
					<thead>
						<tr>
							<!-- 정렬을 위한 구분값 data-value, order(css class) -->
							<th data-value="b_num" class="order text-center">글번호</th>
							<th class="text-center">글제목</th>
							<th data-value="b_date" class="order">작성일</th>
							<th class="text-center">작성자</th>
						</tr>
					</thead>
					<tbody id="list" class="table-striped" >
						<!-- 데이터 출력 -->
						<c:choose>
							<%-- 데이터 존재 시 --%>
							<c:when test="${not empty boardList }">
								<c:forEach var="bvo" items="${boardList }" varStatus="status">
									<tr class="text-center" data-num="${bvo.b_num}">
										<td>${bvo.b_num }</td>
										<td class="goDetail text-left">${bvo.b_title }</td>
										<td class="text-left">${bvo.b_date }</td>
										<td class="name">${bvo.b_name }</td>
										<%-- 										
										<td class="tal">
										<c:if test="${bvo.repstep > 0 }">
											<c:forEach begin="1" end="${bvo.repindent }">
												&nbsp;&nbsp;&nbsp;&nbsp;
											</c:forEach>
											<img src="/siteProject/image/reply.png"/>
										</c:if>
										<span class="goDetail">${bvo.title }</span>
										<c:if test="${bvo.cCount > 0 }">
											<span style="color: orange; font-size: 12px;">
												 [${bvo.cCount }]
											</span>
										</c:if>
											</td> 
	 									--%>
									</tr>
								</c:forEach>
							</c:when>
							<%-- 데이터 존재 안할 시 --%>
							<c:otherwise>
								<tr>
									<td colspan="4" class="tac text-center">등록된 게시물이 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				
			</div>
			<%-- 리스트 종료 --%>
			
			<%-- 글쓰기 버튼 출력 시작 --%>
			<div class="text-right">
				<button type="button" id="insertFormBtn" class="btn btn-success">글쓰기</button>
			</div>
			<%-- 글쓰기 버튼 출력 종료 --%>
		</div>
	</body>
</html>
