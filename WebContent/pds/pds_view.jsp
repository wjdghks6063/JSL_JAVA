<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file ="../common_header.jsp" %>

<script type="text/javascript">
	
	function goView(no){
		pdsView.t_no.value= no;
		pdsView.t_gubun.value="view";
		pdsView.method="post";
		pdsView.action="Pds";
		pdsView.submit();
	}
	
	function goUpdateForm(no) {
		pdsView.t_no.value= no;
		pdsView.t_gubun.value="updateForm";
		pdsView.method="post";
		pdsView.action="Pds";
		pdsView.submit();
	}
	
	function goDelete(no) {
		pdsView.t_no.value= no;
		pdsView.t_gubun.value="delete";
		pdsView.method="post";
		pdsView.action="Pds";
		pdsView.submit();
	}
	
</script>

	<!-- sub contents -->
<form name = "pdsView">
	<input type="hidden" name="t_no" >
	<input type="hidden" name="t_gubun" >
	<input type ="hidden" name="t_attach" value="${t_dto.getAttach()}"">
</form>
	
	<div class="sub_title">
		<h2>자료실</h2>
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
						<a href="gratings.html">질문답변</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
		<div class="board_view">
			<h2>${t_dto.getTitle()}</h2>
			<p class="info"><span class="user">${t_dto.getName()}</span> | ${t_dto.getReg_date()} | <i class="fa fa-eye"></i> ${t_dto.getHit()}</p>
			<div class="board_pds">
				<c:choose>
				 	<c:when test="${not empty t_dto.getAttach()}"> <!-- 글자가 5글자 이상이라면  ..으로 표시-->
				 		첨부파일 : <i class="fas fa-file-download"></i>
				 				<a href="FileDown?t_fileDir=pds&t_file=${t_dto.getAttach()}&t_backURL=pds">${t_dto.getAttach()}</a>
				 	</c:when>
				 	<c:otherwise>
				 		첨부파일 : <a><i class="fas fa-times"></i></a>
				 	</c:otherwise>
			 	</c:choose>
			</div>
			<div class="board_body">
				${t_dto.getContent()}
			</div>
			
			<div class="prev_next">
				<!-- 이전 페이지 버튼 -->
			<c:if test="${t_back.size() > 0 }">
				<a href="javascript:goView('${t_back.get(0)}')" class="btn_prev">
				<i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
					<strong>이전글</strong>
					<span>
						<c:choose>
						 	<c:when test="${fn:length(t_back.get(1)) > 5 }"> <!-- 글자가 5글자 이상이라면  ..으로 표시-->
						 		${fn:substring(t_back.get(1),0,5)}....
						 	</c:when>
						 	<c:otherwise>
						 		${t_back.get(1)} <!-- 글자가 5글자 이하라면 그대로 표시 -->
						 	</c:otherwise>
						 </c:choose>
					</span>
				</span>
				</a>
			</c:if>
				<!-- 목록,수정,삭제 버튼 -->
				<div class="btn_3wrap">
					<a href="Pds">목록</a> 
					<c:if test="${session_level eq 'top' }"> <!--equals는 eq만 써도 된다. -->
					<a href="javascript:goUpdateForm('${t_dto.getNo()}')">수정</a> 
					<a href="javascript:goDelete('${t_dto.getNo()}')" onClick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					</c:if>
				</div>
				
				<!-- 다음페이지 버튼 -->
			<c:if test="${t_go.size() > 0 }">
				<a href="javascript:goView('${t_go.get(0)}')" class="btn_next">
				<span class="next_wrap">
					<strong>다음글</strong>
					<span>
						<c:choose>
						 	<c:when test="${fn:length(t_go.get(1)) > 5 }"> <!-- 글자가 5글자 이상이라면  ..으로 표시-->
						 		${fn:substring(t_go.get(1),0,5)}....
						 	</c:when>
						 	<c:otherwise>
						 		${t_go.get(1)} <!-- 글자가 5글자 이하라면 그대로 표시 -->
						 	</c:otherwise>
						 </c:choose>
					</span>
				</span>
				<i class="fa fa-angle-right"></i></a>
			</c:if>
			
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

	<%@ include file ="../common_footer.jsp" %>

 </body>
</html>
