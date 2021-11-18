package command.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Notice_dao;
import dto.Notice_dto;
import inter.Command;

public class NoticeView implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Notice_dao dao = new Notice_dao();
		String no = request.getParameter("t_no"); 
		dao.setHitCount(no); 
		
		Notice_dto dto1 = dao.getNoticeView(no);
		request.setAttribute("t_dto", dto1); 
		
		ArrayList<String> go = dao.getNoticeGo(no);
		ArrayList<String> back = dao.getNoticeBack(no);
		
		
		request.setAttribute("t_go", go); 
		request.setAttribute("t_back", back); 
	}
}