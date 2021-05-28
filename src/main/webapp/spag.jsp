<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- page저장소를 이용한 "Hello" -> "aa"에 저장  -->
<%
pageContext.setAttribute("value", "hello");
%>


<!--출력 코드--------------------------------------------------------->

<!-- 변수: request.getAttribute("value")  -->
<!-- request객체에서 value를 찾겠다! -->
	${requestScope.value}<br>

<!-- List: ((List)request.getAttribute("names")).get(0) -->
	${names[0]}<br>
	
<!-- Map: ((Map)request.getAttribute("notice")).get("title") -->
	${notice.title}<br>
	
<!-- PageContext 페이지 객체 -->
	${value}<br>
	
<!-- Parameter 정보 -->
	${param.num}<br>

<!-- header에서 accept 정보 -->
	${header.accept}<br>

<!-- EL 연산자 사용------------------------- -->
	${param.num>10}<br>

	${param.num == null || param.num ==""}<br>
	${empty param.num}<br>
	
	${not empty param.num}<br>
	
	${empty param.num?'값이 비어있습니다.':param.num}<br>
	
	${param.num/2}<br>


	
</body>
</html>	