<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file ="../common_header.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">

	function goSave(){
		
		//첨부 파일검사
		var fileName = pds.t_attach.value; //벨류 검사
		if(fileName !=""){
			var pathFileName = fileName.lastIndexOf(".")+1;    //확장자 제외한 경로+파일명
			var extension = (fileName.substr(pathFileName)).toLowerCase();	//확장자명
			//파일명.확장자
			if(extension != "jpg" && extension != "gif" && extension != "png"){
				alert(extension +" 형식 파일은 업로드 안됩니다. 이미지 파일만 가능!");
				return;
			}		
		}	
		// 첨부 용량 체크

		var file = pds.t_attach; // form.첨부파일;
		var fileMaxSize  = 5; //첨부 최대 용량 설정 ?mb로 설정
		if(file.value !=""){
			// 사이즈체크
			var maxSize  = 1024 * 1024 * fileMaxSize;  // 1kb * 1kb = 1mb * 숫자 로 크기 조절
			var fileSize = 0;
		
			// 브라우저 확인
			var browser=navigator.appName;
			// 익스플로러일 경우
			if (browser=="Microsoft Internet Explorer"){
				var oas = new ActiveXObject("Scripting.FileSystemObject");
				fileSize = oas.getFile(file.value).size;
			}else {
			// 익스플로러가 아닐경우
				fileSize = file.files[0].size;
			}
			//alert("파일사이즈 : "+ fileSize);
		
			if(fileSize > maxSize){
				alert(" 첨부파일 사이즈는 "+fileMaxSize+"MB 이내로 등록 가능합니다. ");
				return;
			}	
		}		
		
		
		if(checkValue(pds.t_title,"제목을 입력하세요!")) return;
		if(checkValue(pds.t_content,"내용을 입력하세요!")) return;
//		pds.t_gubun.value="save"; //request.getparameter로 받을수 없다. mpr로 받기 때문에 //해결 방법은 저번에 했던것처럼 saveattach로 따로 받던가 ?로 붙여서 보내준다.
		pds.method="post";
		pds.action="Pds?t_gubun=save"; //?로 붙여서 보내주면 request.getparameter로 받을수 있다.
		pds.submit();
	}
	

</script>	
	<!-- sub contents -->
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
					<a href="">공지사항<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="gratings.html">DW인터뷰</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">자료실 글쓰기</h2>
	  <form name="pds" enctype="multipart/form-data">
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
							<input type="hidden" name="t_reg_id" value="${session_id}">
						</td>	
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="t_title"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="t_content"></textarea></td>
					</tr>
					<tr>
						<th>첨부</th>
						<td><input type="file" name="t_attach"></td>
					</tr>
					<tr>
						<th>작성 일자</th>
						<td>${CommonUtil.getToday()}
							<input type="hidden" name="t_reg_date" value="${CommonUtil.getToday()}">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_wrap">
				<input type="button" name="" onClick="javascript:goSave()" value="등록" class="btn_ok" style="cursor:pointer">&nbsp;&nbsp;
				<input type="button" value="취소" class="btn_list" onClick="location.href='Pds'" style="cursor:pointer">
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
