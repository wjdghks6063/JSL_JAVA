package command_pds;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Pds_dao;
import inter.Command;

public class PdsDelete implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Pds_dao dao = new Pds_dao();
		String no = request.getParameter("t_no");
		String attach 	= request.getParameter("t_attach");
		
		int result = dao.PdsDelete(no);
		
		if(result == 1) {
			File file = new File(CommonUtil.getFile_dir_pds(),attach); //폴더 경로 , 파일명
			boolean tf = file.delete();
		}
		 
		request.setAttribute("t_result", result);
		
		if(result ==1) {
			request.setAttribute("t_msg", "삭제 성공");
		}else {
			request.setAttribute("t_msg", "삭제 실패");
		}
			request.setAttribute("t_url", "Pds");
	}

}