package command.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Qna_dto;
import inter.Command;

public class QnaWrite implements Command {

	@Override
public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Qna_dto dto = (Qna_dto)request.getAttribute("t_dto");
		
		request.setAttribute("t_dtos", dto);
	}

}