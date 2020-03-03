// 댓글 DB데이터를 JSON으로 가공한 후, 페이지에 출력
function listAll(num){
	// 기존 가지고 있는 목록 비우기
	$("#comment_list").html("");
	
	// ajax를 이용해서 JSON을 결과로 받아오기
	var url = "/siteProject/board_comment/getCommentList.do?num=" + num;
	$.getJSON(url, function(data){
		//console.log("list count: " + data.length); - 댓글 수
		replyCnt = data.length;
		
		// JSON 배열 수 만큼 목록을 구성
		$(data).each(function(){
			var bc_num = this.bc_num;
			var bc_name = this.bc_name;
			var bc_content = this.bc_content;
			var bc_date = this.bc_date;
			// 줄바꿈 문자를 태그로 변환
			bc_content = bc_content.replace(/(\r\n|\r|\n)/g, "<br />");
			
			addNewItem(bc_num, bc_name, bc_content, bc_date);
		});
	}).fail(function(){
		alert("댓글 목록을 불러오는데 실패했습니다. 잠시후에 다시 시도해 주세요.");
	});
}

// 댓글 조회 폼 생성 함수, addNewItem(글번호, 작성자 이름, 댓글 내용, 작성일시)
function addNewItem(bc_num, bc_name, bc_content, bc_date){
   var new_li = $("<li>");
   new_li.attr("data-bc_num", bc_num);
   new_li.addClass("comment_item");
   
   var writer_p = $("<p>");
   writer_p.addClass("writer");
   
   var name_span = $("<span>");
   name_span.addClass("name");
   name_span.html(bc_name + "님");
   
   var date_span = $("<span>");
   date_span.html(" / " + bc_date + " ");
   
   var update_input = $("<input>");
   update_input.attr({"type" : "button", "value" : "수정하기", "style" : "margin: 5px;"});
   update_input.addClass("update_btn").addClass("btn").addClass("btn-info");
   
   var del_input = $("<input>");
   del_input.attr({"type" : "button", "value" : "삭제하기", "style" : "margin: 5px;"});
   del_input.addClass("delete_btn").addClass("btn").addClass("btn-info");
   
   var content_p = $("<p>");
   content_p.html(bc_content);
   
   writer_p.append(name_span).append(date_span).append(update_input).append(del_input);
   new_li.append(writer_p).append(content_p);
   $("#comment_list").append(new_li).append($("<hr />"));
}

// 비밀번호 입력 폼 생성
function pwdForm(elem){
	var pwd_div = $("<div>");
	pwd_div.addClass("form-inline");
	pwd_div.html("비밀번호 : ");
	
	var pwd_input = $("<input type='password' name='bc_pwd'>");
	pwd_input.addClass("passwd");
	
	var pwd_btn = $("<button type='button' style='margin: 0px 5px;'>");
	pwd_btn.addClass("pwd_btn");
	pwd_btn.html("확인");

	var cancel_btn = $("<button type='button' style='margin: 0px 5px;'>");
	cancel_btn.addClass("cancel_btn");
	cancel_btn.html("취소");
	
	var pwd_span = $("<span style='color: blue;'>");
	pwd_span.addClass("msg");
	pwd_span.html(message);
	
	pwd_div.append(pwd_input).append(pwd_btn).append(cancel_btn).append($("<br>")).append(pwd_span);
	elem.parent("p").parent("li").append(pwd_div);
}

// 댓글 입력 폼 초기화
function dataReset(){
	$("#bc_name").val("").focus();
	$("#bc_pwd").val("").focus();
	$("#bc_content").val("").focus();
}

// 비밀번호 확인 클릭 시, 실질적으로 처리하는 함수
function commentPwdConfirm(_bc_pwd, _bc_num, msg){
	$.ajax({
		url : "/siteProject/board_comment/passwdCheck.do",
		type : "post",
		data : $("#processForm").serialize(), // 폼 전체 데이터 전송
		dataType : "text",
		
		success : function(resultData){
			var goUrl=""; // 이동할 경로를 저장할 변수
			
			if(resultData == 0){ // 불일치하는 경우
				msg.html("비밀번호가 일치하지 않습니다.");
			}else if(resultData == 1){ // 일치하는 경우
				msg.parent("div").remove();
			
				// 비밀번호 검증 후, 버튼 구분 및 처리 (1=수정, 2=삭제)
				if(btnChk == 1){
					// 업데이트 폼 생성 함수 사용
					updateForm(_bc_num);
				}
				
				if(btnChk == 2){
					// 댓글 삭제 처리 
					deleteBtn($("#processForm"))
				}
			}
		},
		
		error : function(){
			alert("시스템 오류입니다. 관리자에게 문의하세요");
		}
	});
}

// 업데이트 폼 생성
function updateForm(bc_num){
   var content = $("li[data-bc_num="+ bc_num + "]").children("p").eq(1).html(); //0부터 시작
   // 내용 중 줄바꿈 관련 태그, 문자로 변환
   content = content.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');
   
   var content_area = $("<textarea rows='6' cols='45' name='bc_content'>");
   content_area.html(content);
   
   var btn_p = $("<p>");
   btn_p.addClass("btn_group");
   
   var update_input = $("<input>");
   update_input.attr({"type" : "button", "value" : "수정하기"});
   update_input.addClass("update_proc").addClass("btn").addClass("btn-info");
   
   var cancel_input = $("<input>");
   cancel_input.attr({"type" : "button", "value" : "취소하기", "style" : "margin: 0px 5px;"});
   cancel_input.addClass("cancel_proc").addClass("btn").addClass("btn-info");
   
   btn_p.append(update_input).append(cancel_input);
   
   var comment_li = $("li[data-bc_num="+ bc_num + "]"); 

   var title_p = comment_li.children("p");
   title_p.children("input").hide();
   title_p.eq(1).remove();
   
   comment_li.append(content_area);
   comment_li.append(btn_p);
}

// 삭제 처리
function deleteBtn(form){
	if(confirm("선택하신 댓글을 삭제하시겠습니까?")){
		$.ajax({
			url : "/siteProject/board_comment/deleteComment.do",
			type : "post",
			data : form.serialize(),
			dataType : "text",
			
			error : function(){
				alert("시스템 오류입니다. 관리자에게 문의 하세요.")
			},
			
			success : function(resultData){
				if(resultData == "SUCCESS"){
					alert("댓글 삭제 완료되었습니다.");
					listAll(num);
				}
			}
		});
	}
}