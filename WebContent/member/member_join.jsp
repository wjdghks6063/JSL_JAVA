<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="../common_header.jsp" %>	
	<!-- sub contents -->
<script type="text/javascript">
	function idCheck(){ //글자수가 입력될때마다 아이디 중복검사 및 그림으로 표시
		// 아이디 글자수마다 검사할때
		var len = mem.t_id.value.length; // mem.t_id.value에 글자길이도 포함
		if(len > 2) { //? 글자보다 크면 ?+1 숫자 //아이디 생성시 최소 글자수 제한
				check();	
		} else {  //글자가 3자 이하일때 
			$("#idResult").html("");
			mem.idCheck_yn.value ="no"; //사용 가능여부
			mem.idCheck_value.value =""; // 아이디
		}
	}
	
	function check() {
		var urlLocation="MemberIdCheck"; //member servlet 요청 
	    var id = mem.t_id.value;
	    var params="t_id="+id;  // id값의 변수를 넘긴다.
	    
		$.ajax({
			type : "POST",
			url : urlLocation, // "Member" 직접 값을 넣어도 된다.
			data: params, // "t_gubun=idCheck&t_id="+id; 직접 값을 넣어도 된다.
			dataType : "text",
			error : function(){
				alert('통신실패!!');
			},
			success : function(data){
			/*alert("통신 데이터 값" + data);
			  $("#idResult").html(data);
				
				아래의 idcheck 부분 정보 
			<input type="text" name="t_id" id="mbrId" maxlength="14" class="w200">
			<a id="checkID" class="btn_write" style="cursor:pointer"><b>ID중복검사</b></a>
			<span id = "idResult"></span> //글자색
			<input type ="hidden" name="idCheck_yn"> //사용 가능여부
			<input type ="hidden" name="idCheck_value"> // 아이디
			*/
			 	
				if($.trim(data) =="이미 사용중인 ID입니다."){ //date의 글자는 MemberidCheck의 msg ="이미 사용중인 ID입니다."와 같아야 적용된다.
					$("#idResult").html("<i class='far fa-times-circle'></i>");
				//	$("#idResult").css("color","red"); //글자색
					mem.idCheck_yn.value ="no"; //사용 가능여부
					mem.idCheck_value.value =""; // 아이디
				} else {
					$("#idResult").html("<i class='fas fa-check'></i>");
				//	$("#idResult").css("color","blue");
					mem.idCheck_yn.value ="yes";
					mem.idCheck_value.value =id;
				}	 
			}
		});
	}
</script>  

  
<script type="text/javascript" language="javascript"> //닉네임 중복 버튼 및 글자로 표시
    $(document).ready(function(){ 
    	$("#checkID").click(function(){ 
    	    var urlLocation="MemberIdCheck"; //member servlet 요청 
    	    var id = mem.t_id.value;
    	    var params="t_id="+id;  // id값의 변수를 넘긴다.
    	    
    	 	   if(id ==""){
    	    	alert("ID를 입력해 주세요.");
    	    	mem.t_id.focus();
    	    	return;
    	    }
    	    
			$.ajax({
				type : "POST",
				url : urlLocation, // "Member" 직접 값을 넣어도 된다.
				data: params, // "t_gubun=idCheck&t_id="+id; 직접 값을 넣어도 된다.
				dataType : "text",
				error : function(){
					alert('통신실패!!');
				},
				success : function(data){
				/*	alert("통신 데이터 값" + data);
					
					아래의 idcheck 부분 정보 
				<input type="text" name="t_id" id="mbrId" maxlength="14" class="w200">
				<a id="checkID" class="btn_write" style="cursor:pointer"><b>ID중복검사</b></a>
				<span id = "idResult"></span> //글자색
				<input type ="hidden" name="idCheck_yn"> //사용 가능여부
				<input type ="hidden" name="idCheck_value"> // 아이디
				*/
				
				 	$("#idResult").html(data);
					if($.trim(data) =="이미 사용중인 ID입니다."){ //date의 글자는 MemberidCheck의 msg ="이미 사용중인 ID입니다."와 같아야 적용된다.
						$("#idResult").css("color","red"); //글자색
						mem.idCheck_yn.value ="no"; //사용 가능여부
						mem.idCheck_value.value =""; // 아이디
					} else {
						$("#idResult").css("color","blue");
						mem.idCheck_yn.value ="yes";
						mem.idCheck_value.value =id;
					}	 
				}
			});
    	});
    });
