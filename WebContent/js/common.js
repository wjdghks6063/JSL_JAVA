	/* input 공백,자리수 검사 */	/*input 상자,input상자에  띄울 메세지,테이블 자릿수 제한*/ 
	function inputCheck_empty_length(obj, msg,mByte) {
		if(obj.value ==""){
			alert(msg);
			obj.focus();
			return true;
		}	

	  	var maxByte = mByte; //최대 입력 바이트 수
	  	var str = strTrim(obj.value);
	  	obj.value = str;
	  	var str_len = str.length;

		var rbyte = 0;
		var one_char = "";
		
		for (var i = 0; i < str_len; i++) {
		    one_char = str.charAt(i);
		
		    if (escape(one_char).length > 4) {
		        rbyte += 2; //한글2Byte
		    } else {
		        rbyte++; //영문 등 나머지 1Byte
		    }
		}
		//alert(rbyte); 
	    if (rbyte > maxByte) {
	        alert("한글 " + (maxByte / 2) + "자 / 영문 " + maxByte + "자를 초과 입력할 수 없습니다.");
	        obj.focus();
			return true;
		} else {
			return false;
		}
	}	
	/*
	문자열 앞뒤에있는 공백없에기
	*/
	function strTrim( arg )
	{
	   var st = 0;
	   var len = arg.length;

	   //문자열앞에 공백문자가 들어 있는 Index 추출
	   while( (st < len) && (arg.charCodeAt(st) == 32) )
	   {
	      st++;
	   }
	   //문자열뒤에 공백문자가 들어 있는 Index 추출
	   while( (st < len) && (arg.charCodeAt(len-1) == 32) )
	   {
	      len--;
	   }
	   return ((st > 0) || (len < arg.length)) ? arg.substring(st, len) : arg;
	}	


function checkValue(obj, msg) {
	var objvalue = obj.value;
	if (objvalue == "") {
		alert(msg);
		obj.focus();
		return true; 
	} 
	return false;	
}

		