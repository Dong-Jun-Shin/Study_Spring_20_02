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

		<title>글쓰기 화면</title>
		
		<!-- 모바일 웹 페이지 설정 - 이미지 경로 위치는 각자 변경 -->
		<link rel="shortcut icon" href="/resources/images/icon.png" />
		<link rel="apple-touch-icon" href="/resources/images/icon.png" />
		
<!-- 		<link rel="stylesheet" type="text/css" href="/resources/include/css/default.css" /> -->
		
		<!-- 부트스트랩 -->
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap-theme.css" />
		
		<!-- 모바일 웹 페이지 설정 끝 -->

		<!--[if lt IE 9] IE9라면 실행>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		

		<script type="text/javascript" src="/resources/include/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="/resources/include/js/common.js"></script>
		<!-- 부트스트랩 -->
		<script type="text/javascript" src="/resources/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#boardInsertBtn").click(function(){
					if(checkExp("#b_name", "작성자")) return;
					if(checkExp("#b_title", "글제목")) return;
					if(checkExp("#b_content", "글내용")) return;
					if(checkExp("#b_pwd", "비밀번호")) return;
					
					$("#f_writeForm").attr({
						"method" : "post",
						"action" : "/board/boardInsert"
					});
					
					$("#f_writeForm").submit();
				})
				
				$("#boardCancleBtn").click(function(){
//					 JavaScript는 가능하지만, jQuery에서는 form 원소에 직접 .reset을 할 수 없기 때문에
//					 .each를 이용해서 this로 원소마다 reset을 호출해야 한다
					$("#f_writeForm").each(function(){
						this.reset();
					});

					alert("작성된 내용이 초기화되었습니다.");
				})
				
				$("#boardListBtn").click(function(){
					location.href = "/board/boardList";
				})
			})
		</script>
	</head>
	<body>
		<div class="contentContainer container">
			<div class="contentTit page-header"><h3 class="text-center">게시판 글작성</h3></div>
		
			<div class="contentTB text-center">
				<form id="f_writeForm" name="f_writeForm" class="form-horizontal">
					<table class="table table-bordered">
						<colgroup>
							<col width="20%" />
							<col width="80%" />
						</colgroup>
						<tbody>
							<tr>
								<td>작성자</td>
								<td class="text-left"><input type="text" name="b_name" id="b_name" class="form-control" /></td>
							</tr>
							<tr>
								<td>글제목</td>
								<td class="text-left"><input type="text" name="b_title" id="b_title" class="form-control" /></td>
							</tr>
							<tr>
								<td>글내용</td>
								<td class="text-left"><textarea name="b_content" id="b_content" class="form-control" rows="8" ></textarea></td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td class="text-left"><input type="password" name="b_pwd" id="b_pwd" class="form-control" /></td>
							</tr>
						</tbody>
					</table>
					
					<div class="text-right">
						<button type="button" class="btn btn-success" id="boardInsertBtn">저장</button>
						<button type="button" class="btn btn-success" id="boardCancleBtn">초기화</button>
						<button type="button" class="btn btn-success" id="boardListBtn">목록</button>
					</div>
				</form>
			</div>
			
		</div>
	</body>
</html>
