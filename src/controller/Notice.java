package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.notice.DBNoticeSave;
import command.notice.DBNoticeUpdate;
import command.notice.NoticeDelete;
import command.notice.NoticeList;
import command.notice.NoticeUpdateForm;
import command.notice.NoticeView;
import command.notice.NoticeWrite;
import inter.Command;

/**
 * Servlet implementation class Notice
 */
@WebServlet("/Notice")
public class Notice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		String viewPage ="";
		if(gubun == null) {
			Command notice = new NoticeList();
			notice.execute(request, response);
			viewPage = "notice/notice_list.jsp";
		}else if(gubun.equals("view")) {//상세보기 
			Command notice = new NoticeView();
			notice.execute(request, response);
			viewPage ="notice/notice_view.jsp";
			
		}else if(gubun.equals("writeForm")) {//등록폼
			Command notice = new NoticeWrite();
			notice.execute(request, response);
			viewPage ="notice/notice_write.jsp";
			
		}else if(gubun.equals("save")) {
			Command notice = new DBNoticeSave();
			notice.execute(request, response); 
			viewPage ="common_alert_page.jsp";
			
		}else if(gubun.equals("updateForm")) {
			Command notice = new NoticeUpdateForm(); 
			notice.execute(request,response);
			viewPage ="notice/notice_update.jsp";
			 
		}else if(gubun.equals("update")) { Command notice = new DBNoticeUpdate();
			notice.execute(request,response);
			viewPage ="common_alert_page.jsp";  //알러트 통해서 수정된 거 view로 보여주러 갈거임	
			
		}else if(gubun.equals("delete")) { 
			Command notice = new NoticeDelete();
			notice.execute(request,response);
			
			viewPage ="common_alert_page.jsp";  //알러트 통해서 수정된 거 view로 보여주러 갈거임
		}
		
		RequestDispatcher rd =
				request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
