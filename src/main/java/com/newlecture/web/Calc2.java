//Calc2.java
package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ServletContext application = request.getServletContext(); //ServletContext ���� application�� ����
//		HttpSession session = request.getSession(); //HttpSession ���� session�� ����
		
		Cookie[] cookies = request.getCookies(); //��Ű�� �迭�� �о�´�
				
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");
		String btn = request.getParameter("button");
		
		int v = 0;
		if(!v_.equals("")) v = Integer.parseInt(v_);
						
		//���
		if(btn.equals("=")) {
//			int x = (Integer) session.getAttribute("value"); //object�� ���� ��ȯ�ϱ� ������ (Integer)��� referŬ������ �ذ�!
//			String button = (String) session.getAttribute("btn"); //�������� �̸��� button���� �����ؼ� �ٸ� ���������� �浹�� ���ش�.
			
			
//			int x = (Integer) session.getAttribute("value"); //object�� ���� ��ȯ�ϱ� ������ (Integer)��� referŬ������ �ذ�!
			int y = v; //���� ���ǹ����κ��� �޾ƿ� ��
			
			int x = 0;
			for(Cookie c : cookies) //��Ű�� ������ �ִ� �迭�� ������ ���鼭 ã�� �ݺ���.
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			
			String button = "";
			for(Cookie c : cookies)
				if(c.getName().equals("btn")) {
					button = c.getValue();
					break;
				}
			
			int result = 0;
			
			if(button.equals("+")) result = x + y;
			else result = x - y;
			
			response.getWriter().printf("Result is %d\n", result);
			
		}
		
		//����
		else {
//			application.setAttribute("val ue", v); //application������ value�̶�� �̸����� v���� �����Ѵ�.
//			application.setAttribute("btn", btn); //application������ btn�̶�� �̸����� btn���� �����Ѵ�.	
			
//			session.setAttribute("value", v); //session������ value�̶�� �̸����� v���� �����Ѵ�.
//			session.setAttribute("btn", btn); //session������ btn�̶�� �̸����� btn���� �����Ѵ�.	
			
			/*��Ű ���� �� ���� */
			Cookie valueCookie = new Cookie("value", String.valueOf(v)); //���ڿ��� ��� �� �� �ֱ� ������ String.valueOf()�� ��ȯ�����ش�.
			Cookie btnCookie = new Cookie("btn", btn);
			
			valueCookie.setPath("/calc2"); ///valueCooke�� calc2���� URL�� ȣ�� �ɶ� Cookie ���� ����.
			valueCookie.setMaxAge(24*60*60); //�ʴ����� �����ȴ�. 24*60*60-> 24��*60��*60��=24�ð�
			
			btnCookie.setPath("/calc2"); //btnCookie�� calc2���� URL�� ȣ�� �ɶ� Cookie ���� ����.
			
			response.addCookie(valueCookie);
			response.addCookie(btnCookie);
			
			response.sendRedirect("calc2.html"); //calc2.html �������� ����
			
		}
		
		}
	}
	


