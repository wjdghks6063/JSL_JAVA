package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.Member_dto;
import dto.Notice_dto;
import dto.Pds_dto;

public class Pds_dao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//삭제
	public int PdsDelete(String choice){
		int result = 0;
		String query ="delete from h06_ser_pds where no='"+choice+"' ";
		
		try {
		con = DBConnection.getConnection();
		ps = con.prepareStatement(query);
		result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Pds delete()메서드 오류");
			System.out.println("쿼리문 : " +query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//수정저장
	public int pdsUpdate(Pds_dto dto) {
		int result=0;
		String query="update h06_ser_pds set \r\n" + 
				"title = '"+dto.getTitle()+"',\r\n" + 
				"content = '"+dto.getContent()+"',\r\n" + 
				"attach= '"+dto.getAttach()+"',\r\n" + 
				"reg_date= '"+dto.getReg_date()+"' \r\n" + 
				"where no= '"+dto.getNo()+"' ";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();			  
		}catch(SQLException e){
			System.out.println("pdsUpdate Update() 메서드 오류");
			System.out.println("쿼리문 :"+query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//다음
	public ArrayList<String> getPdsGo(String no){
		ArrayList<String> go = new ArrayList<>();
		String query= "select a.minno, b.title\r\n" + 
				"from(select min(no) as minno \r\n" + 
				"from h06_ser_pds\r\n" + 
				"where no > '"+no+"') a, h06_ser_pds b\r\n" + 
				"where a.minno = b.no ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String goNo = rs.getString(1);
				String goTitle = rs.getString(2);
				go.add(goNo);
				go.add(goTitle);
			}
		}catch(SQLException e) {
			System.out.println("pds getPdsGo 메서드 오류 : "+ query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return go;
	}
	
	//이전
	public ArrayList<String> getPdsBack(String no){
		ArrayList<String> back = new ArrayList<>();
		String query= "select a.maxno, b.title \r\n" + 
				"from(select max(no) as maxno\r\n" + 
				"from h06_ser_pds\r\n" + 
				"where no < '"+no+"') a, h06_ser_pds b\r\n" + 
				"where a.maxno = b.no ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String backNo = rs.getString(1);
				String backTitle = rs.getString(2);
				back.add(backNo);
				back.add(backTitle);
			}
		}catch(SQLException e) {
			System.out.println("pds getPdsBack 메서드 오류 : "+ query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return back;
	}
	
	// 조회수 증가
	public void setHitCount(String no){
	  int result = 0;
	  String query ="update h06_ser_pds \r\n" + 
	           " set hit = hit +1 \r\n" + 
	           " where no ='"+no+"'";
	  try {
	     con = DBConnection.getConnection();
	     ps    = con.prepareStatement(query);
	     result = ps.executeUpdate();
	  }catch(SQLException e) {
	     System.out.println("PdsSetHitCount() 메소드 오류~ ");
	     System.out.println("query :"+query);
	     e.printStackTrace();
	  } finally {
	     DBConnection.closeDB(con, ps, rs);
	  }
	}
	
	//등록
	public int PdsSave(Pds_dto dto) {
	int result=0;
	String query="insert into h06_ser_pds \r\n" + 
			"(no,title,content,attach,reg_id,reg_date)\r\n" + 
			"values('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getAttach()+"','"+dto.getReg_id()+"','"+dto.getReg_date()+"') ";
	
	try {
		con = DBConnection.getConnection();
		ps  = con.prepareStatement(query);
		result = ps.executeUpdate();
		
	}catch(SQLException e) {
		System.out.println("PdsSave() 오류~");
		System.out.println("query :"+query);
		e.printStackTrace();
	}finally {
		DBConnection.closeDB(con, ps, rs);
	}		
	return result;
	}
	
	// 상세조회
	public Pds_dto getPdsView(String no) {
	Pds_dto dto =null;
		String query ="select a.no, a.title, a.content, a.attach, b.name,\r\n" + 
				"TO_CHAR(a.reg_date,'yyyy-MM-dd hh24:mi:ss') as reg_date, a.hit\r\n" + 
				"from h06_ser_pds a, h06_ser_member b\r\n" + 
				"where a.reg_id = b.id\r\n" + 
				"and a.no = '"+no+"' ";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			 while(rs.next()) {
					String num 		= rs.getString(1);
					String title 	= rs.getString(2);
					String content 	= rs.getString(3);
					String attach 	= rs.getString(4);
					String name 	= rs.getString(5);
					String reg_date = rs.getString(6);
					int hit  		= rs.getInt(7);
					dto = new Pds_dto(num,title,content,attach,name,reg_date,hit);
			}
					
			}catch(SQLException e) {
				System.out.println("getPdsView()오류:"+ query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return dto;
		}

	
	// 게시글 번호 생성
	   public String getMaxNo(){
	      String no ="";
	      String query="select max(no) from h06_ser_pds";
	      try {
	         con = DBConnection.getConnection();
	         ps  = con.prepareStatement(query);
	         rs  = ps.executeQuery();
	         if(rs.next()) {
	            no = rs.getString(1); 
	         }
	         if(no.equals("")) {
	            no ="P001";
	         } else {
	            String n = no.substring(1); 
	            int num = Integer.parseInt(n); 
	            num = num + 1; 
	            DecimalFormat df = new DecimalFormat("P000"); 
	            no = df.format(num);      
	         }
	      }catch(SQLException e) {
	         System.out.println("Pds getMaxNo() 오류");
	         System.out.println("query :"+query);
	         e.printStackTrace();
	      } finally {
	         DBConnection.closeDB(con, ps, rs);
	      }
	      return no;
	   }
	
	//페이징
	public int getTotalCount(String select, String search){
		int result= 0;
		String query="select count(*) from h06_ser_pds a, h06_ser_member b\r\n" + 
				"where a.reg_id = b.id\r\n" + 
				"and "+select+" like '%"+search+"%' ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			}catch(SQLException e) {
				System.out.println("pds totalCount()메서드 오류 : "+ query);
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
		return result;
	}	
	
	//목록조회
	public ArrayList<Pds_dto> getPdsList(String select, String search, int start, int end){
		ArrayList<Pds_dto> dtos = new ArrayList<>();
		String query="select * from\r\n" + 
				"(select a.no, a.title, a.attach, b.name, TO_CHAR(a.reg_date,'yyyy-mm-dd') as reg_date,\r\n" + 
				"a.hit, row_number() over(order by no desc) as rnum\r\n" + 
				"from h06_ser_pds a, h06_ser_member b\r\n" + 
				"where a.reg_id = b.id\r\n" + 
				"and "+select+" like '%"+search+"%' \r\n" + 
				"order by a.no desc)\r\n" + 
				"WHERE rnum >= "+start+" and rnum <= "+end+" ";
	
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
			
			String no 		= rs.getString(1);
			String title 	= rs.getString(2);
			String attach 	= rs.getString(3);
			String name 	= rs.getString(4);
			String reg_date = rs.getString(5);
			int hit 		= rs.getInt(6);
			Pds_dto dto = new Pds_dto(no,title,attach,name,reg_date,hit); 
			dtos.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("getPdsList() 오류");
			System.out.println("query :"+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return dtos;		
		}
}
