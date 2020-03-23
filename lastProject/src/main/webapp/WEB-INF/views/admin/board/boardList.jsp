<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jspf" %>
<script type="text/javascript" src="/resources/include/js/adminBoard.js"></script>
<script type="text/javascript">
	search = "<c:out value='${data.search}' />";
</script>

<%-- 스크립트 태그 정의 --%>
<h2 class="sub-header">게시판 리스트</h2>

<%-- 검색부분 정의 --%>
<div class="well">
	<form id="f_search" name="f_search" class="form-inline">
		<h3><span class="label label-success">검색조건</span></h3>
		<div class="form-group">
			<select id="search" name="search" class="form-control">
				<option value="b_title">제목</option>
				<option value="b_name">작성자</option>
				<option value="b_content">내용</option>
				<option value="b_date">작성일자</option>
			</select>
		</div>
		<div class="form-group" id="textCheck">
			<input type="text" name="keyword" id="keyword" class="form-control" placeholder="검색어를 입력하세요.">
		</div>
		<div class="form-group" id="dateCheck">
			<input type="date" name="start_date" id="start_date" placeholder="시작일자" class="form-control">
			<input type="date" name="end_date" id="end_date" placeholder="종료일자" class="form-control">
		</div>
		<button type="button" id="searchBtn" class="btn btn-primary">Search</button>
		<button type="button" id="allSearchBtn" class="btn btn-primary">All Search</button>
		<button type="button" id="excelDownBtn" class="btn btn-primary">Excel Down</button>
	</form>
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
							<td>${fn:length(boardList) - status.index }</td>
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