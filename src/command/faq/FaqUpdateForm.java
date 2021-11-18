package command.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Faq_dao;
import dto.Faq_dto;
import inter.Command;

public class FaqUpdateForm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Faq_dao dao = new Faq_dao();
		String no = request.getParameter("t_no");  
		
		Faq_dto dto = dao.getFaqView(no);  
		request.setAttribute("t_dto", dto); 
	}
}