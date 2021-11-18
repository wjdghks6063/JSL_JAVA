package command.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Faq_dao;
import inter.Command;

public class FaqDelete implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Faq_dao dao = new Faq_dao();
		String no = request.getParameter("t_no");
		int result = dao.FaqDelete(no);
		 
		request.setAttribute("t_result", result);
		
		if(result ==1) {
			request.setAttribute("t_msg", "삭제 되었습니다.");
		}else {
			request.setAttribute("t_msg", "오류로 인해 삭제 되지않았습니다. 다시 시도해 주세요.");
		}
			request.setAttribute("t_url", "Faq");
	}

}