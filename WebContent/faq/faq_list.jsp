<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file ="../common_header.jsp" %>
<%@ page import="common.*" %>
<link href="/css/font-awesome.min.css" rel="stylesheet">
<%
	int current_page = (int)request.getAttribute("t_c_page"); 
	int total_page = (int)request.getAttribute("t_t_page"); 
%>

<script type="text/javascript">
	function goSearch(){
		faq_search.method="post";
		faq_search.action="Faq";
		faq_search.submit();
		//값이 서블릿으로 넘어감 즉 자기 자신한테.
	}
	
	function gowriteForm(){
		faqgubun.t_gubun.value="writeForm";
		faqgubun.method="post";
		faqgubun.action="Faq";
		faqgubun.submit();
	}


	function goPage(pageNumber) {
		pageForm.t_nowPage.value = pageNumber;
		pageForm.method="post";
		pageForm.action="Faq";
		pageForm.submit();
	}
	
	function goUpdateForm(no) {
		faqgubun.t_no.value= no;
		faqgubun.t_gubun.value="updateForm";
		faqgubun.method="post";
		faqgubun.action="Faq";
		faqgubun.submit();
	}
	
	function goDelete(no) {
		if(confirm( "삭제하시겠습니까?" ) == true){
			faqgubun.t_no.value= no;
			faqgubun.t_gubun.value="delete";
			faqgubun.method="post";
			faqgubun.action="Faq";
			faqgubun.submit();
		}else {
			return false;
		}
	}
			
			
	
</script>
    
	<!-- sub contents -->
	
<form name = "faqgubun">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_gubun" >
</form>

<form name="pageForm">
	<input type="hidden" name="t_nowPage">
	<input type="hidden" name="t_select" value="${t_select}">
	<input type="hidden" name="t_search" value="${t_search}">
</form>	

	<div class="sub_title">
		<h2>자주하는 질문(FAQ)</h2>
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
					<a href="">자주하는질문<i class="fa fa-plus btn_plus"></i></a>
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
	  <div class="search_wrap">
		<div class="record_group">
			<p>총게시글<span>${t_total_count}</span>건</p>
		</div>
		<div class="search_group">
			<form name="faq_search">
				<select name="t_select" class="select45">
					<option value="a.title" <c:if test="${t_select eq 'a.title' }">selected</c:if>>제목</option>
						<option value="a.content" <c:if test="${t_select eq 'a.content' }">selected</c:if>>답변</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="search_word">
					<button class="btn_search" onclick="goSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		
		<div class="faq-group">
			<c:forEach items="${t_dtos}" var="dto">
				<div class="accordion">
					<table class="table">
						<colgroup>
							<col width="10%">
							<col width="*">
							<col width="10%">
							<col width="16%">
						</colgroup>
							<tr>
								<td>${dto.getNo()}</td>
								<td>${dto.getTitle()}</td>
								<td>${dto.getName()}</td>
								<td>${dto.getReg_date()}</td>
							</tr>
					</table>
				</div>
				<div class="panel">
					<textarea class="textArea_H120" name="t_content" style="resize:none;" readonly>${dto.getContent()}</textarea>
						<c:if test="${session_level eq 'top' }">
							<input type="button" name="updatebtn" onClick="javascript:goUpdateForm('${dto.getNo()}')" value="수정" class="updatebtn" style="cursor:pointer">&nbsp;&nbsp;
							<input type="button" name="deletebutt" value="삭제" class="btn_list" onClick="javascript:goDelete('${dto.getNo()}')"  style="cursor:pointer">
						</c:if>
				</div>
			</c:forEach>	
		</div>

<style>
	.textArea_H120{
		padding:5px;
		width:830px;
		height:120px;
	}
	.updatebtn {
		margin-bottom:1px;
	}	
</style>

		<script>
			$(function() {
/*			
				$( '.accordion' ).click( function() {
				//$(".accordion").on("click",function() {	
					//$(".panel").slideUp();
					//$(this).next().slideToggle();
					//$(this).next().slideDown();
					$(".panel").not($(this).next().slideToggle()).slideUp();
					//$(this).next().slideDown();
					

				} );
		*/			
			
				$(".accordion").on("click",function() {
					$(".panel").not($(this).next().slideToggle()).slideUp();
					$(".accordion").not($(this)).removeClass("active");
					$(this).toggleClass("active");
				});
		
			});
		</script>

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
	