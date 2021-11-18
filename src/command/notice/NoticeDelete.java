package command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import dao.Notice_dao;
import inter.Command;

public class NoticeDelete implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Notice_dao dao = new Notice_dao();
		String no = request.getParameter("t_no");
		int result = dao.NoticeDelete(no);
		 
		request.setAttribute("t_result", result);
		
		if(result ==1) {
			request.setAttribute("t_msg", "삭제 되었습니다.");
		}else {
			request.setAttribute("t_msg", "오류로 인해 삭제 되지않았습니다. 다시 시도해 주세요.");
		}
			request.setAttribute("t_url", "Notice");
	}

}
