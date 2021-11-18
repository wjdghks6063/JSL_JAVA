<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file ="../common_header.jsp" %>

<script type="text/javascript">

	function goSave(){
		if(checkValue(qna.q_title,"제목을 입력하세요!")) return;
		if(checkValue(qna.q_content,"내용을 입력하세요!")) return;
		qna.t_gubun.value="save";
		qna.method="post";
		qna.action="Qna";
		qna.submit();
	}
	
</script>
    
<!-- sub contents -->

	<div class="sub_title">
		<h2>질문답변</h2>
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
					<a href="">질문답변<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="gratings.html">질문과답변</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">질문과 답변 글쓰기</h2>
	  <form name="qna">
	  	<input type ="hidden" name="t_gubun">
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
			<table class="bord_table">
				<caption class="sr-only">공지사항 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>글쓴이</th>
						<td>${session_name}
							<input type="hidden" name="q_name" value="${session_name}">
							<input type="hidden" name="q_reg_id" value="${session_id}">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="q_title"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea style="resize:none;" name="q_content"></textarea></td>
					</tr>
					<tr>
						<th>작성 일자</th>
						<td>${CommonUtil.getToday()}
							<input type="hidden" name="q_reg_date" value="${CommonUtil.getToday()}">
						</td>
					</tr>
					<tr>
						<th>공개 여부</th>
						<td >
							<i class="fas fa-lock-open"></i> &nbsp
							<div class="toggle">
						    <input type="checkbox" name="q_secret" id="toggle1" value="y">
						    <label for="toggle1"></label>
							</div>
							&nbsp <i class="fas fa-lock"></i>
						</td>
					</tr>
					
				</tbody>
			</table>
			<div class="btn_wrap">
				<input type="button" name="" onClick="javascript:goSave()" value="등록" class="btn_ok" style="cursor:pointer">&nbsp;&nbsp;
				<input type="button" value="취소" class="btn_list" onClick="location.href='Qna'" style="cursor:pointer">
			</div>
		</form>
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
	
	<%@ include file ="../common_footer.jsp" %>

 </body>
</html>
