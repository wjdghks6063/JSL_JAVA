package command.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Faq_dto;
import inter.Command;

public class FaqWrite implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Faq_dto dto = (Faq_dto)request.getAttribute("t_dto");
		
		request.setAttribute("t_dtos", dto);
	}

}