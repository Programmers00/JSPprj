package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자가 어떤 검색기준으로 전달할 것인지, 임시변수에 저장
		String field_ = request.getParameter("f");	//
		//사용자가 검색을 위한 검색어를 입력, 임시변수에 저장
		String query_ = request.getParameter("q");	
		
		/* 사용자가 전달하는 값이 null이 아닌경우에 대한 조건문*/
		String field = "title";	//전달되지 않으면 기본값
		if(field_ != null)
			field = field_;
		
		String query = "";	//전달되지 않으면 기본값
		if(query_ != null)
			query = query_;
		
		
		NoticeService service = new NoticeService();
		/*3가지의 오버로드 함수중에서 field, query, page를 선택*/
		//field는 옵션, null이 올 경우가 있기 때문에 조건문을 설정해야한다.
		List<Notice> list = service.getNoticeList(field, query, 1); // ctr+shift+O로 패키지 추가
		
		request.setAttribute("list", list);
		
		//forward -> list.jsp를 요청하면서 현재 사용중인 request 저장소와 출력도구를 공유한다.
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);

	}
}
