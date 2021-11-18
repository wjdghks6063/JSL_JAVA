package command_pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Pds_dao;
import dto.Pds_dto;
import inter.Command;

public class PdsUpdateForm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Pds_dao dao = new Pds_dao();
		String no = request.getParameter("t_no");  
		
		Pds_dto dto = dao.getPdsView(no);  
		request.setAttribute("t_dto", dto); 
	}
}
