package command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dto.Notice_dto;
import inter.Command;

public class NoticeWrite implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Notice_dto dto = (Notice_dto)request.getAttribute("t_dto");
		
		request.setAttribute("t_dtos", dto);
	}

}