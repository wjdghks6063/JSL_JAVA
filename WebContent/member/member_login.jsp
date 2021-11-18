<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="../common_header.jsp" %>
	
<script type="text/javascript">
	function goLogin() {
		if(checkValue(mem.t_id,"ID를 입력해주세요.")) return;
	if(checkValue(mem.t_pw,"비밀번호를 입력해주세요.")) return;
	mem.method="post";
	mem.action="Member";
	mem.submit();
	}
	
	function check(){ //엔터를 치면 아이와 비밀번호창에 정보가 입력되어있다면 로그인을 실행시킨다.
	var k = event.keyCode;
	if(k==13){
		if(checkValue(mem.t_id,"아이디를 입력하시오!")) return; //아이디를 입력하지 않았다면 아이디 입력후 시도하라는 메세지
		mem.t_pw.focus(); //아이디를 입력한 상태라면 비밀번호창으로 이동
		}
	}
</script>	
  
	<!-- sub contents -->
	<div class="sub_title">
		<h2>접속자 로그인</h2>
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

	<div class="container">
			<div class="member_boxL">
                <h2>개인회원</h2>
                <div class="login_form">
                    <form name="mem" id="frmLogin">
						<input type="hidden" name="t_gubun" value="login"> <!-- onkeypress check로 엔터입력으로 로그인이 가능하게 해준다. -->
	                    <div class="fl_clear"><label for="mbrId">아이디</label><input name="t_id" id="mbrId" type="text" onkeypress="check()" autofocus></div>
	                    <div class="fl_clear"><label for="scrtNo">비밀번호</label><input name="t_pw" id="scrtNo" type="password" onkeypress="if(event.keyCode==13){goLogin()}" ></div>
	                    <a class="btn_login btn_Blue" href="javascript:goLogin();">로그인</a>
                    </form>
                </div>
            </div>
	</div>
	<!-- end contents -->
	
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
