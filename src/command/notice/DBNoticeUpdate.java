package command.notice;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Notice_dao;
import dto.Notice_dto;
import inter.Command;

public class DBNoticeUpdate implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		Notice_dao dao = new Notice_dao();
		String no = request.getParameter("t_no"); //t_no의 숫자값을 update에서 받아온다.
		request.setAttribute("t_no", no);	//t_no의 숫자값을 no에 담아둔다.
		
		String num 		= request.getParameter("t_no");
		String title 	= request.getParameter("t_title");
		String content 	= request.getParameter("t_content");
		String reg_id 	= request.getParameter("t_reg_id");
		String reg_date = CommonUtil.getTodayTime();
		
		Notice_dto dto 	= new Notice_dto(num,title,content,reg_id,reg_date);
		int result = dao.NoticeUpdate(dto); 
		
		if(result ==1) {
			request.setAttribute("t_msg", "수정 성공");
		}else {
			request.setAttribute("t_msg", "수정 실패");
		}
			request.setAttribute("t_url", "Notice");
	}

}
