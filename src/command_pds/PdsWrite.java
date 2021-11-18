package command_pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Pds_dto;
import inter.Command;

public class PdsWrite implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	Pds_dto dto = (Pds_dto)request.getAttribute("t_dto");
		
		request.setAttribute("t_dtos", dto);
	}

}