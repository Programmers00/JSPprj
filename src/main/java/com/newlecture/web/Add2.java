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
		
		/* html로부터 num을 getParmeterValues로 배열로 전달 받아서 num_에 배열로 저장한다 */
		String[] num_ = request.getParameterValues("num");
		
		int result = 0;
		
		/* 배열을 길이만큼 읽으면서, 문자열을 정수로 변환해 결과값에 저장한다 */
		for(int i=0; i<num_.length; i++) {
			int num = Integer.parseInt(num_[i]);
			result += num;
		}
						
		response.getWriter().printf("Result is %d\n", result);
				
		}
	}
	


