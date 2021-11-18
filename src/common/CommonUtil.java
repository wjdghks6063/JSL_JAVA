package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	//첨부파일 스낵
	public static String getFile_dir_snack() {	//주소변경	
		return "C:/Users/JSL505-014/Desktop/work_LJH/java_project/servlet_mvc_exe/WebContent/file_room/snack/";
	}
	//첨부파일 멤버
	public static String getFile_dir_pds() {		
		return "C:/Users/JSL505-014/Desktop/work_LJH/java_project/servlet_mvc_homepage/WebContent/file_room/pds/";
	}
	
	//첨부파일 프리보드
	public static String getFile_dir_freeboard() {		
		return "C:/Users/cbjub/eclipse-workspace/jsp_homepage_2/WebContent/file_room/freeboard/";
	}
	
	//null이면 공백으로 만들어주는 메소드
	public static String checkNull(String value) {
		
		
		String result="";
		if(value !=null) result = value;
		
		return result;
	}
	
	//오늘날짜
	public static String getToday(){
//		LocalDate currentDate = LocalDate.now();
		Date time = new Date();
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String today = format1.format(time);

//		SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
//		String time2 = format2.format(time);
		
		return today;
	}
	
	//오늘날짜 시,분,초
		public static String getTodayTime(){
//			LocalDate currentDate = LocalDate.now();
			Date time = new Date();
			
//			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//			String today = format1.format(time);

			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String time2 = format2.format(time);
			
			return time2;
		}
	
	//방법 1 (쓸 데에서 객체생성)
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs 		 = null;
	
	public void setHitCountCommon(String no, String tableName){
		String query ="update "+tableName+" \r\n" + 
					" set hit = hit +1 \r\n" + 
					" where no ='"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps 	= con.prepareStatement(query);
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("setHitCountCommon() 메소드 오류~ ");
			System.out.println("query :"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
	}
	
	//방법2 static 붙이고 변수 메서드 안쪽에
	public static void setHitCountCommon1(String no, String tableName){
		Connection con 		 = null;
		PreparedStatement ps = null;
		ResultSet rs 		= null;
		String query ="update "+tableName+" \r\n" + 
					" set hit = hit +1 \r\n" + 
					" where no ='"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps 	= con.prepareStatement(query);
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("setHitCountCommon() 메소드 오류~ ");
			System.out.println("query :"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
	}
	
	
	// 페이지
	public static String pageListPost(int current_page,int totalpage, int pageColCount){
		int pagenumber;    //화면에 보여질 페이지 인덱스수
		int startpage;     //화면에 보여질 시작 페이지 번호
		int endpage;       //화면에 보여질 마지막 페이지 번호
		int curpage;       //이동하고자 하는 페이지 번호
		
		String strList=""; //리턴될 페이지 인덱스 리스트

		pagenumber = pageColCount;   //한 화면의 페이지 인덱스수
		
		//시작 페이지 번호 구하기
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//마지막 페이지 번호 구하기
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//총페이지수가 계산된 마지막 페이지 번호보다 작을 경우
		//총페이지수가 마지막 페이지 번호가 됨
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//첫번째 페이지 인덱스 화면이 아닌경우
		if(current_page > pagenumber){
			curpage = startpage -1;  //시작페이지 번호보다 1적은 페이지로 이동
			strList = strList +"<a href=javascript:goPage('"+curpage+"') ><i class='fa fa-angle-double-left'></i></a>";
		}
						
		//시작페이지 번호부터 마지막 페이지 번호까지 화면에 표시
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a class='active'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href=javascript:goPage('"+curpage+"')>"+curpage+"</a>";
			}
			curpage++;
		}
		//뒤에 페이지가 더 있는 경우
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href=javascript:goPage('"+curpage+"') ><i class='fa fa-angle-double-right'></i></a>";
		}
		return strList;
	}	
}
