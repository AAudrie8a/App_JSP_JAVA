<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="style/Login.css">
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

		<div class="login-container">
			<h1>Iniciar Sesión</h1>
			<form class='Form_Login'>
				<div class='Input_Data'>
					<label> Email: </label> <input type="user" name="user"
						value={formData.user} onChange={handleChange} />
				</div>
				<div class='Input_Data'>
					<label> Contraseña: </label> <input type="password" name="password"
						value={formData.password} onChange={handleChange} />
				</div>


				<button type="submit">Iniciar Sesión</button>
			</form>
		</div>
	</div>

</body>
</html>