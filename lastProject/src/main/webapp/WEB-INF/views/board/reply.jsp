<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		
		<title>댓글</title>
		
		<!-- 모바일 웹 페이지 설정 -->
		<link rel="shortcut icon" href="/resources/images/common/icon.png" />
		<link rel="apple-touch-icon" href="/resources/images/common/icon.png " />
		<!-- 모바일 웹 페이지 설정 끝 -->
		
		<!--[if It IE 9]>
		<script src="/resources/include/js/htmlSshivjs"></script>
		<![endif] -->
		
		<!-- 
			<link rel="stylesheet" type="text/css" href="/resources/include/css/common.css" />
			<link rel="stylesheet" type="text/css" href="/resources/include/css/board.css" />
		-->
		<link rel="stylesheet" type="text/css" href="/resources/include/css/default.css" />
		
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap-theme.min.css" />
		
		<script type="text/javascript" src="/resources/include/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="/resources/include/js/common.js"></script>
		<script type="text/javascript" src="/resources/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			var message = "작성시 입력한 비밀번호를 입력해 주세요.", btnKind="";
			$(function() {
				/** 기본 댓글 목록 불러오기 */
				var b_num = ${detail.b_num};
				listAll(b_num);

				/** data-button 속성으로 모달의 버튼에 상황에 따른 등록과 수정 구분 */
				if(!$("#replyInsertBtn").attr("data-button")){
					$("#replyInsertBtn").attr("data-button", "insertBtn");
				}

				/** 댓글 등록 버튼 클릭 시 모달창 설정 작업 */
				$("#replyInsertFormBtn").click(function(){
					setModal("댓글 등록", "insertBtn", "등록");
					dataReset();
					$('#replyModal').modal();
				});
				
				/** 글입력을 위한 Ajax 연동 처리 */
				$(document).on("click", "button[data-button='insertBtn']", function(){
					var insertUrl = "/replies/replyInsert";
					
					/* JSON.stringify(): JavaScript 값이나 객체를 JSON 문자열로 변환. */
					/* Controller에서 @RequestBody로 vo에 자동맵핑을 위해 vo와 동일한 필드명을 써서 JSON data 생성 */
					var value = JSON.stringify({
						b_num: b_num,
						r_name: $("#r_name").val(),
						r_pwd : $("#r_pwd").val(),
						r_content: $("#r_content").val()
					});
				
					/*
						X-HTTP-Method-Override 속성
						-환경에 따라 이 4가지 전부를 이용하지 못할 때 사용
						-추가적인 정보를 이용해서 PUT, PATCH, DELETE와 같은 정보를 같이 전송하는 것
						-RESTful API: GET은 select, POST는 insert, PUT은 update, DELETE는 delete
					*/
					$.ajax({
						url : insertUrl, //전송 url
						type : "post", //전송 시 method 방식
						headers : {
							"Content-Type":"application/json",	//전달하는 data가 application/json 방식
							"X-HTTP-Method-Override": "POST"	//일관성을 맞추기 위해 한번 더 명시
						},
						datatype : "text",
						data : value, //JSON Data 전달
						error : function(){ //실행 시 오류가 발생하였을 경우
							alert("시스템 오류 입니다. 관리자에게 문의 하세요");
						},
						
						// 전송 전 실행하는 속성
						beforeSend: function(){
							if (checkExp("#r_name", "작성자를")) return false;
							else if (checkExp ("#r_content","댓글 내용")) return false;
							else if (checkExp ("#r_pwd"," 비밀번호")) return false;
						},
						
						success : function(result){
							if(result=="SUCCESS"){
								alert("댓글 등록이 완료되었습니다");
								dataReset();
								$ ('#replyModal').modal('hide'); //부트스트랩에서 제공하는 모달창 제어 함수
								listAll(b_num);
							}
						}
					});
				});

				$('button[data-dismiss="modal"]').click(function(){
					dataReset();
				});
				
				/* 비밀번호 체크 화면에서 “취소” 버튼 클릭 처리 */
				$(document).on("click", ".pwdResetBut", function() {
					$(this).parents("div.panel .panel-heading .pwdArea").remove();
				});

				/* 비밀번호 확인 버튼 클릭 시 처리 이벤트 */
				$(document).on("click", ".pwdCheckBut", function() {
					var r_num = $(this).parents("div.panel").attr("data-num");
					var form = $(this).parents(".inline");
					var pwd = form.find(".passwd");
					var msg = form.find(".msg");
					var value = 0;
					
					if (formCheck(pwd, msg, "비밀번호")) return;
					else {
						/* 
							호출 함수.then() : 함수 매개변수로 성공 콜백함수, 실패 콜백함수 넘겨줌 
							- 각 함수의 매개변수는 resolve, reject 함수에서 넘겨준 매개변수를 자동으로 넘겨 받는다. 
							- 동기 처리를 위해 사용
							
							매개변수 data : pwdCheck의 결과
						*/
						pwdCheck(r_num, pwd, msg).then(function(data){
							console.log ("data : " + data);
							if(data==1){
								console.log("비밀번호 확인 후 btnKind : " + btnKind);
								if(btnKind=="upBtn"){
									//console.log(“수정 폼 출력”);
									updateForm(r_num);
								}else if(btnKind=="delBtn"){
									//console.log ("삭제 처리");
									deleteBtn(b_num, r_num);
								}
							}
							btnKind="";
						});
					}
				});
				
				/* 비밀번호 확인 버튼 클릭 시 실질적인 처리 함수 */
				/**
				 * 참고 : Promise는 비동기 처리가 성공(fulfilled)하였는지 또는 실패(rejected)하였는지
				 * 등의 상태 정보와 처리 종료 후 실행될 콜백함수(then , catch) 담고 있는 객체이다. 
				 * jQuery.Deferred는 각각의 비동기식 처리에 Promise 객체를 연계하여 그 상태를 
				 * 전파하는 것으로 promise를 구현한 jQuery 객체이다. Deferred 객체는 상태를 가지고 있는데 
				 * 이는 비동기식 처리의 상태가 변경되는 시점에 특정 함수(resolve(), reject())를 호출하여 
				 * Deferred 객체에 상태를 부여하기 때문이다.
				 * 
				 * 성공했을 때에는 resolve, 실패했을 때에는 reject 메소드를 호출하면 각각 resolve는 done으로, reject는 fail로 연결
				 */
				function pwdCheck(r_num, pwd, msg){
					var def = new $.Deferred();
					
					$.ajax({
						url : "/replies/pwdConfirm.json",
						type : "POST",
						data : "r_num="+r_num+"&r_pwd="+pwd.val(),
						dataType : "text",
						error : function(){
							alert("시스템 오류 입니다. 관리자에게 문의 하세요");
						},
						
						success : function(resultData){
							console.log("resultData : " + resultData);
							// 비동기 함수 success 콜백 함수에 def.resolve() 함수 호출
							if(resultData==0){ // 일치하지 않는 경우
								msg.addClass("msg_error");
								msg.text ("입력한 비밀번호가 일치하지 않습니다");
								pwd.select();
							}else if(resultData==1){ // 일치할 경우
								// 위에 정의되어 있는 pwdCheck에 대한 .then()을 호출
								def.resolve(resultData);
								$(pwd).parents("div.panel .panel-heading .pwdArea").remove();
							}
						}
					});
					
					// def.promise() 함수 리턴
					return def.promise();
				}

				/* 비밀번호 입력 양식에 키보드로 문자를 누르면 처리 이벤트 */
				$(document).on("keydown", ".passwd", function() {
					var span = $(this).parents("form").find("span");
					span.removeClass("msg_error");
					span.addClass("msg_default");
					span.html(message);
				});
				
				/* 수정 폼 화면 구현 함수 */
				function updateForm(r_num){
					$.ajax({
						url : "/replies/"+r_num+".json",
						type : "get",
						dataType : "json",
						error : function(){
							alert('시스템 오류입니다. 관리자에게 문의하세요');
						},
						
						success : function(data){
							$("#r_name").val(data.r_name);
							$("#r_content").val(data.r_content);
							
							var num_input = $("<input>");
							num_input.attr({"type": "hidden", "name":"r_num"});
							num_input.val(r_num);
							$("#comment_form").append(num_input);
							
							setModal("댓글 수정", "updateBtn", "수정");
							$('#replyModal').modal();
						}
					});
				}
				
				/** 글 삭제를 위한 Ajax 연동 처리 */
				function deleteBtn(b_num, r_num){
					if(confirm("선택하신 댓글을 삭제하시겠습니까?")) {
						$.ajax({
							//url : '/replies/' + b_num + '/' + r_num,
							url : '/replies/' + r_num,
							type : 'delete',
							headers : {
								"X-HTTP-Method-Override" : "DELETE"
							},
							dataType : 'text',
							error : function(){
								alert("시스템 오류 입니다 관리자에게 문의 하세요");
							},
							
							success : function(result) {
								console.log("result: " + result);
								if (result=="SUCCESS") {
									console.log("b_num: " + b_num);
									alert("댓글 삭제가 완료되었습니다.");
									listAll(b_num);
								}
							}
						});
					}
				}
					
					
				/** 수정 및 삭제 전 비밀번호 화면 출력을 위한 저리 */
				$(document).on("click", "button[data-btn]", function(){
					$(".btn").parents("div.panel .panel-heading .pwdArea").remove();	//다른 댓글 목록들의 비밀번호 화면 삭제
					$(this).parents("div.panel .panel-heading").append(pwdView());		//클릭된 댓글 목록에 비밀번호 화면 추가
					
					btnKind = $(this).attr("data-btn");		// upBtn or delBtn을 입력
					
					console.log("클릭 버튼 btnKind : " + btnKind);
				});
					
				/** 수정을 위한 Ajax 연동 처리 */
				$(document).on("click", "button[data-button='updateBtn']", function(){
					//console. log ("수정버튼");
					var r_num = $("input[name='r_num']").val();
					if (checkExp("#r_content", "댓글 내용")) return;
					else {
						$.ajax({
							url : "/replies/" + r_num,
							type: "put",
							headers: {
								"Content-Type": "application/json",
								"X-HTTP-Method-Override": "PUT" 
							},
							data: JSON.stringify({
								r_content: $("#r_content").val(),
								r_pwd: $("#r_pwd").val()
							}),
							dataType: "text",
							error : function(){
								alert("시스템 오류입니다. 관리자에게 문의하세요.");
							},
							
							success:function(result){
								console.log("result: " + result);
								if(result == "SUCCESS"){
									alert("댓글 수정이 완료되었습니다");
									dataReset();
									$('#replyModal').modal('hide');
									listAll(b_num);
								}
							}
						});
					}
				}); 
			}); // 최상위 $ 종료
			
			/** 댓글 목록 보여주는 함수*/
			function listAll(b_num){
				$("#reviewList").html("");
				var url = "/replies/all/"+b_num+".json";
				
				// $.getJSON : url을 요청하고, JSON 타입의 data을 매개변수로 갖는 함수를 실행
				$.getJSON(url, function(data){
					console.log("list count: "+data.length);
					replyCnt = data.length;
					$(data).each(function(){
						var r_num = this.r_num;
						var r_name = this.r_name;
						var r_content = this.r_content;
						var r_date = this.r_date;
						r_content = r_content.replace(/(\r\n|\r|\n)/g, "<br />");	// 줄바꿈 문자를 태그로 변환
						addItem(r_num, r_name, r_content, r_date);	// 댓글 리스트 생성
					});
				}).fail(function() {
					alert("댓글 목록을 불러오는데 실패하였습니다. 잠시 후에 다시 시도해 주세요.");
				});
			}
			
			/** 새로운 글을 화면에 추가하기(보여주기) 위한 함수 */
			function addItem(r_num, r_name, r_content, r_date) {
				// 새로운 글이 추가될 div태그 객체
				var wrapper_div = $("<div>");
				wrapper_div.attr("data-num", r_num);
				wrapper_div.addClass("panel panel-default");
				
				var new_div = $("<div>");
				new_div.addClass("panel-heading");
				
				// 작성자 정보의 이름
				var name_span = $("<span>");
				name_span.addClass("name");
				name_span.html(r_name + "님");
				
				// 작성일시
				var date_span = $("<span>");
				date_span.html(" / " + r_date + " ");
				
				// 수정하기 버튼
				var upBtn = $("<button>");
				upBtn.attr({"type" : "button"});
				upBtn.attr("data-btn", "upBtn"); //동적 개체를 지칭하기 위한 속성
				upBtn.addClass("btn btn-primary gap");
				upBtn.html("수정하기");
				
				// 삭제하기 버튼
				var delBtn = $("<button>");
				delBtn.attr({"type" : "button"});
				delBtn.attr("data-btn", "delBtn");
				delBtn.addClass("btn btn-primary gap");
				delBtn.html("삭제하기");
				
				// 내용
				var content_div = $("<div>");
				content_div.html(r_content);
				content_div.addClass("panel-body");
				
				
				// 조립하기
				new_div.append(name_span).append(date_span).append(upBtn).append(delBtn);
				wrapper_div.append(new_div).append(content_div);
				$("#reviewList").append(wrapper_div);
			}
			
			/* 입력 폼 조기화 */
			function dataReset() {
				$("#r_name").val("");		//form의 작성자명 값 비우기
				$("#r_pwd ").val("");		//form의 비밀번호 값 비우기
				$("#r_content").val("");	//form의 내용 값 비우기
			}
			
			/* 모달창 초기화 */
			function setModal(title, value, text) {
				$("#replyModalLabel").html(title);					//모달 창의 제목을 title로 값 변경
				$("#replyInsertBtn").attr("data-button", value);	//모달의 등록 버튼에 button 속성 부여
				$("#replyInsertBtn").html(text);					//등록 버튼 안에 값 변경
			}
			 
			/** 비밀번호 체크를 동적 화면 구현 */
			function pwdView(){
				var span = $("<span>");
				span.addClass("pwdArea");
				
				var pwd_form = $("<form>");
				pwd_form.attr("name", "f_pwd");
				pwd_form.addClass("form-inline inline");
				
				var pwd_label = $("<label>");
				pwd_label.attr("for", "passwd");
				pwd_label.html("비밀번호 : ");
				
				var pwd_input = $("<input>");
				pwd_input.attr({"type" : "password ", "name" : " passwd "});
				pwd_input.addClass("form-control passwd gap");
				pwd_input.prop("autofocus", "autofocus");
				
				var pwd_check = $("<input>");
				pwd_check.attr({"type" : "button", "value" : "확인"});
				pwd_check.addClass("btn btn-default pwdCheckBut gap");
				
				var pwd_reset = $("<input>");
				pwd_reset.attr({"type" : "button", "value" : "취소"});
				pwd_reset.addClass("btn btn-default pwdResetBut gap");
				
				var pwd_span = $("<span>");
				pwd_span.addClass("msg");
				pwd_span.html(message);
				
				pwd_form.append(pwd_label).append(pwd_input).append(pwd_check).append(pwd_reset).append(pwd_span);
				return span.append(pwd_form);
			}
		</script>
	</head>
	<body>
		<div id="replyContainer">
	
			<%-- 등록 버튼 영역 --%>
			<p class="text-right">
				<button type="button" class="btn btn-success" id="replyInsertFormBtn">댓글 등록</button>
				<!-- data-toggle="modal" data-target="#replyModal" data-whatever= "@mdo" -->
			<p>
	
			<%-- 리스트 영역 --%>
			<div id="reviewList"></div>
	
			<%-- 등록 화면 영역(modal) --%>
			<%-- 
				data-backdrop : 배경 클릭 시, 모달 닫힘 off(static)
				data-keyboard : Esc 키가 눌렸을 때, 모달 닫힘 (true)
			--%>
			<div class="modal fade" id="replyModal" tabindex="-1" role="dialog"
				aria-labelledby="replyModalLabel" aria-hidden="true"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="replyModalLabel">댓글 등록</h4>
						</div>
						<div class="modal-body">
							<form id="comment_form" name="comment_form">
								<input type="hidden" name="b_num" maxlength="5" value="${detail.b_num}" />
								<div class="form-group">
									<label for="g_name" class="control-label"> 작성자 </label> 
									<input type="text" class="form-control" name="r_name" id="r_name"
										maxlength="5" />
								</div>
								<div class="form-group">
									<label for="g_content" class="control-label">글내용</label>
									<textarea class="form-control" name="r_content" id="r_content" rows="5"></textarea>
								</div>
								<div class="form-group">
									<label for="g_pwd" class="control-label">비밀번호</label> 
									<input type="password" class="form-control" name="r_pwd" id="r_pwd" />
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
							<button type="button" class="btn btn-success" id="replyInsertBtn">등록</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>