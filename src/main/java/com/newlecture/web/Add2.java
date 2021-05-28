//Add2.java
package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		/* html�κ��� num�� getParmeterValues�� �迭�� ���� �޾Ƽ� num_�� �迭�� �����Ѵ� */
		String[] num_ = request.getParameterValues("num");
		
		int result = 0;
		
		/* �迭�� ���̸�ŭ �����鼭, ���ڿ��� ������ ��ȯ�� ������� �����Ѵ� */
		for(int i=0; i<num_.length; i++) {
			int num = Integer.parseInt(num_[i]);
			result += num;
		}
						
		response.getWriter().printf("Result is %d\n", result);
				
		}
	}
	


