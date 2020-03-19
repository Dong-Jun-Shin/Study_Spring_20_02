<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jspf" %>

<%-- 스크립트 태그 정의 --%>

<h2 class="sub-header">게시판 리스트</h2>

<%-- 검색부분 정의 --%>
<div class="well">

</div>

<%-- 게시판 리스트 --%>
<div class="table-responsive">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th class="tac">글번호</th>
				<th class="tac">글제목</th>
				<th class="tac">작성일</th>
				<th class="tac">작성자</th>
			</tr>
		</thead>
		<tbody>
			<!-- 데이터 출력 -->
			<c:choose>
				<c:when test="${not empty boardList}">
					<c:forEach var="board" items="${boardList }" varStatus="status">
						<tr class="tac" data-num="${board.b_num }">
							<td>${board.b_num }</td>
							<td class="goDetail tal">${board.b_title }</td>
							<td>${board.b_date }</td>
							<td class="name">${board.b_name }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4" class="tac">등록된 게시물이 존재하지 않습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>