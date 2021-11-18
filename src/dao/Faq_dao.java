package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.Faq_dto;

public class Faq_dao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;	
	
	//삭제
		public int FaqDelete(String choice){
			int result = 0;
			String query ="delete from h06_ser_faq where no='"+choice+"' ";
			
			try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Faq delete()메서드 오류");
				System.out.println("쿼리문 : " +query);
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return result;
		}
	
	//수정저장
		public int FaqUpdate(Faq_dto dto) {
			int result=0;
			String query="update h06_ser_faq set " + 
					"title ='"+dto.getTitle()+"', \r\n" + 
					"content='"+dto.getContent()+"',\r\n" + 
					"reg_date='"+dto.getReg_date()+"' \r\n" + 
					"where no= '"+dto.getNo()+"' ";
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				result = ps.executeUpdate();			  
			}catch(SQLException e){
				System.out.println("faqUpdate Update() 메서드 오류");
				System.out.println("쿼리문 :"+query);
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return result;
		}
	
	// 상세조회
	public Faq_dto getFaqView(String no) {
		Faq_dto dto =null;
		String query ="select a.no, a.title, a.content, b.name,\r\n" + 
				"TO_CHAR(a.reg_date,'yyyy-MM-dd hh24:mi:ss') as reg_date\r\n" + 
				"from h06_ser_faq a, h06_ser_member b\r\n" + 
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
					String name 	= rs.getString(4);
					String reg_date = rs.getString(5);
					dto = new Faq_dto(num,title,content,name,reg_date);
			}
					
			}catch(SQLException e) {
				System.out.println("getFaqView()오류"+ query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return dto;
		}	
		
	
	//등록
		public int FaqSave(Faq_dto dto) {
			int result=0;
			String query="insert into h06_ser_faq\r\n" + 
					"(no,title,content,reg_id,reg_date)\r\n" + 
					"values('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getName()+"','"+dto.getReg_date()+"') ";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				result = ps.executeUpdate();
				
			}catch(SQLException e) {
				System.out.println("FaqSave() 오류~");
				System.out.println("query :"+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}		
			return result;
		}
	
	// 게시글 번호 생성
	   public String getMaxNo(){
	      String no ="";
	      String query="select max(no) from h06_ser_faq";
	      try {
	         con = DBConnection.getConnection();
	         ps  = con.prepareStatement(query);
	         rs  = ps.executeQuery();
	         if(rs.next()) {
	            no = rs.getString(1); 
	         }
	         if(no.equals("")) {
	            no ="F001";
	         } else {
	            String n = no.substring(1); 
	            int num = Integer.parseInt(n); 
	            num = num + 1; 
	            DecimalFormat df = new DecimalFormat("F000"); 
	            no = df.format(num);      
	         }
	      }catch(SQLException e) {
	         System.out.println("Faq getMaxNo() 오류");
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
			String query="select count(*) from h06_ser_faq a, h06_ser_member b\r\n" + 
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
					System.out.println("faq totalCount()메서드 오류 : "+ query);
				}finally {
					DBConnection.closeDB(con, ps, rs);
				}
			return result;
		}	
	
	//목록조회
		public ArrayList<Faq_dto> getFaqList(String select, String search, int start, int end){
			ArrayList<Faq_dto> dtos = new ArrayList<>();

			
			String query="select * from\r\n" + 
					"(select a.no, a.title, a.content, b.name, TO_CHAR(a.reg_date,'yyyy-MM-dd hh24:mi:ss') as reg_date,\r\n" + 
					"row_number() over(order by no desc) as rnum\r\n" + 
					"from h06_ser_faq a, h06_ser_member b \r\n" + 
					"where a.reg_id = b.id and "+select+" like '%"+search+"%'order by a.no desc)\r\n" + 
					"WHERE rnum >= "+start+" and rnum <= "+end+" ";
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while(rs.next()) {
					String no 		= rs.getString(1);
					String title 	= rs.getString(2);
					String content 	= rs.getString(3);
					String name 	= rs.getString(4);
					String reg_date = rs.getString(5);
					Faq_dto dto = new Faq_dto(no,title,content,name,reg_date);
					dtos.add(dto);
				}
			}catch(SQLException e) {
				System.out.println("getFaqList()오류"+ query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return dtos;
		}

	}
