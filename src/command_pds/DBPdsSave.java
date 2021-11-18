package command_pds;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonUtil;
import dao.Pds_dao;
import dto.Pds_dto;
import inter.Command;

public class DBPdsSave implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Pds_dao dao = new Pds_dao();
		
		int sizeLimit = 1024 * 1024 * 10; // 1024는 1kb * kb 는 1mb  총 용량제한 = 10mb
		String file_dir = CommonUtil.getFile_dir_pds();		// 유틸가서 주소변경하기
		MultipartRequest mpr = null;
		try {
			mpr = new MultipartRequest(request,file_dir,sizeLimit,"utf-8",new DefaultFileRenamePolicy()); 
		} 	catch (IOException e) {
			e.printStackTrace();
		}
		String no 		= dao.getMaxNo();
		String title 	= mpr.getParameter("t_title");
		String content 	= mpr.getParameter("t_content");
		String attach 	= mpr.getFilesystemName("t_attach");
		if(attach == null) attach = "";
		String reg_id 	= mpr.getParameter("t_reg_id");
		String reg_date = CommonUtil.getTodayTime();
		
		Pds_dto dto 	= new Pds_dto(no,title,content,attach,reg_id,reg_date);
		
		int result = dao.PdsSave(dto);
		if(result ==1) {
			request.setAttribute("t_msg", "등록 성공");
		}else {
			request.setAttribute("t_msg", "등록 실패");
		}
		request.setAttribute("t_url", "Pds");
	}

}