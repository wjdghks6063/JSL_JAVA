package command_pds;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Pds_dao;
import dto.Pds_dto;
import inter.Command;

public class PdsView implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Pds_dao dao = new Pds_dao();
		String no = request.getParameter("t_no"); 
		dao.setHitCount(no); 
		
		Pds_dto dto1 = dao.getPdsView(no);
		request.setAttribute("t_dto", dto1); 
		
		ArrayList<String> go = dao.getPdsGo(no);
		ArrayList<String> back = dao.getPdsBack(no);
		
		
		request.setAttribute("t_go", go); 
		request.setAttribute("t_back", back); 
	}
}