</script>  
  
<script type="text/javascript">
	function emailChange() {
		var sel = mem.t_email_select.value;
		if(sel != ""){
			mem.t_email_2.value = sel;
		} else {
			mem.t_email_2.value = "";
			mem.t_email_2.focus();
		}
	}

	function goSave() {
		if(inputCheck_empty_length(mem.t_name,"성명을 입력해주세요.",20)) return; /*input 상자위치,input상자에  띄울 메세지,테이블 자릿수 제한*/ 
		//if(checkValue(mem.t_name,"성명을 입력해주세요.")) return;
		
		if(checkValue(mem.t_id,"ID를 입력해주세요.")) return;
		
		if(mem.t_id.value.length < 3){
			alert("ID는 3글자부터 생성 가능합니다.")
			mem.t_id.focus();
			return;
		}
/*		
		if(mem.t_id.value != mem.idCheck_value.value){
			alert("아이디 중복여부를 확인해 주세요");
			mem.t_id.focus();
			return;
		}	
*/			
		if(mem.idCheck_yn.value=="no"){
			alert("사용 불가 ID 입니다.");
			mem.t_id.focus();
			return;
		}
		
		if(checkValue(mem.t_pw_1,"비밀번호를 입력해주세요.")) return;
		if(checkValue(mem.t_pw_2,"비밀번호 확인을 입력해주세요.")) return;
		if(mem.t_pw_1.value != mem.t_pw_2.value){
			alert("비밀번호가 일치하지 않습니다.");
			mem.t_pw_2.focus();
			return;
		}
		if(checkValue(mem.t_belong,"소속을 선택해 주세요.")) return;
		if(checkValue(mem.t_tell_1,"휴대전화를 입력해 주세요.")) return;
		if(checkValue(mem.t_tell_2,"휴대전화를 입력해 주세요.")) return;
		if(checkValue(mem.t_tell_3,"휴대전화를 입력해 주세요.")) return;
		if(checkValue(mem.t_email_1,"e_mail을 입력해 주세요.")) return;
		if(checkValue(mem.t_email_2,"e_mail을 입력해 주세요.")) return;
		
		mem.method="post";
		mem.action="Member";
		mem.submit();
	 
}
</script>	
	
	<div class="sub_title">
		<h2>회원가입</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="index.html"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">공지사항<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="notice.html">공지사항</a>
						<a href="qa.html">질문과답변</a>
						<a href="faq.html">FAQ</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>


