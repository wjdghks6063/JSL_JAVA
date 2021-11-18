package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDown
 */
@WebServlet("/FileDown")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDown() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = request.getParameter("t_fileDir"); // 첨부 파일경로 
		if(savePath.equals("snack")) savePath = CommonUtil.getFile_dir_snack(); //다른곳에서도 같은 주소로 여러번 사용할 경우 하나의 값으로 수정,삭제 편리하게
		else if(savePath.equals("pds")) savePath = CommonUtil.getFile_dir_pds();
		
//		if(savePath.equals("notice")) savePath =  "C:/Users/JSL505-014/Desktop/work_LJH/java_project/jsp_homepage_1/WebContent/file_room/notice/";
//		파일 등록이 가능한 게시판이 1개인 경우 따로 jsp를 안만들고 직접 파일 사진이 등록될 파일주소를 입력하면 된다.

	 	String fileName = request.getParameter("t_file"); //첨부파일명
	    String backURL  = request.getParameter("t_backURL"); //첨부파일 오류발생시 돌아갈 페이지명
			
	    String orgfilename = fileName ;
	 
	    InputStream in = null;
	    OutputStream os = null;
	    File file = null;
	    boolean skip = false;
	    String client = "";
	    
	    // print out 만들기
	    PrintWriter out = response.getWriter();
	  
	    try{
	        try{
	            file = new File(savePath, fileName);
	            in = new FileInputStream(file);
	        }catch(FileNotFoundException fe){
	            skip = true;
	        }
	         
	        client = request.getHeader("User-Agent");
	        response.reset() ;
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Description", "JSP Generated Data");
	 
	        if(!skip){
	 
	            // IE
	            if(client.indexOf("MSIE") != -1){
	                response.setHeader ("Content-Disposition", "attachment; filename="+orgfilename);
	 
	            }else{
	                // 한글 파일명 처리
	                orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");

	                response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
	                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
	            } 
	             
	            response.setHeader ("Content-Length", ""+file.length() );
	            os = response.getOutputStream();
	            byte b[] = new byte[(int)file.length()];
	            int leng = 0;
	             
	            while( (leng = in.read(b)) > 0 ){
	                os.write(b,0,leng);
	            }
	 
	        }else{
	            response.setContentType("text/html;charset=UTF-8");
	            out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
	        }
	        in.close();
	        os.close();
	 
	    }catch(Exception e){
	    	System.out.println("첨부 파일 다운 오류~ 파일명:"+fileName);
	    	response.sendRedirect(backURL);
	    } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
