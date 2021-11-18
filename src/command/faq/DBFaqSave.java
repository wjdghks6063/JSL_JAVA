package command.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Faq_dao;
import dto.Faq_dto;
import inter.Command;

public class DBFaqSave implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Faq_dao dao = new Faq_dao();
		
		String no 		= dao.getMaxNo();
		String title 	= request.getParameter("t_title");
		String content 	= request.getParameter("t_content");
		String reg_id 	= request.getParameter("t_reg_id");
		String reg_date = CommonUtil.getTodayTime();
		Faq_dto dto 	= new Faq_dto(no,title,content,reg_id,reg_date);
		
		int result = dao.FaqSave(dto);
		System.out.println("reuslt : " + result);
		
		if(result ==1) {
			request.setAttribute("t_msg", "등록 성공");
		}else {
			request.setAttribute("t_msg", "등록 실패");
		}
			request.setAttribute("t_url", "Faq");
	}

}