<form name ="mem">
	<input type="hidden" name="t_gubun" value="memberSave">
	<div class="container">
		<div class="con_title">
            <h1>내정보(개인회원)</h1>
         <p>HOME / 마이페이지 / 내정보(개인회원)</p>
        </div>
		<div class="join_write col_989">
                <div class="list_con">
                    <ul class="icon_type1">
                        <li>회원정보는 개인정보 취급방침에 따라 안전하게 보호되며 회원님의 명백한 동의 없이 공개 또는 제3자에게 제공되지 않습니다.</li> <br>
                        <li><a style="color: red">필수 입력 정보란에는 *가 표시되어 있습니다.</a></li>
                    </ul>
                </div>
            <table class="table_write02" summary="회원가입을 위한 이름, 아이디, 비밀번호, 비밀번호확인, 소속, 유선전화번호, 휴대전화번호, 이메일, 주소, 본인확인질문, 본인확인답, 주활용사이트, 알림여부 정보 입력">
                <caption>회원가입을 위한 정보입력표</caption>
                <colgroup>
                    <col width="160px">
                    <col width="auto">
                </colgroup>
                <tbody id="joinDataBody">
                    <tr>
                        <th><label for="name">* 이름</label></th>
                        <td>
                            <input type="text" name="t_name" id="mbrName" maxlength="20" class="w150">
                        </td>
                    </tr>
                    <tr>
                        <th><label for="id">* 아이디<span class="must"></span></label></th>
                        <td>
							<!-- onkeyup은 글자가 한글자씩 눌릴때마다 이벤트가 발생함  idCheck()를 쓰면 한글자씩 입력할떄마다 funtion이 실행됨-->    
                            <input type="text" onkeyup="idCheck()" name="t_id" id="mbrId" maxlength="14" class="w200">
						<!-- 	<a id="checkID" class="btn_write" style="cursor:pointer"><b>ID중복검사</b></a>
						 -->
							<span id = "idResult"></span>
							<input type ="hidden" name="idCheck_yn">
							<input type ="hidden" name="idCheck_value">
							 <p class="guideTxt">아이디는 3글자 이상부터 생성 가능합니다.</p>
						</td>
                    </tr>
                    <tr>
                        <th><label for="pw">* 비밀번호<!-- <span class="must"><b>필수입력</b></span> --></label></th>
                        <td>
                            <input type="password" name="t_pw_1" id="scrtNo" maxlength="14" class="w200">
                            <p class="guideTxt"><span class="tc_point"></p>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="pw_confirm">* 비밀번호확인<!-- <span class="must"><b>필수입력</b></span> --></label></th>
                        <td>
                            <input type="password" name="t_pw_2" id="scrtNoConfirm" maxlength="14" class="w200">
                            <p class="guideTxt"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>* 소속<span class="must"></span></th>
                        <td>
                            <label for="mbrClCd" class="blind">소속1차 카테고리 선택</label>
                            <select name="t_belong" id="mbrClCd">
                                <option value="">선택</option>
                                    <option value="1">기업</option>
                                    <option value="2">교수자</option>
                                    <option value="3">미취업자</option>
                                    <option value="4">기타</option>
                                
                            </select>
							<!--
                            <ul id="posCdData" style="display: none;">
                            
                                <li id="posCdVal" poscd="10" posname="협회" postype="01">
                            
                                </li><li id="posCdVal" poscd="11" posname="단체" postype="01">
                            
                                </li><li id="posCdVal" poscd="12" posname="일반기업" postype="01">
                            
                                </li><li id="posCdVal" poscd="20" posname="전문대학" postype="02">
                            
                                </li><li id="posCdVal" poscd="21" posname="폴리텍대학" postype="02">
                            
                                </li><li id="posCdVal" poscd="22" posname="4년제대학" postype="02">
                            
                                </li><li id="posCdVal" poscd="23" posname="일반고" postype="02">
                            
                                </li><li id="posCdVal" poscd="24" posname="마이스터고" postype="02">
                            
                                </li><li id="posCdVal" poscd="25" posname="특성화고" postype="02">
                            
                                </li><li id="posCdVal" poscd="26" posname="직업훈련기관" postype="02">
                            
                                </li><li id="posCdVal" poscd="30" posname="학생" postype="03">
                            
                                </li><li id="posCdVal" poscd="31" posname="일반인" postype="03">
                            
                                </li><li id="posCdVal" poscd="40" posname="공공기관" postype="04">
                            
                                </li><li id="posCdVal" poscd="41" posname="공공단체" postype="04">
                            
                                </li><li id="posCdVal" poscd="42" posname="소속기관" postype="04">
                            
                            </li></ul>
                            <label for="posCd" class="blind">소속2차 카테고리 선택</label>
                            <select name="posCd" id="posCd">
                                
                            <option value="">선택</option><option value="10">협회</option><option value="11">단체</option><option value="12">일반기업</option></select>
                            <input type="text" name="posName" id="posName" class="w200" value=""><label for="belong" class="blind">소속입력</label>
							-->
                            <p class="guideTxt">학생 신분은 '미취업자-학생' 소속으로 선택해주십시오.</p>
                        </td>
                    </tr>
                    <tr>
                        <th>&nbsp;&nbsp;유선전화</th>
                        <td>
                            <label for="phone_number1" class="blind">유선전화 앞번호 선택</label>
                            <select name="t_line_1" id="telNo1" class="input.w100">
                                	<option value="" selected>선택</option>
                                    <option value="02">02</option>
                                    <option value="042">042</option>
                                    <option value="051">051</option>
                                    <option value="061">061</option>
                                    <option value="070">070</option>
                            </select>
                            <input type="text" name="t_line_2" id="telNo2" class="w100" value="" maxlength="3"><label for="phone_number2" class="blind">중간번호</label>
                            <input type="text" name="t_line_3" id="telNo3" class="w100" value="" maxlength="4"><label for="phone_number3" class="blind">마직막번호</label>
                        </td>
                    </tr>
                    <tr>
                        <th>* 휴대전화<span class="must"></th>
                        <td>
                            <label for="mphonNo1" class="blind">휴대전화 앞번호 선택</label>
                            <select name="t_tell_1" id="mphonNo1" class="input.w100">
                            		<option value="" selected>선택</option>
                                    <option value="010">010</option>
                                    <option value="011">011</option>
                            </select>
                            <input type="text" name="t_tell_2" id="mphonNo2" class="w100" value="" maxlength="4"><label for="mphonNo2" class="blind">중간번호</label>
                            <input type="text" name="t_tell_3" id="mphonNo3" class="w100" value="" maxlength="4"><label for="mphonNo3" class="blind">마직막번호</label>
                        </td>
                    </tr> 
                    <tr>
                        <th><label for="email">* 이메일</label></th>
                        <td>
                            <input type="email" name="t_email_1" id="email" class="w150" >
                        @
                        	 <input type="email" name="t_email_2" id="email" class="w150" >
                        	<select name="t_email_select" onchange="emailChange()">
                        		<option value="" selected>직접 입력</option>
                        		<option value="naver.com">naver.com</option>
                        		<option value="daum.com">daum.com</option>
                        		<option value="nate.com">nate.com</option>
                        		<option value="google.com">google.com</option>
                        	</select>
                        </td>
                    </tr>
                    <!-- <tr>
                        <th>주소<span class="must"><b>필수입력</b></span></th>
                        <td>
                            <input type="text" name="zipNo" id="zipNo" disabled="disabled" placeholder="우편번호" class="w120" value="34839"><label for="zipNo" class="blind">우편번호</label>
                            <a id="btn_findJuso" href="javascript:gfn_com_openJusoPopup('fn_jusoCallBack');" class="btn_form btn_DeepGray">주소검색</a>
                            <hr>
                            <input type="text" name="addr" id="addr" disabled="disabled" placeholder="주소" class="w550" value="대전 중구 선화동 1∼66"><label for="addr" class="blind">주소</label>
                            <hr>
                            <input type="text" name="detlAddr" id="detlAddr" placeholder="상세주소" class="w550" value="32-5 대우직업전문학교"><label for="detlAddr" class="blind">상세주소</label>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="myslfConfQuest">본인확인질문</label></th>
                        <td>
                            <select name="myslfConfQuest" id="myslfConfQuest">
                                <option value="">선택</option>
                                
                                    <option value="01">가장 기억에 남는 장소는?</option>
                                
                                    <option value="02">나의 좌우명은?</option>
                                
                                    <option value="03">나의 보물 제1호는?</option>
                                
                                    <option value="06">오래도록 기억하고 싶은 날짜는?</option>
                                
                                    <option value="11">내가 존경하는 인물은?</option>
                                
                                    <option value="12">다시 태어나면 되고 싶은 것은?</option>
                                
                                    <option value="15">내 휴대폰 1번에 등록된 사람은?</option>
                                
                                    <option value="16">나의 출신 초등학교는?</option>
                                
                                    <option value="17">우리집 애완동물의 이름은?</option>
                                
                                    <option value="20">좋아하는 스포츠 팀 이름은?</option>
                                
                                    <option value="21">본인의 출생지는 ?</option>
                                
                            </select>
                        </td>
                    </tr>
                    <tr> 
                        <th><label for="myslfConfAns">본인확인답</label></th>
                        <td>
                            <input type="text" name="myslfConfAns" id="myslfConfAns" class="w300" value="예수님">
                        </td>
                    </tr>
                    <tr>
                        <th>SNS계정연동</th>
                        <td>
	                    	<ul class="join_sns">
			                   <li class="naver">
			                    	<input type="hidden" id="naverYn" value="">
			                        <div id="naver_id_login" class="blind"><a href="https://nid.naver.com/oauth2.0/authorize?response_type=token&amp;client_id=KHOG8V_PSvFjJoo7WpGL&amp;redirect_uri=https%3A%2F%2Fwww.ncs.go.kr%2Flogin%2FloginNaverCallBack.do&amp;state=" onclick="window.open(this.href, 'naverloginpop', 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550'); return false" id="naver_id_login_anchor"><img src="https://static.nid.naver.com/oauth/button_g.PNG" title="네이버 아이디로 로그인" width="40" height="40" border="0"></a> </div>
			                    	<button onclick="fn_loginNaver()" class="btnGet">네이버 연동</button> <!-- ◀ 연동된 계정은 class에 On 추가해주세요 
			                    	  ◀ 연동된 계정에만 연동해제 버튼이 생겨요 
			                    </li>
			                    <li class="kakao">
			                    	<input type="hidden" id="kakaoYn" value="">
			                        <a id="kakao_id_login" class="blind" href="javascript:;"></a>
			                    	<button onclick="fn_kakaoLogin()" class="btnGet">카카오톡 연동</button>
			                    	 
		                    	</li>
			                    <li class="face">
			                    	<input type="hidden" id="faceYn" value="">
			                    	<button onclick="fn_facebookLogin()" class="btnGet">페이스북 연동</button>
			                    	 
		                    	</li>
			                </ul>
                        </td>
                    </tr>
                    <tr>
                        <th>주활용사이트</th>
                        <td>
                        <input type="hidden" name="siteGbn" id="siteGbn" value="">
                        
                            <input type="radio" name="siteGbnChk" id="siteGbn01" chkvalue="01"><label for="siteGbn01">능력중심채용</label>
                        
                            <input type="radio" name="siteGbnChk" id="siteGbn02" chkvalue="02"><label for="siteGbn02">기업활용</label>
                        
                            <input type="radio" name="siteGbnChk" id="siteGbn03" chkvalue="03"><label for="siteGbn03">교육·훈련설계</label>
                        
                        </td>
                    </tr>
                    <tr>
                        <th>알림여부</th>
                        <td>
                            <input type="hidden" name="alarmAgreeYn" id="alarmAgreeYn" value="N">
                            <input type="checkbox" name="alarmAgree" id="alarmAgree"><label for="alarmAgree">NCS 홈페이지에서 제공하는 SMS, 카톡알림 등의 서비스 이용에 동의합니다.</label>
                        </td>
                    </tr>
                </tbody> -->
            </table>
        </div>
	</div>
</form>	
	<!-- end contents -->
	
	<div class="btnArea Acenter pt60 pb100">
        <a href="javascript:history.go(-1);" class="btn_round btn_large btn_BlueGray w180"><b>취소</b></a>
        <a href="javascript:goSave();" class="btn_round btn_large btn_pointColor w180"><b>확인</b></a>
    </div>
	
	
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
	

<footer class="footer">
		<div class="container clearfix">
			<address class="address">
				<p class="title">본사</p>
				(우)12345 대전광역시 중구 계룡로 825 (용두동, 희영빌딩) 5층,6층/고객센터: 042-242-4412 	사업자등록번호: 305-86-06709
			</address>
			<p class="copyright">Copyright &copy JSL 인재개발원주식회사. All rights reserved.</p>
		</div>
</footer>

 </body>
</html>

