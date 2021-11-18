package command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Notice_dao;
import dto.Notice_dto;
import inter.Command;

public class DBNoticeSave implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Notice_dao dao = new Notice_dao();
		
		String no 		= dao.getMaxNo();
		String title 	= request.getParameter("t_title");
		String content 	= request.getParameter("t_content");
		String reg_id 	= request.getParameter("t_reg_id");
		String reg_date = CommonUtil.getTodayTime();
		Notice_dto dto 	= new Notice_dto(no,title,content,reg_id,reg_date);
		
		int result = dao.NoticeSave(dto); 
		System.out.println("reuslt : " + result);
		
		if(result ==1) {
			request.setAttribute("t_msg", "등록 성공");
		}else {
			request.setAttribute("t_msg", "등록 실패");
		}
			request.setAttribute("t_url", "Notice");
	}

}