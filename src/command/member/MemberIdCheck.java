package command.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;

/**
 * Servlet implementation class MemberIdCheck
 */
@WebServlet("/MemberIdCheck")
public class MemberIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberIdCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		int count = dao.getIdCount(id);
		
	//	String msg = count == 1 ? "이미 사용중인 ID입니다." : "사용 가능한 ID입니다.";
		String msg = "사용 가능한 ID입니다.";
		if(count == 1 ) msg ="이미 사용중인 ID입니다.";
		
		response.setContentType("text/html;charset=utf-8"); //이 메소드의 속성을 ""으로 지정해준다.
		PrintWriter out = response.getWriter();
		out.print(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
