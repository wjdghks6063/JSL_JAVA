package command.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Qna_dao;
import dto.Qna_dto;
import inter.Command;

public class QnaUpdateForm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Qna_dao dao = new Qna_dao();
		String no = request.getParameter("t_no");  
		
		Qna_dto dto = dao.getQnaView(no);  
		request.setAttribute("t_dto", dto); 
	}
}

