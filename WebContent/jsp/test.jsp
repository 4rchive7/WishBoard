<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8" import="model.vo.WishVO" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example of JSP</title>
<style type="text/css">
</style>
</head>
<body>
<%ArrayList<WishVO> cateSet = (ArrayList<WishVO>)request.getAttribute("cateSet");%>
	<script>
		
	</script>
	<div>
		<button id="textBtn" onclick="load()">sdf</button>
		<output id="result"></output>
		<table>
			<tr>
				<th>#</th>
				<th width=60%>제목</th>
				<th>작성자</th>
				<th width=20%>작성일</th>
				<th>조회수</th>
			</tr>
		</table>

	</div>
	<script>
		function load() {
			var nameSpace = "<table><tr><th>#</th><th width=60%>제목</th><th width=20%>작성일</th><th>조회수</th></tr>>";
			var content;
			
			<%
			ArrayList<WishVO> cateName = (ArrayList<WishVO>)request.getAttribute("cateName");
			for(WishVO vo: cateName){
				%>
				contenxt += "<tr>";
				contenxt += "<th>";
				contenxt += <%=vo.getId()%>;
				contenxt += "</th>";
				contenxt += "<th>";
				contenxt += <%=vo.getTitle()%>;
				contenxt += "</th>";
				contenxt += "<th>";
				contenxt += <%=vo.getWritedate()%>;			
				contenxt += "</th>";
				contenxt += "<th>";
				contenxt += <%=vo.getCnt()%>;	
				contenxt += "</th>";
				contenxt += "</tr>";
			<%}
			%>		
			content += "</table>";
			document.getElementById("result").innerHTML = table;
		}
	</script>
</body>
</html>