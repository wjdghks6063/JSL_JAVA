package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Member_dao;
import dto.Member_dto;
import inter.Command;

public class MemberSave implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Member_dao dao = new Member_dao();
		
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		
		String password = request.getParameter("t_pw_1");
		try {
			password = dao.encryptSHA256(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String belong = request.getParameter("t_belong");
		String linetell_1 = request.getParameter("t_line_1");
		String linetell_2 = request.getParameter("t_line_2");
		String linetell_3 = request.getParameter("t_line_3");
		String tell_1 = request.getParameter("t_tell_1");
		String tell_2 = request.getParameter("t_tell_2");
		String tell_3 = request.getParameter("t_tell_3");
		String email_1 = request.getParameter("t_email_1");
		String email_2 = request.getParameter("t_email_2");
		String reg_date = CommonUtil.getTodayTime();
		
		Member_dto dto =
				new Member_dto(id,name,password,belong,linetell_1,linetell_2,linetell_3,
						tell_1,tell_2,tell_3,email_1,email_2,reg_date,"","");
		
		int result = dao.saveMember(dto);
		if(result == 1) {
			request.setAttribute("t_msg", name+"님 회원가입 되셨습니다.");
		} else {
			request.setAttribute("t_msg", "회원 가입 오류입니다. 다시 시도해주시거나 관리자에게 문의 바랍니다.");
		}
		request.setAttribute("t_url", "Member");
	}

}
