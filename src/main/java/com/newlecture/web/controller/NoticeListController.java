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
		
		NoticeService service = new NoticeService();
		List<Notice> list = service.getNoticeList(); // ctr+shift+O�� ��Ű�� �߰�
		
		request.setAttribute("list", list);
		
		//forward -> list.jsp�� ��û�ϸ鼭 ���� ������� request ����ҿ� ��µ����� �����Ѵ�.
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);

	}
}