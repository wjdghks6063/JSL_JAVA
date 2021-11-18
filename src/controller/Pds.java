package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_pds.DBPdsSave;
import command_pds.DBPdsUpdate;
import command_pds.PdsDelete;
import command_pds.PdsList;
import command_pds.PdsUpdateForm;
import command_pds.PdsView;
import command_pds.PdsWrite;
import inter.Command;

/**
 * Servlet implementation class Pds
 */
@WebServlet("/Pds")
public class Pds extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pds() {
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
			Command pds = new PdsList();
			pds.execute(request, response);
			viewPage = "pds/pds_list.jsp";
		}else if(gubun.equals("view")) {//상세보기 
			Command pds = new PdsView();
			pds.execute(request, response);
			viewPage ="pds/pds_view.jsp";
			
		}else if(gubun.equals("writeForm")) {//등록폼
			Command pds = new PdsWrite();
			pds.execute(request, response);
			viewPage ="pds/pds_write.jsp";
			
		}else if(gubun.equals("save")) {
			Command pds = new DBPdsSave();
			pds.execute(request, response); 
			viewPage ="common_alert_page.jsp";
		
		}else if(gubun.equals("updateForm")) {
			Command pds = new PdsUpdateForm(); 
			pds.execute(request,response);
			viewPage ="pds/pds_update.jsp";
		
		}else if(gubun.equals("update")) { 
			Command pds = new DBPdsUpdate();
			pds.execute(request,response);
			viewPage ="common_alert_page.jsp";  //알러트 통해서 수정된 거 view로 보여주러 갈거임	
			
		}else if(gubun.equals("delete")) { 
			Command pds = new PdsDelete();
			pds.execute(request,response);
			
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
