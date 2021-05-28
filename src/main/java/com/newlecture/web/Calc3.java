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
		response.addCookie(resultCookie);		
		response.sendRedirect("/calcpage"); //calcpage�� �̵�, ��ΰ� ����.
			
		
		
		}
	}
	


