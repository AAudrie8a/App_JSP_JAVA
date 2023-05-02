<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

</head>
<body>

	<div class="Contenedor">
		<%
		java.util.Date d = new java.util.Date();
		%>

		<div class="Informacion">
			<label>Hoy estamos: </label>
			<p><%=java.text.DateFormat.getDateInstance().format(d)%></p>
			<h1>
				<%
				out.println("Hola a Todos");
				%>
			</h1>
		</div>
		<%@ include file="menu2.jsp" %>
		<%@ include file="formRegistro.jsp" %>
		
	</div>

</body>
</html>