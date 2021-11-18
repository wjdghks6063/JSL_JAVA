package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.qna.DBQnaAnswerDelete;
import command.qna.DBQnaAnswerSave;
import command.qna.DBQnaSave;
import command.qna.DBQnaUpdate;
import command.qna.QnaDelete;
import command.qna.QnaList;
import command.qna.QnaUpdateForm;
import command.qna.QnaView;
import command.qna.QnaWrite;
import inter.Command;

/**
 * Servlet implementation class Qna
 */
@WebServlet("/Qna")
public class Qna extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Qna() {
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
			Command qna = new QnaList();
			qna.execute(request, response);
			viewPage = "qna/qna_list.jsp";
		
		}else if(gubun.equals("view")) {//상세보기 
			Command qna = new QnaView();
			qna.execute(request, response);
			viewPage ="qna/qna_view.jsp";
			
		}else if(gubun.equals("writeForm")) {//등록폼
			Command qna = new QnaWrite();
			qna.execute(request, response);
			viewPage ="qna/qna_write.jsp";
		
		}else if(gubun.equals("save")) {
			Command qna = new DBQnaSave();
			qna.execute(request, response); 
			viewPage ="common_alert_page.jsp";
			
		}else if(gubun.equals("answerSave")) {
			Command qna = new DBQnaAnswerSave(); 
			qna.execute(request,response);
			viewPage ="common_alert_page.jsp";
			
		}else if(gubun.equals("answerDelete")) {
			Command qna = new DBQnaAnswerDelete(); 
			
			qna.execute(request,response);
			viewPage ="common_alert_page.jsp";		
			
		}else if(gubun.equals("updateForm")) {
			Command qna = new QnaUpdateForm(); 
			qna.execute(request,response);
			viewPage ="qna/qna_update.jsp";	
			 
		}else if(gubun.equals("update")) { 
			Command qna = new DBQnaUpdate();
			qna.execute(request,response);
			viewPage ="common_alert_page.jsp";  //알러트 통해서 수정된 거 view로 보여주러 갈거임	
						
		}else if(gubun.equals("delete")) { 
			Command qna = new QnaDelete();
			qna.execute(request,response);
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
