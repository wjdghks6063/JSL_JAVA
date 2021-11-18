package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnection;
import dto.Member_dto;

public class Member_dao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	//로그인
	public String getLoginName(String id, String pw){
		String name =""; //로그인에 성공하였다면 이름을 보내주고 로그인에 성공하지않아 값이 없다면 공백을 보낸다.
		String query ="select name from h06_ser_member\r\n" + 
				"where id ='"+id+"'\r\n" + 
				"and password ='"+pw+"' ";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				name = rs.getString(1); 
			}
		} catch (SQLException e) {
			System.out.println("member getLoginName() 오류~: " + query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return name;
	}
	
	// 회원 등록
	public int saveMember(Member_dto dto) {
		int result = 0;
		String query = "insert into h06_ser_member\r\n"
				+ "(id,name,password,belong,linetell_1,linetell_2,linetell_3,\r\n"
				+ "tell_1,tell_2,tell_3,email_1,email_2\r\n" + ",reg_date)\r\n" + "values('" + dto.getId() + "','"
				+ dto.getName() + "','" + dto.getPassword() + "','" + dto.getBelong() + "','" + dto.getLinetell_1()
				+ "','" + dto.getLinetell_2() + "','" + dto.getLinetell_3() + "',\r\n" + "'" + dto.getTell_1() + "','"
				+ dto.getTell_2() + "','" + dto.getTell_3() + "','" + dto.getEmail_1() + "','" + dto.getEmail_2()
				+ "',\r\n" + "'" + dto.getReg_date() + "')";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("member saveMember() 오류~: " + query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	// 비밀번호 암호화
	public String encryptSHA256(String value) throws NoSuchAlgorithmException {
		String encryptData = "";

		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		sha.update(value.getBytes());

		byte[] digest = sha.digest();
		for (int i = 0; i < digest.length; i++) {
			encryptData += Integer.toHexString(digest[i] & 0xFF).toUpperCase();
		}

		return encryptData;
	}

	// 아이디 중복 조회
	public int getIdCount(String id) {
		int count = 0;
		String query = "select count(*) from h06_ser_member " + 
						" where id ='"+id+"' ";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} catch (SQLException se) {
			System.out.println("getIdCount() query 오류~: " + query);
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return count;
	}
}
