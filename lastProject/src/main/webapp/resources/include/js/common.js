function checkExp(elem, str){
	var spaceExp = /\s/g;
	
	if($(elem).val().replace(spaceExp, "")==""){
		alert(str + "을(를) 입력해주세요.");
		$(elem).focus();
		$(elem).val("");
		
		return true;
	}
	
	return false;
};


//formCheck(유효성 체크 대상, 출력 영역, 메시지 내용)
function formCheck(elem, item, msg){
	if(elem.val().replace(/\s/g, "")==""){
		item.html(msg + " 입력해주세요.");
		elem.val("");
		elem.focus();

		return true;
	}else{
		return false;
	}
}

/*
 * 파일 확장자 체크(파일)
 */
function checkFile(file){
	/*
	 * 배열내의 값을 찾아서 인덱스를 반환(요소가 없을 경우 -1 반환)
	 * jQuery.inArray(찾을 값, 검색 대상의 배열)
	 * 
	 * .split('.') : 구분자를 기준으로 배열 생성
	 * .pop() : 마지막 배열 반환
	 * .toLowerCase() : 모두 소문자로 변환 
	 */
	
//	var fileName = file.val();
//	var divide = fileName.indexOf('.');
//	var nameEnd = fileName.length;
//	var extension = fileName.substring(divide, nameEnd).toLowerCase();
	var ext = file.val().split('.').pop().toLowerCase();

	if(jQuery.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
		alert('gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.');
		return true;
	}else{
		return false;
	}
}