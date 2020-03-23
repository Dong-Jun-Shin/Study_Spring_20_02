var keyword = "", search = "", start_date = "", end_date = "";
$(function(){
	/* 엑셀다운로드 버튼 클릭 시 처리 이벤트 */
	$("#excelDownBtn").click(function(){
		$("#f_search").attr({
			"method":"get",
			"action":"/admin/board/boardExcel"
		});
		$("#f_search").submit();
	});
});