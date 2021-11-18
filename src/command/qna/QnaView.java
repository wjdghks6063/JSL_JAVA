package command.qna;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Qna_dao;
import dto.Qna_dto;
import inter.Command;

public class QnaView implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Qna_dao dao = new Qna_dao();
		String no = request.getParameter("t_no"); 
		String secret = request.getParameter("t_secret"); 
		dao.setHitCount(no); 
		
		Qna_dto dto1 = dao.getQnaView(no);
		request.setAttribute("t_dto", dto1); 

		ArrayList<String> go = dao.getQnaGo(no,secret);
		request.setAttribute("t_go", go); 
		
		ArrayList<String> back = dao.getQnaBack(no,secret);
		request.setAttribute("t_back", back); 
		
		
	}
}