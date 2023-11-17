<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Brinquedo"%>
<%@ page import="dao.BrinquedoDAO"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap"
	rel="stylesheet">

<style>
/* Estilos Globais */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	font-family: 'Josefin Sans', sans-serif;
}

/* Estilos para a barra lateral (sidebar) */
.sidebar {
	width: 200px;
	height: 100%;
	background: #d4a405;
	padding: 30px 0px;
	position: fixed;
	overflow-y: auto;
}

.sidebar h2 {
	color: #fff;
	text-transform: uppercase;
	text-align: center;
	margin-bottom: 30px;
}

.sidebar ul li {
	padding: 15px;
	border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.sidebar ul li a {
	color: #ffffff;
	display: block;
}

.sidebar ul li:hover {
	background-color: #886903;
}

.sidebar ul li:hover a {
	color: black;
}

/* Conteúdo principal */
.main_content {
	margin-left: 220px; /* Para acomodar a barra lateral */
	padding: 20px;
}

.header {
	padding: 20px;
	background: #fff;
	color: #000000;
	border-bottom: 1px solid #e0e4e8;
	font-size: 30px;
	text-align: center;
	margin-bottom: 20px;
}

/* Estilos para os cards */
.card {
	border: none;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
	margin-bottom: 25px;
}

.card img {
	max-width: 100%;
	height: auto;
}

.btnn {
	background-color: #d4a405;
	color: #ffffff;
	border: none;
	padding: 8px 20px;
	cursor: pointer;
}
</style>
<title>Lista Brinquedos</title>


</head>
<body>

	<div class="wrapper">
		<div class="sidebar">
			<img src="imagensSistema/toyshell.png" style="width:100%;"><hr>
			<ul class="menu">
				<input type="hidden" name="cmd" value="destaque" />
				<li><a href="ServletBrinquedo?cmd=destaque" class="btn"
					role="button" data-bs-toggle="button">MENU</a></li>
				<li class="menu-item dropdown"><a href="#"
					class="btn dropdown-toggle" role="button" id="catalogoDropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						CATÁLOGO </a>
					<div class="dropdown-menu" aria-labelledby="catalogoDropdown">

						<%
						BrinquedoDAO obj = new BrinquedoDAO();
						Brinquedo b = new Brinquedo();

						ArrayList<Brinquedo> list = obj.todosCat();
						int num = 0;

						for (num = 0; num < list.size(); num++) {
						%>

						<a class="dropdown-item"
							href="ServletBrinquedo?cmd=lis&cat=<%=list.get(num).getCategoria()%>"><%=list.get(num).getCategoria()%></a>
						<%
						}
						%>
					</div></li>
				<li><a href="ServletBrinquedo?cmd=login" class="btn"
					role="button" data-bs-toggle="button">ADMINISTRAÇÃO</a></li>
				<li><a href="jsp/sobre.jsp" class="btn" role="button"
					data-bs-toggle="button">SOBRE NÓS</a></li>
			</ul>

		</div>
		<div class="main_content">
			<div class="header">CATEGORIAS</div>

			<div class="row">
				<%
				List<Brinquedo> lista = new ArrayList<Brinquedo>();
				int count = 0;
				lista = (ArrayList) request.getAttribute("brinquedoList");
				for (Brinquedo a : lista) {
				%>

				<div class="col-md-4">
					<div class="card">
						<form action="ServletBrinquedo" method="post">
							<input type="hidden" name="cmd" value="ver"> <img
								src=<%="Imagens/" + a.getImagem()%> alt="Imagem do Brinquedo"
								height="300">
							<div class="card-body">
								<h5 class="card-title"><%=a.getNome()%></h5>
								<p class="card-text"><%=a.getDetalhes()%></p>
								<p class="card-text" id="preco">
									R$<%=a.getValor()%></p>
								<input type="hidden" name="txtId"
									value="<%=a.getIdBrinquedo()%>">
								<button class="btnn" type="submit">Ver</button>
							</div>
						</form>
					</div>
				</div>

				<%
				count++;
				if (count % 3 == 0) {
				%>
				<div class="w-100"></div>
				<%
				}
				}
				%>
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
			integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
			crossorigin="anonymous"></script>
</body>
</html>