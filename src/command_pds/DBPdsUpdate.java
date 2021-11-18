package command_pds;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonUtil;
import dao.Pds_dao;
import dto.Pds_dto;
import inter.Command;

public class DBPdsUpdate implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		Pds_dao dao = new Pds_dao();
		
		int sizeLimit = 1024 * 1024 * 10; // 1024는 1kb * kb 는 1mb  총 용량제한 = 10mb
		String file_dir = CommonUtil.getFile_dir_pds();		// 유틸가서 주소변경하기
		MultipartRequest mpr = null;
		try {
		mpr = new MultipartRequest(request,file_dir,sizeLimit,"utf-8",new DefaultFileRenamePolicy()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String no 		= mpr.getParameter("t_no");
		String title 	= mpr.getParameter("t_title");
		String content 	= mpr.getParameter("t_content");
		String reg_id 	= mpr.getParameter("t_reg_id");
		String reg_date = CommonUtil.getTodayTime();
		
		String delFile 	   	= mpr.getParameter("del_checkbox_file"); //체크박스 체크 값 / 체크할시 기존 첨부파일 명으로 바뀜 (가가.jsp가 기존 첨부파일일시 삭제 체크하면 del의 이름이 가가.jsp가 된다.)
		String oriFile 	   	= mpr.getParameter("t_ori_attach"); //기존 첨부파일  ex)가가.jsp
		String attach_name 	= mpr.getFilesystemName("t_attach"); //새로운 첨부파일 //타입이 file인것은 getParameter가 아니라 getFilesystemName 로 받아준다.
		
		String attach = ""; /*attach 담아주는 이름*/
		
		//첨부삭제 체크박스 체크시
		if(delFile != null){
			File file = new File(file_dir,delFile); //폴더 경로 , 파일명
			boolean tf = file.delete();
	//		out.println(" 삭제 여부 : " +tf);
		} else {
			attach = oriFile;
		}
		//기존 첨부파일을 새로운 첨부파일로 변경할 때
		if(attach_name != null){
			if(!oriFile.equals("")){
				File file = new File(file_dir,oriFile); //폴더 경로 , 파일명
				boolean tf = file.delete();
			}
			attach = attach_name;
		}
		
		Pds_dto dto = new Pds_dto(no,title,content,attach,reg_id,reg_date);
		int result = dao.pdsUpdate(dto); 
		
		if(result ==1) {
			request.setAttribute("t_msg", "수정 성공");
		}else {
			request.setAttribute("t_msg", "수정 실패");
		}
			request.setAttribute("t_url", "Pds");
	}

}

