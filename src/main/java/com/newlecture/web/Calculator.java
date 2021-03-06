package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {	
	/* GET방식 응답서블릿 */
	/*  */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		/* 지금 내 url에 쿠키를 저장 */
		resultCookie.setPath("/calculator");
		response.addCookie(resultCookie);		
		response.sendRedirect("calculator"); //현재경로calculator 다시 호출!
	}
	
	/* POST방식의 응답 서블릿  */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 쿠키 */
		
		Cookie[] cookies = request.getCookies(); //쿠키를 cookies 배열로 저장
		
		String result = "0";
		if(cookies != null)	//브라우저에서 null을 아무 값도 안 줄경우 조건문
			for(Cookie c : cookies)	//쿠키변수를 선언하고 쿠키의 개수만큼 반복
				if(c.getName().equals("result")) {	//쿠키의 값이 result와 같은 조건문
					result = c.getValue();	//result에 쿠키 값을 저장
					break;
				}
				
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();

		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<Style>");
		out.write("input{");
		out.write("	width:50px;");
		out.write("	height:50px;");
		out.write("}");
		out.write(".output{");
		out.write("	height:50px;");
		out.write("	backgroud: #e9e9e9;");
		out.write("	font-size:24px;");
		out.write("	font-width:bold;");
		out.write("	text-align:right;");
		out.write("	padding:0px 5px;");
		out.write("}");
			
		out.write("</Style>");

		out.write("</head>");
		out.write("<body>");
		out.write("	<form action=\"calc3\" method=\"post\">");
		out.write("		<table>");
		out.write("			<tr>");
		/* 계산된 결과를 출력 */
		out.printf("				<td class=\"output\" colspan=\"4\">%s</td>", result);
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"CE\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"C\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"BS\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"/\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"7\"></td>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"8\"></td>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"9\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"*\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"4\"></td>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"5\"></td>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"6\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"-\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"1\"></td>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"2\"></td>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"3\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"+\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td></td>");
		out.write("				<td><input type=\"submit\" name=\"number\" value=\"0\"></td>");
		out.write("				<td><input type=\"submit\" name=\"dot\" value=\".\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"=\"></td>");
		out.write("			</tr>");
								
		out.write("		</table>");
		out.write("	</form>");

		out.write("</body>");
		out.write("</html>");
		
	}
}
