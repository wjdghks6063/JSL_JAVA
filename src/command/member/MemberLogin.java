package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member_dao;
import inter.Command;

public class MemberLogin implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		String pw = request.getParameter("t_pw");
		try { //비밀번호 입력값과 데이터에 보관중인 암호화된 데이터와 비교하기위해 입력한 비밀번호를 암호화 시켜준다.
			pw = dao.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String name = dao.getLoginName(id,pw);
		String msg ="";
		String url ="";
		if(!name.equals("")) { //아이디와 비밀번호가 맞을경우
			msg = name+"님 환영합니다.";
			url = "Index";
			
			HttpSession session = request.getSession(); //jsp에서 만들던 세션을 java에서는 이렇게 사용한다.
			session.setAttribute("session_name", name);
			session.setAttribute("session_id", id);
			
			if(id.equals("manager") || id.equals("aaa")) { //운영자로 사용될 id 이름
				session.setAttribute("session_level", "top"); //운영자 일시 세션 레벨등급을 top으로 준다
			}else{
				session.setAttribute("session_level", ""); //일반 사용자일 경우 세션 레벨의 값은 ""이다.
			}
			session.setMaxInactiveInterval(60 * 60 * 1); //1시간
		} else {
			msg = "아이디나 패스워드가 맞지 않습니다.";
			url = "Member";
		}
		
		request.setAttribute("t_msg",msg); // 로그인 결과 여부에 따라 출력 메세지가 바뀜
		request.setAttribute("t_url",url); // 로그인 결과 여부에 따라 이동되는 페이지가 바뀜
	}
}
