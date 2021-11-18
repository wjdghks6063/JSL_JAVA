package command.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Qna_dao;
import dto.Qna_dto;
import inter.Command;

public class DBQnaSave implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Qna_dao dao = new Qna_dao();
		
		String no 			= dao.getMaxNo();
		String q_title 		= request.getParameter("q_title");
		String q_content 	= request.getParameter("q_content");
		String q_reg_id 	= request.getParameter("q_reg_id");
		String secret 		= request.getParameter("q_secret");
		
		if(secret == null) {
			secret = "n";
		}
		
		String q_reg_date 	= CommonUtil.getTodayTime();
		Qna_dto dto 		= new Qna_dto(no,q_title,q_content,q_reg_id,secret,q_reg_date);
		
		int result = dao.QnaSave(dto); 
		System.out.println("reuslt : " + result);
		
		if(result ==1) {
			request.setAttribute("t_msg", "등록 성공");
		}else {
			request.setAttribute("t_msg", "등록 실패");
		}
			request.setAttribute("t_url", "Qna");
	}

}