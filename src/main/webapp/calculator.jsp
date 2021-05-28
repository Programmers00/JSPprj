int x = 3;
int y = 4;

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<Style>
input{
	width:50px;
	height:50px;
}
.output{
	height:50px;
	backgroud: #e9e9e9;
	font-size:24px;
	font-width:bold;
	text-align:right;
	padding:0px 5px;
}

	
</Style>

</head>
<body>
	<form action="calc3" method="post">
		<table>
			<tr>
				<td class="output" colspan="4">${5+7}</td>
			</tr>
			<tr>
				<td><input type="submit" name="operator" value="CE"></td>
				<td><input type="submit" name="operator" value="C"></td>
				<td><input type="submit" name="operator" value="BS"></td>
				<td><input type="submit" name="operator" value="/"></td>
			</tr>
			<tr>
				<td><input type="submit" name="number" value="7"></td>
				<td><input type="submit" name="number" value="8"></td>
				<td><input type="submit" name="number" value="9"></td>
				<td><input type="submit" name="operator" value="*"></td>
			</tr>
			<tr>
				<td><input type="submit" name="number" value="4"></td>
				<td><input type="submit" name="number" value="5"></td>
				<td><input type="submit" name="number" value="6"></td>
				<td><input type="submit" name="operator" value="-"></td>
			</tr>
			<tr>
				<td><input type="submit" name="number" value="1"></td>
				<td><input type="submit" name="number" value="2"></td>
				<td><input type="submit" name="number" value="3"></td>
				<td><input type="submit" name="operator" value="+"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="number" value="0"></td>
				<td><input type="submit" name="dot" value="."></td>
				<td><input type="submit" name="operator" value="="></td>
			</tr>
		
		
		</table>
	</form>

</body>
</html>