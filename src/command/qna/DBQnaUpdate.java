package command.qna;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Qna_dao;
import dto.Qna_dto;
import inter.Command;

public class DBQnaUpdate implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
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
		String q_title 		= request.getParameter("q_title");
		String q_content 	= request.getParameter("q_content");
		String q_reg_id 	= request.getParameter("q_reg_id");
		String secret 		= request.getParameter("t_secret");
		
		if(secret == null) {
			secret = "n";
		}
		
		String q_reg_date 	= CommonUtil.getTodayTime();
		
		Qna_dto dto 	= new Qna_dto(num,q_title,q_content,q_reg_id,secret,q_reg_date);
		int result = dao.QnaUpdate(dto); 
		System.out.println("reuslt : " + result);
		
		if(result ==1) {
			request.setAttribute("t_msg", "수정 성공");
		}else {
			request.setAttribute("t_msg", "수정 실패");
		}
			request.setAttribute("t_url", "Qna");
	}

}