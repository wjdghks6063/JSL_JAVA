<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file ="../common_header.jsp" %>
<%@ page import="common.*" %>
<%
	int current_page = (int)request.getAttribute("t_c_page"); 
	int total_page = (int)request.getAttribute("t_t_page"); 
%>

<script type="text/javascript">

	function goSearch(){
		qna.method="post";
		qna.action="Qna";
		qna.submit();
		//값이 서블릿으로 넘어감 즉 자기 자신한테.
	}
	
	function goView(no){
		qnaView.t_no.value= no;
		qnaView.t_gubun.value="view";
		qnaView.method="post";
		qnaView.action="Noticeview";
		qnaView.action="Qna";
		qnaView.submit();
	}
	
	function gowriteForm(){
		qnaWirte.t_gubun.value="writeForm";
		qnaWirte.method="post";
	//	notiWirte.action="NoticeWirte";
		qnaWirte.action="Qna";
		qnaWirte.submit();
	}

	function goPage(pageNumber) {
		pageForm.t_nowPage.value = pageNumber;
		pageForm.method="post";
		pageForm.action="Qna";
		pageForm.submit();
	}
	
</script>

<!-- sub contents -->
<form name = "qnaWirte">
	<input type="hidden" name="t_id">
	<input type="hidden" name="t_gubun" >
</form>	
<form name = "qnaView">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_gubun" >
</form>
<form name="pageForm">
	<input type="hidden" name="t_nowPage">
	<input type="hidden" name="t_select" value="${t_select}">
	<input type="hidden" name="t_search" value="${t_search}">
</form>
		
	<div class="sub_title">
		<h2>질문과답변</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="Index"><i class="fa fa-home btn_plus"></i></a>
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
					<a href="">질문과답변<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="Notice">공지사항</a>
						<a href="Qna">질문과답변</a>
						<a href="Faq">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="search_wrap">
		<div class="record_group">
			<p>총게시글<span>${t_total_count}</span>건</p>
		</div>
		<div class="search_group">
			<form name="qna">
				<select name="t_select" class="select45">
					<option value="a.q_title" <c:if test="${t_select eq 'a.q_title' }">selected</c:if>>제목</option>
						<option value="b.name" <c:if test="${t_select eq 'b.name' }">selected</c:if>>작성자</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="search_word">
					<button class="btn_search" onclick="goSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		<table class="bord_table" summary="이표는 번호,제목,답변상태, 작성자, 작성일, 조회수로 구성되어 있습니다">
			<caption class="sr-only">질문과 답변 리스트</caption>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="5%">
				<col width="7%">
				<col width="10%">
				<col width="7%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>답변상태</th>
					<th>공개</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${t_dtos}" var="dto">
				<tr>
				
				<c:choose>
					<c:when test="${session_level eq 'top' || dto.getSecret() eq 'n' || dto.getQ_reg_id() eq session_id }">
						<td><a href="Qna?t_gubun=view&t_no=${dto.getNo()}"> ${dto.getNo()} </a></td>
						<td><a href="javascript:goView('${dto.getNo()}')"> ${dto.getQ_title()}</a></td>
					</c:when>
					<c:otherwise>
						<td><span style="color:#BDBDBD">${dto.getNo()}</span></td>
						<td><span style="color:#BDBDBD"><i class="fas fa-lock"></i>&nbsp${dto.getQ_title()}</span></td>
					</c:otherwise>	
				</c:choose>
		
					<c:if test="${empty dto.getA_content()}"> <!-- null 방식 -->
						<td><span class="waiting">답변대기</span></td>
					</c:if>	
					<c:if test="${not empty dto.getA_content()}">
						<td><span class="complet">답변완료</span></td>
					</c:if>
					
					<c:if test="${dto.getSecret() eq 'y'}"> <!-- null 방식 -->
						<td><i class="fas fa-lock"></i></td>
					</c:if>	
					<c:if test="${dto.getSecret() eq 'n'}">
						<td><i class="fas fa-lock-open"></i></td>
					</c:if>
					<td>${dto.getQ_name()}</td>
					<td>${dto.getQ_reg_date()}</td>
					<td>${dto.getHit()}</td>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<div class="paging">
			<%
				out.print(CommonUtil.pageListPost(current_page, total_page, 5));
			%>
			<c:choose>
					<c:when test="${session_id eq null }">
						<a href="Member" class="btn_write" onClick="alert('로그인 후 이용 가능합니다.')">글쓰기</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:gowriteForm()" class="btn_write">글쓰기</a>
					</c:otherwise>	
				</c:choose>
			
				
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
