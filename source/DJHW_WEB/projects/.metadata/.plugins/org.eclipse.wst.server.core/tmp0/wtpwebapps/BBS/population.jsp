<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import = "info.getPopulation" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>����������</title>
</head>
<body>
	<%
	//==========�������α���==========
		//������ �����Ǵ��� �˻�
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">����������</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="clinic.jsp">������ ����</a></li>
				<li class="active"><a href="population.jsp">������ �α���</a></li>
				<li><a href="bbs.jsp">�¶��� ���</a></li>
				<li><a href="question.jsp">���ֹ�������</a></li>
				<li><a href="check.jsp">�ڱ������ڳ�</a></li>
				<li><a href="law.jsp">���������ȳ�</a></li>			
				<%
				if("admin".equals(userID)) {
				%>
				<li><a href="admin.jsp">������������</a></li>	
				<%
					}
				%>							
			</ul>
			<%
				//������ ���ٸ� -> �α���, ȸ������ ����
				if(userID == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">�����ϱ�<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">�α���</a></li>
						<li><a href="join.jsp">ȸ������</a></li>
					</ul>
				</li>
			</ul>
			<%		
				//������ �ִٸ� -> �α׾ƿ�, ȸ��Ż�� ����
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">ȸ������<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">�α׾ƿ�</a></li>
						<li><a onclick="return confirm('������ Ż���Ͻðڽ��ϱ�?')" href="removeAction.jsp">ȸ��Ż��</a></li>
					</ul>
				</li>
			</ul>			
			<%
				}
			%>
		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1>������ �α���</h1>
				<p>������ ������ �α��� <br />(���� : ��������������)</p>
			</div>
		</div>
	</div>
	<div class="container">
		<% getPopulation gp = new getPopulation(); //�������������� XML���� ������ ������ ��ü%>
		<% gp.getTagValue(); %>
		<div class="row">
			<table class="table table-striped" style="border: none;">
				<thead>
					<tr>
						<th>���ø�</th>
						<th>�α���</th>
						<th>���ø�</th>
						<th>�α���</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<td><%=gp.cityname1%></td>
					<td><%=gp.citycnt1%></td>
					<td><%=gp.cityname10%></td>
					<td><%=gp.citycnt10%></td>
				</tr>
				<tr>
					<td><%=gp.cityname2%></td>
					<td><%=gp.citycnt2%></td>
					<td><%=gp.cityname11%></td>
					<td><%=gp.citycnt11%></td>
				</tr>
				<tr>
					<td><%=gp.cityname3%></td>
					<td><%=gp.citycnt3%></td>
					<td><%=gp.cityname12%></td>
					<td><%=gp.citycnt12%></td>
				</tr>
				<tr>
					<td><%=gp.cityname4%></td>
					<td><%=gp.citycnt4%></td>
					<td><%=gp.cityname13%></td>
					<td><%=gp.citycnt13%></td>
				</tr>
				<tr>
					<td><%=gp.cityname5%></td>
					<td><%=gp.citycnt5%></td>
					<td><%=gp.cityname14%></td>
					<td><%=gp.citycnt14%></td>
				</tr>
				<tr>
					<td><%=gp.cityname6%></td>
					<td><%=gp.citycnt6%></td>
					<td><%=gp.cityname15%></td>
					<td><%=gp.citycnt15%></td>
				</tr>
				<tr>
					<td><%=gp.cityname7%></td>
					<td><%=gp.citycnt7%></td>
					<td><%=gp.cityname16%></td>
					<td><%=gp.citycnt16%></td>
				</tr>
				<tr>
					<td><%=gp.cityname8%></td>
					<td><%=gp.citycnt8%></td>
					<td><%=gp.cityname17%></td>
					<td><%=gp.citycnt17%></td>
				</tr>
				<tr>
					<td><%=gp.cityname9%></td>
					<td><%=gp.citycnt9%></td>
					<td><%=gp.cityname18%></td>
					<td><%=gp.citycnt18%></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>