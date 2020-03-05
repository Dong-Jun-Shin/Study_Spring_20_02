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
		
		<!-- 모바일 웹 페이지 설정 끝 -->

		<!--[if lt IE 9] IE9라면 실행>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		
		<script type="text/javascript" src="/resources/include/js/jquery-3.3.1.min.js"></script>
		<!-- 부트스트랩 -->
		<script type="text/javascript" src="/resources/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(function(){
			//전체 상황 체크
			$("#btn").click(function(){
				$("#result").html("");
				for(var ip = 101; ip <= 140; ip++){
					check(ip);
				}
			})
			
			//개별 버튼, 이동
			$(document).on("click", ".check", function(){
				location.href = "http://192.168.0." + $(this).attr("data-val") + ":8080/board/boardList";
			})
		})
		
		function check(ip){
			var str = "";
			var sendUrl = "http://192.168.0." + ip + ":8080";
			$.ajax({
    			 url : sendUrl,
       			 type : "get",
       			 dataType : "text",
      			 
       			 error : function(){
//       				 	str = sendUrl + " : 비활성화 ";
//       				 	console.log(str);
      				 	createBtn(ip, "gray");
       			 },
      			 
       			 success : function(resultData){
//       				 	str = sendUrl + " : 활성화 ";
//        					console.log(str);
       					createBtn(ip, "skyblue");
       			 }
       		})
		}
		
		function createBtn(ip, color){
			var result = $("#result");
			var btn = $("<button>").attr({"type":"button", "class":"check", "data-val": ip, "style":"background:"+color}).html(ip);
			
			result.append(btn).append("    ");
			
// 			if(ip%10==0 && ip > 1){
// 				result.append($("<br>"));
// 			}
		}
		</script>
	</head>
	<body>
		<button type="button" id="btn">확인</button>
		<hr />
		<div id="result"></div>
	</body>
</html>
