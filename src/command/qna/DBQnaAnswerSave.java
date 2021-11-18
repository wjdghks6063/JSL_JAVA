package command.qna;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Qna_dao;
import dto.Qna_dto;
import inter.Command;

public class DBQnaAnswerSave implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		Qna_dao dao = new Qna_dao();
		String no = request.getParameter("t_no"); //t_no의 숫자값을 update에서 받아온다.
		request.setAttribute("t_no", no);	//t_no의 숫자값을 no에 담아둔다.
		
		String num 			= request.getParameter("t_no");
		String a_content 	= request.getParameter("a_content");
		String a_reg_id 	= request.getParameter("a_reg_id");
		String a_reg_date 	= CommonUtil.getTodayTime();
		
		Qna_dto dto 	= new Qna_dto(num,a_content,a_reg_id,a_reg_date);
		int result = dao.QnaAnswerSave(dto); 
		System.out.println("reuslt : " + result);
		
		if(result ==1) {
			request.setAttribute("t_msg", "답변 성공");
		}else {
			request.setAttribute("t_msg", "답변 실패");
		}
			request.setAttribute("t_url", "Qna");
	}

}