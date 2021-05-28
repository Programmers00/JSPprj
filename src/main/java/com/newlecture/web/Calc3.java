//Calc3.java
package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import jdk.internal.org.jline.reader.ScriptEngine;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		Cookie[] cookies = request.getCookies(); //쿠키를 배열로 읽어온다
				
		String number = request.getParameter("number");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String result = "";
				
		if(cookies != null)	//브라우저에서 null을 아무 값도 안 줄경우 조건문
			for(Cookie c : cookies)	//쿠키변수 c를 선언, c를 하나씩 돌면서 쿠키의 개수만큼 반복
				if(c.getName().equals("result")) {	//쿠키의 값이 result와 같은 조건문
					result = c.getValue();	//result에 쿠키 값을 저장
					break;
				}
		
		if(operator != null && operator.equals("=")) {	//조건문, opartor이 null이 아니고, =인 경우
			/* ScriptEngine으로 구현 */
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				result = String.valueOf(engine.eval(result));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if(operator != null && operator.equals("C")) {	//조건문, opartor이 null이 아니고, C인 경우 빈문자열 반환
			result = "";
		}
		
		
			
		
		else {
			result += (number == null)?"":number;	//조건문, number가 null이면 빈문자열, null아니면 number 누적
			result += (operator == null)?"":operator;	//조건문, operator가 null이면 빈문자열, null아니면 operator 누적
			result += (dot == null)?"":dot;	//조건문, dot이 null이면 빈문자열, null아니면 dot 누적
		}
	
		Cookie resultCookie = new Cookie("result", result);	//resultCookie 생성, 생성되는 **위치가 중요!! 쿠키를 저장하기 바로전에 생성하자
		/*쿠키를 비우기 */
		if(operator != null && operator.equals("C"))
			resultCookie.setMaxAge(0);	//MaxAge가 0이면 바로 쿠키를 없앤다.
		response.addCookie(resultCookie);		
		response.sendRedirect("/calcpage"); //calcpage로 이동, 경로가 같음.
			
		
		
		}
	}
	


