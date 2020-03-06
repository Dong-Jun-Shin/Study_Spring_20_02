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
		<script type="text/javascript" src="/resources/include/js/common.js"></script>
		<!-- 부트스트랩 -->
		<script type="text/javascript" src="/resources/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function(){
				/* 검색 후 검색 대상과 검색 단어 출력 */
				var word = "<c:out value='${data.keyword}' />";
				var value="";
				if(word != ""){
					$("#search").val("<c:out value = '${data.search}' />");
					$("#keyword").val("<c:out value = '${data.keyword}' />");
					
					if($("#search").val() != 'b_content'){
						if($("#search").val() == 'b_title') 
							value = "#list tr td.goDetail";
						else if($("#search").val() == 'b_name')
							value = "#list tr td.name";
						//$("#list tr td.name:contains('준')").html() = 준이 포함된 결과물
						console.log($(value + ":contains('" + word + "')").html());
						
						// :contains()는 특정 텍스트를 포함한 요소반환)
						// $("#list tr td.name:contains('이름')");
						$(value + ":contains('" + word + "')").each(function(){
							// g 글로벌, i 대소문자 상관x
							var regex = new RegExp(word, 'gi');
							// default.css에 정의된 클래스를 추가
							$(this).html($(this).html().replace(regex, "<span class='required'>" + word + "</span>"));
						});
					}
				}
				
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
				
				/* 검색 대상이 변경될 때마다 처리 이벤트 */
				$("#search").change(function(){
					if($("#search").val()=="all"){
						$("#keyword").val("전체 데이터 조회합니다.");
					}else if($("#search").val()!="all"){
						$("#keyword").val("");
						$("#keyword").focus();
					}
				});
				
				/* 검색 버튼 클릭 시 처리 이벤트 */
				$("#searchData").click(function(){
					if($("#search").val()!="all"){
						if(checkExp("#keyword", "검색어")) return;
					}
					goPage();
				});
				
				/* 글쓰기 버튼 클릭 시 처리 이벤트 */
				$("#insertFormBtn").click(function(){
					location.href = "/board/writeForm";
				});
				
				/* 검색 기능을 수행할 함수 */
				function goPage(){
					if($("#search").val()=="all"){
						$("#keyword").val("");
					}
					$("#f_search").attr({
						"method":"get",
						"action":"/board/boardList"
					});
					$("#f_search").submit();
				}
			});
		</script>
	</head>
	<body>
		<div class="container">
<!-- 			<div class="page-header"><h3 class="text-center">게시판 리스트</h3></div> -->
			
			<form id="detailForm">
				<input type="hidden" id="b_num" name="b_num" />
			</form>
			
			<%-- 검색기능 시작 --%>
			<div id="boardSearch" class="text-right">
				<form id="f_search" name="f_search" class="form-inline">
					<div class="form-group">
						<label>검색조건</label>
						<select id="search" name="search" class="form-control">
							<option value="all">전체</option>
							<option value="b_title">제목</option>
							<option value="b_content">내용</option>
							<option value="b_name">작성자</option>
						</select>
						<input type="text" name="keyword" id="keyword" value="검색어를 입력하세요" class="form-control" />
						<button type="button" id="searchData" class="btn btn-success">검색</button>
					</div>
				</form>
			</div>
			
			
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
