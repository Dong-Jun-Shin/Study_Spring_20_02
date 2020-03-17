<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />      	

		<title>갤러리 리스트</title>
		
		<!-- 모바일 웹 페이지 설정 - 이미지 경로 위치는 각자 변경 -->
		<link rel="shortcut icon" href="/resources/images/icon.png" />
		<link rel="apple-touch-icon" href="/resources/images/icon.png" />
		
		<link rel="stylesheet" type="text/css" href="/resources/include/css/default.css" />

		<!-- 부트스트랩 -->
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/resources/include/dist/css/bootstrap-theme.css" />
		
		<!-- 모바일 웹 페이지 설정 끝 -->

		<!--[if lt IE 9] IE9라면 실행>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<script type="text/javascript" src="/resources/include/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="/resources/include/js/jquery.form.min.js"></script>
		<script type="text/javascript" src="/resources/include/js/common.js"></script>
		
		<!-- lightbox 라이브러리 -->
		<link rel="stylesheet" type="text/css" href="/resources/include/css/lightbox.min.css" />
		<script type="text/javascript" src="/resources/include/js/lightbox.min.js"></script>
		
		<script type="text/javascript">
			var message = "입력한 비밀번호를 입력해 주세요.", btnKind="", galleryNum = 0;
			
			$(function(){
				listData();
				
				if(!$("#galleryBtn").attr("data-button")){
					$("#galleryBtn").attr("data-button", "insertBtn");
				}
				
				$("#galleryInsertBtn").click(function(){
					setModal("갤러리 등록", "insertBtn", "등록");
					dataReset();
					$("#galleryModal").modal();
				});
				
				/* 등록 버튼 클릭 시 처리 이벤트 */
				$(document).on("click", "button[data-button='insertBtn']", function(){
					//입력값 체크
					if(checkExp($("#g_name"), "작성자")) return;
					else if (checkExp($("#g_subject"), "글제목")) return;
					else if (checkExp($("#g_content"), "내용")) return;
					else if (checkExp($("#file"), "등록할 이미지")) return;
					else if (checkFile($("#file"))) return;
					else if (checkExp($("#g_pwd"), "비밀번호")) return;
					else{
						// 첨부파일까지 함께 보내는 설정을 form에 적용하는 메서드 (jquery.form) (.ajaxForm)
						$("#f_writeForm").ajaxForm({
							url : "/gallery/galleryInsert",
							type : "post",						// 첨부파일로 인해 GET 불가
							
							/*
								application/x-www-form-urlencoded 은 영숫자가 아닌 
								데이터를 3바이트로 표현하기 때문에 바이너리 파일을 전송하면 3배로 전송하여 낭비가 발생됩니다.
								그렇기 때문에 파일을 전송할 때 multipart/form-data를 사용하지만, MIME 헤더가 추가되는 
								오버헤드가 발생되기 때문에 텍스트로만 이루어진 데이터 전송은 오히려 비효율적인 것입니다.
								따라서 전송되는 데이터의 유형과 양에 따라 효율적인 Content-type을 선택해야 합니다.
							*/
							enctype : "multipart/form-data",	// HTTP header의 Content-Type 수정
							
							dataType : "text",
							error : function(){
								alert("시스템 오류 입니다. 관리자에게 문의해 주세요.");
							},
							success : function(data){
								console.log(data);
								if(data=="성공"){
									listData();
									dataReset();
									$("#galleryModal").modal("hide");
								}else{
									alert("[ " + data + " ]\n등록에 문제가 있어 완료하지 못하였습니다. 잠시 후 다시 시도해주세요.");
									dataReset();
								}
							}
						});
						$("#f_writeForm").submit();
					}
				});
			}); //최상위 $ 종료
			
			//갤러리 리스트 요청 처리
			function listData(){
				console.log("리스트 출력");
				$("#rowArea").html("");
				var count = 0;
				
				$.getJSON("/gallery/galleryData", function(data){
					console.log("length: " + data.length);
					$(data).each(function(index){
						var g_num = this.g_num;
						var g_name = this.g_name;
						var g_subject = this.g_subject;
						var g_content = this.g_content;
						var g_thumb = this.g_thumb;
						var g_file = this.g_file;
						var g_date = this.g_date;
						thumbnailList(g_num, g_name, g_subject, g_content, g_thumb, g_file, g_date, index);
// 						accordionList(g_num, g_name, g_subject, g_content, g_thumb, g_file, g_date, index);
					});
				}).fail(function(){
					alert("목록을 불러오는데 실패하였습니다. 잠시후에 다시 시도해 주세요.");
				});
			}
			
			//부트스트랩을 활용하여 썸네일 요소를 동적으로 생성(콤포넌트 - 썸네일(Thumbnails))
			function thumbnailList(g_num, g_name, g_subject, g_content, g_thumb, g_file, g_date, index){
				var column = $("<div>");
				column.attr("data-num",g_num);
				column.addClass("col-sm-6 col-md-4");
				
				var thumbnail = $("<div>");
				thumbnail.addClass("thumbnail");

				var lightbox_a = $("<a>");
				lightbox_a.attr({"href":"/uploadStorage/gallery/"+g_file, "data-lightbox":"roadtrip", "title":g_subject});

				var img = $("<img>");
				img.attr("src", "/uploadStorage/gallery/thumbnail/"+g_thumb);

				var caption = $("<div>");
				caption.addClass("caption");
				
				var h3 = $("<h3>");
				h3.html(g_subject.substring(0, 12)+"...");
				
				var pInfo = $("<p>");
				pInfo.html("작성자: "+ g_name +" / 등록일: " + g_date);
				
				var pContent = $("<p>");
				pContent.html(g_content.substring(0, 24)+"...");
				
				var pBtnArea = $("<p>");
				
				var upBtn = $("<a>");
				upBtn.attr({"data-btn":"upBtn", "role":"button"});
				upBtn.addClass("btn btn-primary gap");
				upBtn.html("수정");
				
				var delBtn = $("<a>");
				delBtn.attr({"data-btn":"delBtn", "role":"button"});
				delBtn.addClass("btn btn-default");
				delBtn.html("삭제");
				
				pBtnArea.append(upBtn).append(delBtn)
				caption.append(h3).append(pInfo).append(pContent).append(pBtnArea);
				column.append(thumbnail.append(lightbox_a.append(img)).append(caption));
				
				$("#rowArea").append(column);
			}
			
			// 폼 초기화 작업
			function dataReset(){
				$("#f_writeForm").each(function(){
					this.reset();
				});
			}
			
			// modal 초기화 작업
			function setModal(title, value, text){
				$("#galleryModalLabel").html(title);
				$("#galleryBtn").attr("data-button", value);
				$("#galleryBtn").html(text);
				if($("#gallseryBtn").attr("data-button")=="insertBtn"){
					$("#f_wrtieForm > input[type='hidden'], #f_writeForm .image_area > img").remove();
					$("#g_name").removeAttr("readonly");
					galleryNum = 0;
				}
			}
		</script>
	</head>
	<body>
		 <div class="contentContainer container">
		 	<%-- 등록 버튼 영역 --%>
		 	<p class="text-right">
		 		<button type="button" class="btn btn-primary" id="galleryInsertBtn">갤러리 등록</button>
		 	</p>
		 	
		 	<%-- 갤러리 리스트 영역(썸네일) --%>
		 	<div class="row" id="rowArea"></div>
		 	
		 	<%-- 갤러리 등록 화면 영역(modal) --%>
		 	<div class="modal fade" id="galleryModal" tabindex="-1" role="dialog" aria-labelledby="galleryModalLabel"  
		 			aria-hidden="true" data-backdrop="static" data-keyboard="false">
		 		<div class="modal-dialog">
		 			<div class="modal-content">
		 				<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
							<h4 class="modal-title" id="galleryModalLabel">갤러리 등록</h4>
		 				</div>
		 				<div class="modal-body">
		 					<form id="f_writeForm" name="f_writeForm">
		 						<div class="form-group">
		 							<label for="g_name" class="control-label">작성자</label>
		 							<input type="text" class="form-control" name="g_name" id="g_name" maxlength="5" />
		 						</div>
		 						<div class="form-group">
		 							<label for="g_subject" class="control-label">글제목</label>
		 							<input type="text" class="form-control" name="g_subject" id="g_subject" maxlength="" />
		 						</div>
		 						<div class="form-group">
		 							<label for="g_content" class="control-label">글내용</label>
		 							<textarea class="form-control" name="g_content" id="g_content" rows="4"></textarea>
		 						</div>
		 						<div class="form-group image_area">
		 							<label for="file" class="control-label">이미지</label>
		 							<!-- multipartFile용으로 만든 VO의 file 필드명을 사용 -->
		 							<input type="file" name="file" id="file" />
		 						</div>
		 						<div class="form-group">
		 							<label for="g_pwd" class="control-label">비밀번호</label>
		 							<input type="password" class="form-control" name="g_pwd" id="g_pwd" />
		 						</div>
		 					</form>
		 				</div>
		 				<div class="modal-footer">
		 					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		 					<button type="button" class="btn btn-primary" id="galleryBtn">등록</button>
		 				</div>
		 			</div>
		 		</div>
		 	</div>
		 </div>
	</body>
</html>
