<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import ="java.util.ArrayList" %>
    <%@ page import ="com.spring.springform.EmpDeptVO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content_type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table border="1"align="center">
	<tr>
		<th width="80">EMPNO</th>
		<th width="80">ENAME</th>
		<th width="100">JOB</th>
		<th width="80">DEPTNO</th>
		<th width="80">DNAME</th>
		<th width="80">LOC</th>
	</tr>
	
<%
	ArrayList<EmpDeptVO>edlist =(ArrayList<EmpDeptVO>)request.getAttribute("edlist");
	for(int i=0; i<edlist.size();i++){
		EmpDeptVO empdept =(EmpDeptVO) edlist.get(i);
	
%>	

	<tr>
		<td>&nbsp;<%=empdept.getEmpno() %></td>
		<td>&nbsp;<%=empdept.getEname() %></td>
		<td>&nbsp;<%=empdept.getJob() %></td>

		<td>&nbsp;<%=empdept.getDeptno() %></td>
		<td>&nbsp;<%=empdept.getDname() %></td>
		<td>&nbsp;<%=empdept.getLoc() %></td>
	</tr>
	<%
	}
	%>
	<tr>
	<td colspan="6"><a href="index.jsp">처음으로</a></td>
	</tr>	
</table>
</body>
</html>