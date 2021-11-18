package command_pds;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Pds_dao;
import dto.Pds_dto;
import inter.Command;

public class PdsList implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Pds_dao dao = new Pds_dao();
		String select = request.getParameter("t_select"); // jsp랑 같은 방법으로 값받아오기
		String search = request.getParameter("t_search"); // jsp랑 같은 방법으로 값받아오기

		if (select == null) {
			select = "a.title";
			search = "";
		}

		String nowPage = request.getParameter("t_nowPage");
		int current_page = 0; // 현재페이지 번호
		int total_page = 0; // 전체 페이지 수
		int total_count = dao.getTotalCount(select, search); // 전체 행수
		int list_setup_count = 10; // 한 페이지당 출력 행수

		if (nowPage == null)
			current_page = 1;
		else
			current_page = Integer.parseInt(nowPage);

		total_page = total_count / list_setup_count; // 몫 : 2
		int rest = total_count % list_setup_count; // 나머지 : 1
		if (rest != 0)
			total_page = total_page + 1; // 3

		int start = (current_page - 1) * list_setup_count + 1;
		int end = current_page * list_setup_count;

		ArrayList<Pds_dto> dtos = dao.getPdsList(select, search, start, end);

		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
		request.setAttribute("t_c_page", current_page);
		request.setAttribute("t_t_page", total_page);
		request.setAttribute("t_total_count", total_count);

//		RequestDispatcher dispatcher = request.getRequestDispatcher("member/member_list.jsp"); //어떤 파일을 열껀가
//		dispatcher.forward(request, response);
	}

}