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
         var btnChk=0;//수정버튼과 삭제버튼을 구별하기 위한 변수
         $(function () {
            $("#pwdChk").css("visibility","hidden");
            
            //수정 버튼 클릭시 처리이벤트
            $("#updateFormBtn").click(function() {
               $("#pwdChk").css("visibility","visible");
               $("#msg").text("수정에 필요한 작성시 입력한 비밀번호를 입력해주세요").css("color","#000099");
               btnChk=1;
            });
            
            //삭제버튼 클릭시 처리 이벤트
            $("#boardDeletBtn").click(function () {
               $("#pwdChk").css("visibility","visible");
               $("#msg").text("삭제에 필요한 작성시 입력한 비밀번호를 입력해주세요").css("color","#000099");
               btnChk=2;
            });
            
            //비밀번호 확인 버튼 클릭 시 처리 이벤트
            $("#pwdBtn").click(function(){
            	boardPwdConfirm();
            });
            
            //글쓰기
            $("#insertFormBtn").click(function() {
               location.href="/board/writeForm";
            });
            
            //목록 버튼
            $("#boardListBtn").click(function () {
               location.href="/board/boardList";
            });
         });
         
         // 비밀번호 확인 버튼 클릭시 실질적인 처리 함수
         function boardPwdConfirm(){
        	 if(checkExp("#b_pwd", "비밀번호")) return;
        	 else{
        		 $.ajax({
        			 url : "/board/pwdConfirm",
        			 type : "post",
        			 data : $("#f_pwd").serialize(),
        			 dataType : "text",
        			 
        			 error : function(){
        				 alert("시스템 오류 입니다. 관리자에게 문의하세요");
        			 },
        			 
        			 success : function(resultData){
        				 if(resultData=="FAIL"){
        					 $("#msg").text("작성시 입력한 비밀번호가 일치하지 않습니다.").css("color", "red");
        					 $("#b_pwd").select();
        				 }else if(resultData=="SUCCESS"){
        					 $("#msg").text("");
        					 if(btnChk==1){
        						 goUrl = "/board/updateForm";
        					 }else if(btnChk==2){
        						 goUrl = "/board/boardDelete";
        					 }
        					 $("#f_data").attr("action",goUrl);
        					 $("#f_data").submit();
        				 }
        			 }
        		 })
        	 }
         }
      </script>
	</head>
	<body>
		<div class="contentContainer container">
			<form name="f_data" id="f_data" method="post">
               <input type="hidden" name="b_num" value="${detail.b_num}"/>
            </form>

<!-- 			<div class="contentTit page-header"><h3 class="text-center">게시판 상세보기</h3></div> -->
         
            <%--===================== 비밀번호 확인 버튼 및 추가 시작 ================= --%>
            <div id="boardPwdBut" class="row text-center">
               <div id="pwdChk" class="authArea col-md-8 text-left">
                  <form name="f_pwd" id="f_pwd" class="form-inline">
                     <input type="hidden" name="b_num" id="b_num" class="form-control" value="${detail.b_num}" />
                     <label for="b_pwd" id="l_pwd">비밀번호: </label>
                     <input type="password" name="b_pwd" id="b_pwd" class="form-control"/>
                     
                     <button type="button" id="pwdBtn" class="btn btn-default">확인</button>
                     <span id="msg"></span>
                  </form>
               </div>
               <div class="btnArea col-md-4 text-right">
                  <input type="button" value="글수정" id="updateFormBtn" class="btn btn-success"/>
                  <input type="button" value="글삭제" id="boardDeletBtn" class="btn btn-success"/>
                  <input type="button" value="글쓰기" id="insertFormBtn" class="btn btn-success"/>
                  <input type="button" value="글목록" id="boardListBtn" class="btn btn-success"/>
               </div>
            </div>
            <%--===================== 비밀번호 확인 버튼 및 추가 종료 ================= --%>
            
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
			<div style="width: 90%; margin: 0px auto;">
				<jsp:include page="reply.jsp" />
			</div>
		</div>
	</body>
</html>
