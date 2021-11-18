<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file ="../common_header.jsp" %>

<script type="text/javascript">
	
	function goView(no,secret){
		qnaView.t_no.value= no;
		qnaView.t_secret.value= secret;
		qnaView.t_gubun.value="view";
		qnaView.method="post";
	//	memberView.action="Noticeview";
		qnaView.action="Qna";
		qnaView.submit();
	}
	
	function goUpdateForm(no) {
		qnaView.t_no.value= no;
		qnaView.t_gubun.value="updateForm";
		qnaView.method="post";
		qnaView.action="Qna";
		qnaView.submit();
	}
	
	function goDelete(no) {
		qnaView.t_no.value= no;
		qnaView.t_gubun.value="delete";
		qnaView.method="post";
		qnaView.action="Qna";
		qnaView.submit();
	}
	
	function goAnswerSave(no) {
		if(checkValue(answer.a_content,"내용을 입력하세요!")) return;
		answer.t_no.value= no;
		answer.t_gubun.value="answerSave";
		answer.method="post";
		answer.action="Qna";
		answer.submit();
	}
	
	function goAnswerDelete(no) {
		qnaView.t_no.value= no;
		qnaView.t_gubun.value="answerDelete";
		qnaView.method="post";
		qnaView.action="Qna";
		qnaView.submit();
	}
	
</script>

	<!-- sub contents -->
<form name = "qnaView">
	<input type="hidden" name="t_no" >
	<input type="hidden" name="t_secret" >
	<input type="hidden" name="t_gubun" >
</form>
	
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
			<h2>${t_dto.getQ_title()}</h2>
			<p class="info"><span class="user">${t_dto.getQ_name()}</span> | ${t_dto.getQ_reg_date()} | <i class="fa fa-eye"></i> ${t_dto.getHit()}</p>
			<div class="board_body">
			<p>${t_dto.getQ_content()}</p>
			</div>
		</div>		
		<c:if test="${not empty t_dto.getA_name() }"> <!-- 공백이 아닌 경우에 출력된다. -->
		<div class="board_view">
			<h2>re : ${t_dto.getQ_title()}</h2>
			<p class="info"><span class="user">${t_dto.getA_name()}</span> | ${t_dto.getA_reg_date()} | </p>
			<div class="board_body">
			<p>${t_dto.getA_content()}</p>
			</div>
		</div>
		
			
		</c:if>	
		
<script type="text/javascript">
//<![CDATA[
$(document).ready(function(){
	$(".answerButt").toggle(function(){
		$(".answerArea").slideDown(500);	
	}, function(){
		$(".answerArea").slideUp(500);
	})
});
//]]>
</script>
<style>
	.answerArea{display:none} 
	.btn_3wrap span {
		padding: 12px 18px;
		display: inline-block;
		border: 1px solid #878787;
	}
	.answerArea .textArea_H120{
		padding:5px;
		width:930px;
		height:120px;
	}	
	.answerArea .saveButt{
		float:right;
		padding: 12px 18px;
		display: inline-block;
		border: 1px solid #878787;
	}	
</style>
				
			<!-- 답변 -->
			<form name="answer">
				<div class="answerArea">
					<input type="hidden" name="t_no">
					<input type="hidden" name="t_gubun">
					<input type="hidden" name="a_reg_id" value="${session_id}">
					<span class="user">${session_id} : ${session_name}</span>
					<textarea name="a_content" class="textArea_H120" style="resize:none;"></textarea>
					<a href="javascript:goAnswerSave('${t_dto.getNo()}')" class="saveButt">저장</a>
				</div>
			</form>
								
			</div>
			<div class="menu_body">
				<div class="prev_next">
				
				<!-- 이전 페이지 버튼 -->
				<c:if test="${t_back.size() > 0 }">
				<c:choose>
					<c:when test="${session_level eq 'top' || t_back.get(2) eq 'n' || t_back.get(3) eq session_id }">
						<a href="javascript:goView('${t_back.get(0)}','${t_back.get(2)}')" class="btn_prev"> 
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
					</c:when>
					<c:otherwise>
						<a href="Qna" class="btn_prev" onClick="alert('비공개 질문입니다.')"><i class="fas fa-lock"></i>
							<span class="prev_wrap">
								<strong>이전글</strong><span> 비공개 질문</span>
							</span>
						</a>
					</c:otherwise>	
				</c:choose>
				</c:if>
				<!--  목록,수정,삭제 버튼    -->
					<div class="btn_3wrap">
						<a href="Qna">목록</a> 
						
						<c:if test="${t_dto.getQ_reg_id() eq session_id && not empty t_dto.getA_content()}">
							<a style="cursor:pointer" onClick="alert('답변이 처리되어 수정 하실 수 없습니다.')">수정</a>  
						</c:if>

						<c:if test="${t_dto.getQ_reg_id() eq session_id && empty t_dto.getA_content()}">
							<a href="javascript:goUpdateForm('${t_dto.getNo()}')">수정</a>  
						</c:if>
						
						<c:if test="${session_level eq 'top' || t_dto.getQ_reg_id() eq session_id }">
							<a href="javascript:goDelete('${t_dto.getNo()}')" onClick="return confirm('정말로 삭제하시겠습니까?')">삭제</a> 
						</c:if>
						
						<c:if test="${session_level eq 'top' && empty t_dto.getA_content() }">
							<span class="answerButt" style="cursor:pointer">답변</span>
						</c:if>
						
						<c:if test="${session_level eq 'top' && not empty t_dto.getA_content() }">
							<span class="answerButt" style="cursor:pointer">답변 수정</span>
							<a href="javascript:goAnswerDelete('${t_dto.getNo()}')">답변 삭제</a>
						</c:if>
					</div>
					
				<!-- 다음 페이지 버튼 -->	
				
				<c:if test="${t_go.size() > 0 }">
					<c:choose>
					<c:when test="${session_level eq 'top' || t_go.get(2) eq 'n' || t_go.get(3) eq session_id }">
						<a href="javascript:goView('${t_go.get(0)}','${t_go.get(2)}')" class="btn_next">
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
							<i class="fa fa-angle-right"></i>
						</a>
					</c:when>
					<c:otherwise>
						<a href="Qna" class="btn_next" onClick="alert('비공개 질문입니다.')">
							<span class="next_wrap">
								<strong>다음글</strong><span> 비공개 질문</span>
							</span>
							<i class="fas fa-lock"></i>
						</a>
					</c:otherwise>	
				</c:choose>
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

