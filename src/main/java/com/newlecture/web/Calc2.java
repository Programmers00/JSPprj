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
		//ServletContext application = request.getServletContext(); //ServletContext 변수 application을 선언
//		HttpSession session = request.getSession(); //HttpSession 변수 session을 선언
		
		Cookie[] cookies = request.getCookies(); //쿠키를 배열로 읽어온다
				
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");
		String btn = request.getParameter("button");
		
		int v = 0;
		if(!v_.equals("")) v = Integer.parseInt(v_);
						
		//계산
		if(btn.equals("=")) {
//			int x = (Integer) session.getAttribute("value"); //object로 값을 반환하기 때문에 (Integer)라는 refer클래스로 해결!
//			String button = (String) session.getAttribute("btn"); //지역변수 이름을 button으로 설정해서 다른 지역변수와 충돌을 없앤다.
			
			
//			int x = (Integer) session.getAttribute("value"); //object로 값을 반환하기 때문에 (Integer)라는 refer클래스로 해결!
			int y = v; //위의 조건문으로부터 받아온 값
			
			int x = 0;
			for(Cookie c : cookies) //쿠키가 가지고 있는 배열의 개수를 돌면서 찾는 반복문.
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
		
		//저장
		else {
//			application.setAttribute("val ue", v); //application변수에 value이라는 이름으로 v값을 저장한다.
//			application.setAttribute("btn", btn); //application변수에 btn이라는 이름으로 btn값을 저장한다.	
			
//			session.setAttribute("value", v); //session변수에 value이라는 이름으로 v값을 저장한다.
//			session.setAttribute("btn", btn); //session변수에 btn이라는 이름으로 btn값을 저장한다.	
			
			/*쿠키 생성 및 저장 */
			Cookie valueCookie = new Cookie("value", String.valueOf(v)); //문자열만 사용 할 수 있기 때문에 String.valueOf()로 변환시켜준다.
			Cookie btnCookie = new Cookie("btn", btn);
			
			valueCookie.setPath("/calc2"); ///valueCooke는 calc2하위 URL이 호출 될때 Cookie 값을 전달.
			valueCookie.setMaxAge(24*60*60); //초단위로 설정된다. 24*60*60-> 24번*60번*60초=24시간
			
			btnCookie.setPath("/calc2"); //btnCookie는 calc2하위 URL이 호출 될때 Cookie 값을 전달.
			
			response.addCookie(valueCookie);
			response.addCookie(btnCookie);
			
			response.sendRedirect("calc2.html"); //calc2.html 페이지를 전달
			
		}
		
		}
	}
	


