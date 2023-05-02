<style type="text/css">
@charset "ISO-8859-1";

body {
	background-image:
		url("https://getwallpapers.com/wallpaper/full/c/7/8/497882.jpg");
}

.Informacion {
	font-family: sans-serif;
	size: 30px;
}

.login-container {
	max-width: 400px;
	margin: 50px auto;
	padding: 20px;
	background-color: rgba(245, 245, 245, 0.7);
	border-radius: 10px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
	color: #333333;
	text-shadow: 1px 1px 2px rgba(255, 255, 255, 0.8);
	transition: transform 0.2s ease-in-out;
	text-align: center;
}

.login-container:hover {
	transform: translateY(-5px);
	box-shadow: 0px 9px 12px #A3DE83;
}

.Form_Login {
	display: inline-block;
	justify-content: center;
}

.Input_Data input {
	width: 300px;
}

.Input_Data label {
	font-size: 25px;
}

h1 {
	font-size: 35px;
	font-weight: bold;
	margin-top: 0;
	padding-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 10px;
	text-align: left;
	/* alinear el texto a la izquierda */
}

input {
	display: block;
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	margin-bottom: 20px;
	text-align: center;
	font-size: 20px;
	color: rgba(108, 106, 106, 0.8);
	/* centrar el contenido del input */
}

button[type="submit"] {
	background-color: #A3DE83;
	color: #fff;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	cursor: pointer;
	width: 200px;
	height: 40px;
	box-shadow: 0px 7px 8px #212125;
	margin: 10px;
	font-size: 20px
}

button[type="submit"]:hover {
	background-color: #F7F39A;
}
</style>
<%
    String action = request.getParameter("parameter1");
	String id = request.getParameter("parameter2");
    String formAction = "";
	System.out.println(action+" "+id);
    if (action != null && action.equals("modificar")) {
        formAction = "Modificar";
    } else {
        formAction = "Registro";
    }
%>
<div class="login-container">
	<h1>Registro Alumno</h1>
	<form class='Form_Login'
		action="<%=request.getContextPath()%>/<%=formAction%>" method="post">
		<div class='Input_Data'>
			<label> Nombre: </label> <input type="text" name="nombre" />
		</div>
		<div class='Input_Data'>
			<label> Apellido: </label> <input type="text" name="apellido" />
		</div>

		<input type="hidden" name="id" value="<%=id%>">
		<button type="submit"><%=formAction%></button>
	</form>
</div>