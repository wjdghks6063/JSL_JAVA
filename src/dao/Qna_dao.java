package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.Notice_dto;
import dto.Qna_dto;

public class Qna_dao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//질문 삭제	
		public int QnaAnswerDelete(Qna_dto dto) {
			int result=0;
			String query="update h06_ser_qna set \r\n" + 
					"a_content='',\r\n" + 
					"a_reg_id='',\r\n" + 
					"a_reg_date=''\r\n" + 
					"where no= '"+dto.getNo()+"' ";
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				result = ps.executeUpdate();			  
			}catch(SQLException e){
				System.out.println("QnaAnswerDelete AnswerDelete() 메서드 오류");
				System.out.println("쿼리문 :"+query);
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return result;
		}
	
	//질문 답변	
		public int QnaAnswerSave(Qna_dto dto) {
			int result=0;
			String query="update h06_ser_qna set \r\n" + 
					"a_content='"+dto.getA_content()+"',\r\n" + 
					"a_reg_id='"+dto.getA_reg_id()+"',\r\n" + 
					"a_reg_date='"+dto.getA_reg_date()+"'\r\n" + 
					"where no= '"+dto.getNo()+"' ";
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				result = ps.executeUpdate();			  
			}catch(SQLException e){
				System.out.println("QnaAnswerSave AnswerSave() 메서드 오류");
				System.out.println("쿼리문 :"+query);
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return result;
		}
	
	//삭제
	
		public int QnaDelete(String choice){
			int result = 0;
			String query ="delete from h06_ser_qna where no='"+choice+"' ";
			
			try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Qna delete()메서드 오류");
				System.out.println("쿼리문 : " +query);
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return result;
		}
	
	//수정저장
		public int QnaUpdate(Qna_dto dto) {
			int result=0;
			String query="update h06_ser_qna set \r\n" + 
					"q_title ='"+dto.getQ_title()+"',\r\n" + 
					"q_content='"+dto.getQ_content()+"',\r\n" + 
					"secret='"+dto.getSecret()+"',\r\n" + 
					"q_reg_date='"+dto.getQ_reg_date()+"'\r\n" + 
					"where no= '"+dto.getNo()+"' ";
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				result = ps.executeUpdate();			  
			}catch(SQLException e){
				System.out.println("QnaUpdate Update() 메서드 오류");
				System.out.println("쿼리문 :"+query);
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			return result;
		}
	
	//다음
	public ArrayList<String> getQnaGo(String no, String secret){
		ArrayList<String> go = new ArrayList<>();
		String query= "select a.minno, b.q_title, b.secret, b.q_reg_id  \r\n" + 
				"from(select min(no) as minno \r\n" + 
				"from h06_ser_qna\r\n" + 
				"where no > '"+no+"') a, h06_ser_qna b\r\n" + 
				"where a.minno = b.no ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String goNo     = rs.getString(1);
				String goTitle  = rs.getString(2);
				String goSecret = rs.getString(3);
				String goQ_reg_id = rs.getString(4);
				
				go.add(goNo);
				go.add(goTitle);
				go.add(goSecret);
				go.add(goQ_reg_id);
			}
		}catch(SQLException e) {
			System.out.println("Qna getQnaGo 메서드 오류 : "+ query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return go;
	}
		
	//이전
	public ArrayList<String> getQnaBack(String no, String secret){
		ArrayList<String> back = new ArrayList<>();
		String query= "select a.maxno, b.q_title, b.secret, b.q_reg_id  \r\n" + 
				"from(select max(no) as maxno\r\n" + 
				"from h06_ser_qna\r\n" + 
				"where no < '"+no+"') a, h06_ser_qna b\r\n" + 
				"where a.maxno = b.no ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String backNo 	 = rs.getString(1);
				String backTitle = rs.getString(2);
				String backSecret = rs.getString(3);
				String backQ_reg_id = rs.getString(4);
				back.add(backNo);
				back.add(backTitle);
				back.add(backSecret);
				back.add(backQ_reg_id);
			}
		}catch(SQLException e) {
			System.out.println("Qna getQnaBack 메서드 오류 : "+ query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return back;
	}
	
	// 조회수 증가
		public void setHitCount(String no){
		  int result = 0;
		  String query ="update h06_ser_qna \r\n" + 
		           " set hit = hit +1 \r\n" + 
		           " where no ='"+no+"'";
		  try {
		     con = DBConnection.getConnection();
		     ps    = con.prepareStatement(query);
		     result = ps.executeUpdate();
		  }catch(SQLException e) {
		     System.out.println("QnaSetHitCount() 메소드 오류~ ");
		     System.out.println("query :"+query);
		     e.printStackTrace();
		  } finally {
		     DBConnection.closeDB(con, ps, rs);
		  }
		}
	
	// 상세조회
	/*목록에 null 값이 들어가면 결과가 아예 안나오지만 id에 (+)를 하면 null이어도 빈값을 더해줘 행에 표시가 된다 */
	public Qna_dto getQnaView(String no) {
		 Qna_dto dto =null;
			String query ="select a.no,a.q_title,\r\n" + 
					"a.q_content, b.name as q_name,a.q_reg_id,\r\n" + 
					"TO_CHAR(a.q_reg_date,'yyyy-MM-dd hh24:mi:ss') as q_reg_date,\r\n" + 
					"a.a_content, c.name as a_name,\r\n" + 
					"TO_CHAR(a.a_reg_date,'yyyy-MM-dd hh24:mi:ss') as a_reg_date,\r\n" + 
					"a.secret, a.hit\r\n" + 
					"from h06_ser_qna a, h06_ser_member b, h06_ser_member c \r\n" + 
					"where a.q_reg_id = b.id\r\n" + 
					"and a.a_reg_id = c.id(+)\r\n" + 
					"and a.no ='"+no+"' ";
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				
				 while(rs.next()) {
						String num 		  = rs.getString(1);
						String q_title 	  = rs.getString(2);
						String q_content  = rs.getString(3);
						String q_name 	  = rs.getString(4);
						String q_reg_id   = rs.getString(5);
						String q_reg_date = rs.getString(6);
						String a_content  = rs.getString(7);
						String a_name 	  = rs.getString(8);
						String a_reg_date = rs.getString(9);
						String secret 	  = rs.getString(10);
						int hit  		  = rs.getInt(11);
						dto = new  Qna_dto(num,q_title,q_content,q_name,q_reg_id,q_reg_date,a_content,
											a_name,a_reg_date,secret,hit);
				}
						
				}catch(SQLException e) {
					System.out.println("get QnaView()오류"+ query);
					e.printStackTrace();
				}finally {
					DBConnection.closeDB(con, ps, rs);
				}
				return dto;
			}
	
	//등록
		public int QnaSave(Qna_dto dto) {
			int result=0;
			String query="insert into h06_ser_qna \r\n" + 
					"(no,q_title,q_content,q_reg_id,secret,q_reg_date)\r\n" + 
					"values('"+dto.getNo()+"','"+dto.getQ_title()+"','"+dto.getQ_content()+"','"+dto.getQ_reg_id()+"','"+dto.getSecret()+"','"+dto.getQ_reg_date()+"') ";
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				result = ps.executeUpdate();
				
			}catch(SQLException e) {
				System.out.println("QnaSave() 오류~");
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
	      String query="select max(no) from h06_ser_qna";
	      try {
	         con = DBConnection.getConnection();
	         ps  = con.prepareStatement(query);
	         rs  = ps.executeQuery();
	         if(rs.next()) {
	            no = rs.getString(1); 
	         }
	         if(no.equals("")) {
	            no ="Q001";
	         } else {
	            String n = no.substring(1); 
	            int num = Integer.parseInt(n); 
	            num = num + 1; 
	            DecimalFormat df = new DecimalFormat("Q000"); 
	            no = df.format(num);      
	         }
	      }catch(SQLException e) {
	         System.out.println("Qna getMaxNo() 오류");
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
		String query="select count(*) from h06_ser_qna a, h06_ser_member b\r\n" + 
				"where a.q_reg_id = b.id\r\n" + 
				"and "+select+" like '%"+search+"%' ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			}catch(SQLException e) {
				System.out.println("Qna totalCount()메서드 오류 : "+ query);
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
		return result;
	}	
	
	//목록조회
	public ArrayList<Qna_dto> getQnaList(String select, String search, int start, int end){
		ArrayList<Qna_dto> dtos = new ArrayList<>();
		
		String query="select * from \r\n" + 
				"(select a.no, a.q_title, a.a_content, b.name, \r\n" + 
				"TO_CHAR(a.q_reg_date,'yyyy-mm-dd') as q_reg_date,\r\n" + 
				"a.secret, a.q_reg_id, a.hit, row_number() over(order by no desc) as rnum \r\n" + 
				"from h06_ser_qna a, h06_ser_member b \r\n" + 
				"where a.q_reg_id = b.id \r\n" + 
				"and "+select+" like '%"+search+"%' \r\n" + 
				"order by a.no desc)\r\n" + 
				"WHERE rnum >= "+start+" and rnum <= "+end+" ";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String no 		  = rs.getString(1);
				String q_title 	  = rs.getString(2);
				String a_content  = rs.getString(3);
				String q_name 	  = rs.getString(4);
				String q_reg_date = rs.getString(5);
				String secret	  = rs.getString(6);
				String q_reg_id	  = rs.getString(7);
				int hit  		  = rs.getInt(8);
				Qna_dto dto = new Qna_dto(no,q_title,a_content,q_name,q_reg_date,secret,q_reg_id,hit);
				dtos.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("getQnaList()오류"+ query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}
}
