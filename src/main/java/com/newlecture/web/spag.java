package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class spag extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
						
			int num = 0;
			String value;
			String num_ = request.getParameter("num");
				if(num_ != null && !num_.equals(""))
					num = Integer.parseInt(num_);
				if(num%2 != 0)
					value = "홀수";
				else
					value = "짝수";
				/*변수*/
				request.setAttribute("value", value);
				
				/*list*/
				String[] names = {"newlec", "dragon"};
				request.setAttribute("names",names);
				
				/*Map*/
				Map<String, Object> notice = new HashMap<String, Object>();
				notice.put("id", 1);
				notice.put("title", "EL은 좋아요");
				request.setAttribute("notice", notice);
				
				/* forward를 위한 dispatcher */				
				RequestDispatcher dispatcher 
					= request.getRequestDispatcher("spag.jsp");
				dispatcher.forward(request, response);
			
		}
	
}
