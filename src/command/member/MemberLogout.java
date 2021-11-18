package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inter.Command;

public class MemberLogout implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String logoutName = (String)session.getAttribute("session_name");
		session.invalidate();
		
		request.setAttribute("t_msg", logoutName+" 님 로그아웃 되었습니다.");
		request.setAttribute("t_url", "Index");
	}

}
