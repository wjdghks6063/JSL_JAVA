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
		pds.method="post";
		pds.action="Pds";
		pds.submit();
		//값이 서블릿으로 넘어감 즉 자기 자신한테.
	}
	
	function goView(no){
		pdsView.t_no.value= no;
		pdsView.t_gubun.value="view";
		pdsView.method="post";
		pdsView.action="Pdsview";
		pdsView.action="Pds";
		pdsView.submit();
	}
	
	function gowriteForm(){
		pdsWirte.t_gubun.value="writeForm";
		pdsWirte.method="post";
	//	notiWirte.action="NoticeWirte";
		pdsWirte.action="Pds";
		pdsWirte.submit();
	}

	function goPage(pageNumber) {
		pageForm.t_nowPage.value = pageNumber;
		pageForm.method="post";
		pageForm.action="Pds";
		pageForm.submit();
	}
	
</script>
	
	<!-- sub contents -->
	
<form name = "pdsWirte">
	<input type="hidden" name="t_id">
	<input type="hidden" name="t_gubun" >
</form>	
<form name = "pdsView">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_gubun" >
</form>
<form name="pageForm">
	<input type="hidden" name="t_nowPage">
	<input type="hidden" name="t_select" value="${t_select}">
	<input type="hidden" name="t_search" value="${t_search}">
</form>
	
	<div class="sub_title">
		<h2>자료실</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="../index.html"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">메인메뉴<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice/notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">자료실<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="Notice">공지사항</a>
						<a href="Qna">질문과답변</a>
						<a href="Faq">FAQ</a>
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
			<form name="pds">
				<select name="t_select" class="select45">
					<option value="a.title" <c:if test="${t_select eq 'a.title' }">selected</c:if>>제목</option>
						<option value="a.content" <c:if test="${t_select eq 'a.content' }">selected</c:if>>내용</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="search_word">
					<button class="btn_search" onclick="goSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		<table class="bord_table" summary="이표는 번호,제목,글쓴이,날자,조회수로 구성되어 있습니다">
			<caption class="sr-only">공지사항 리스트</caption>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="10%">
				<col width="15%">
				<col width="7%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>첨부파일</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${t_dtos}" var="dto">
				<tr>
					<td><a href="Pds?t_gubun=view&t_no=${dto.getNo()}"> ${dto.getNo()} </a></td>
					<td class="title"><a href="javascript:goView('${dto.getNo()}')"> ${dto.getTitle()}</a></td>
					
					<c:if test="${empty dto.getAttach()}"> <!-- null 방식 -->
						<td><i class="fas fa-times"></i></td>
					</c:if>	
					
					<c:if test="${not empty dto.getAttach()}">
						<td><i class="fas fa-file-download"></i></td>
					</c:if>

					<td>${dto.getName()}</td>
					<td>${dto.getReg_date()}</td>
					<td>${dto.getHit()}</td>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<div class="paging">
			<%
				out.print(CommonUtil.pageListPost(current_page, total_page, 5));
			%>
			
			<c:if test="${session_level eq 'top' }"> <!--equals는 eq만 써도 된다. -->
				<a href="javascript:gowriteForm()" class="btn_write">글쓰기</a>
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
