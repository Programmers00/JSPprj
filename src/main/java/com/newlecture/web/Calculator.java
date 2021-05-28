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
	/* GET��� ���伭���� */
	/*  */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies(); //��Ű�� �迭�� �о�´�
		
		String number = request.getParameter("number");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String result = "";
				
		if(cookies != null)	//���������� null�� �ƹ� ���� �� �ٰ�� ���ǹ�
			for(Cookie c : cookies)	//��Ű���� c�� ����, c�� �ϳ��� ���鼭 ��Ű�� ������ŭ �ݺ�
				if(c.getName().equals("result")) {	//��Ű�� ���� result�� ���� ���ǹ�
					result = c.getValue();	//result�� ��Ű ���� ����
					break;
				}
		
		if(operator != null && operator.equals("=")) {	//���ǹ�, opartor�� null�� �ƴϰ�, =�� ���
			/* ScriptEngine���� ���� */
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				result = String.valueOf(engine.eval(result));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if(operator != null && operator.equals("C")) {	//���ǹ�, opartor�� null�� �ƴϰ�, C�� ��� ���ڿ� ��ȯ
			result = "";
		}
		
		else {
			result += (number == null)?"":number;	//���ǹ�, number�� null�̸� ���ڿ�, null�ƴϸ� number ����
			result += (operator == null)?"":operator;	//���ǹ�, operator�� null�̸� ���ڿ�, null�ƴϸ� operator ����
			result += (dot == null)?"":dot;	//���ǹ�, dot�� null�̸� ���ڿ�, null�ƴϸ� dot ����
		}
	
		Cookie resultCookie = new Cookie("result", result);	//resultCookie ����, �����Ǵ� **��ġ�� �߿�!! ��Ű�� �����ϱ� �ٷ����� ��������
		/*��Ű�� ���� */
		if(operator != null && operator.equals("C"))
			resultCookie.setMaxAge(0);	//MaxAge�� 0�̸� �ٷ� ��Ű�� ���ش�.
		
		/* ���� �� url�� ��Ű�� ���� */
		resultCookie.setPath("/calculator");
		response.addCookie(resultCookie);		
		response.sendRedirect("calculator"); //������calculator �ٽ� ȣ��!
	}
	
	/* POST����� ���� ������  */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* ��Ű */
		
		Cookie[] cookies = request.getCookies(); //��Ű�� cookies �迭�� ����
		
		String result = "0";
		if(cookies != null)	//���������� null�� �ƹ� ���� �� �ٰ�� ���ǹ�
			for(Cookie c : cookies)	//��Ű������ �����ϰ� ��Ű�� ������ŭ �ݺ�
				if(c.getName().equals("result")) {	//��Ű�� ���� result�� ���� ���ǹ�
					result = c.getValue();	//result�� ��Ű ���� ����
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
		/* ���� ����� ��� */
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