package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.faq.DBFaqSave;
import command.faq.DBFaqUpdate;
import command.faq.FaqDelete;
import command.faq.FaqList;
import command.faq.FaqUpdateForm;
import command.faq.FaqWrite;
import inter.Command;

/**
 * Servlet implementation class Faq
 */
@WebServlet("/Faq")
public class Faq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Faq() {
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
			Command faq = new FaqList();
			faq.execute(request, response);
			viewPage = "faq/faq_list.jsp";
		
		}else if(gubun.equals("writeForm")) {//등록폼
			Command faq = new FaqWrite();
			faq.execute(request, response);
			viewPage ="faq/faq_write.jsp";
			
		}else if(gubun.equals("save")) {
			Command faq = new DBFaqSave();
			faq.execute(request, response); 
			viewPage ="common_alert_page.jsp";
			
		}else if(gubun.equals("updateForm")) {
			Command faq = new FaqUpdateForm(); 
			faq.execute(request,response);
			viewPage ="faq/faq_update.jsp";	
			
		}else if(gubun.equals("update")) { 
			Command faq = new DBFaqUpdate();
			faq.execute(request,response);
			viewPage ="common_alert_page.jsp";  //알러트 통해서 수정된 거 view로 보여주러 갈거임	
			
		}else if(gubun.equals("delete")) { 
			Command faq = new FaqDelete();
			faq.execute(request,response);
			
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
