
function showPage(pageNum, cnt, paging){
	var endNum = Math.ceil(pageNum / 10.0) * 10;  
	var startNum = endNum - 9; 
	
	var prev = startNum != 1;
	var next = false;
	
	if(endNum * 10 >= cnt){
		endNum = Math.ceil(cnt/10.0);
	}
	
	if(endNum * 10 < cnt){
		next = true;
	}
	
	var str = '<ul class="pagination">'; 
	if(prev){
		str+= '<li class="paginate_button previous"><a href="'+(startNum -1)+'">Previous</a></li>';
	}
			        
	for(var i = startNum; i <= endNum; i++){
		var active = pageNum == i? "active":"";
		str+= '<li class="paginate_button '+active+'"><a href="'+i+'">'+i+'</a></li>';
	}
	
	if(next){
		str+= '<li class="paginate_button next"><a href="'+(endNum + 1)+'">Next</a></li>';
	}
	
	str += '</ul></div>';
	paging.html(str);
}
