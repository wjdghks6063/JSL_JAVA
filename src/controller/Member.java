package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.MemberLogin;
import command.member.MemberLogout;
import command.member.MemberSave;
import dao.Member_dao;
import inter.Command;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		String viewPage ="";
		if(gubun == null) {
			viewPage = "member/member_login.jsp";
		
		} else if(gubun.equals("goJoinForm")) { //회원가입 창 ,단순 화면 이동
			viewPage="member/member_join.jsp";	
			
		} else if(gubun.equals("memberSave")) { // 회원가입 등록, 정보를 넘겨서 DB에서 처리
			Command member = new MemberSave();
			member.execute(request, response);
			viewPage="common_alert_page.jsp";
			
		} else if (gubun.equals("login")) { //로그인 
			Command member = new MemberLogin();
			member.execute(request, response);
			viewPage="common_alert_page.jsp";
			
		} else if (gubun.equals("logout")) { //로그아웃
			Command member = new MemberLogout();
			member.execute(request, response);
			viewPage="common_alert_page.jsp";
			
		}
		
		RequestDispatcher rd =
				request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
