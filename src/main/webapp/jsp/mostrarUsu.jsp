<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Brinquedo"%>
<%@ page import="dao.BrinquedoDAO"%>
<%@ page import="model.Usuario"%>
<%@ page import="dao.UsuarioDAO"%>

<!DOCTYPE html>
<html>
<head>




<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">



<style>
@import
	url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap')
	;

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	font-family: 'Josefin Sans', sans-serif;
}

@font-face {
	font-family: MINHA;
	src: url(KGHAPPYSolid.ttf);
}

body {
	background-color: #f3f5f9;
}

.wrapper {
	display: flex;
	position: relative;
}

.wrapper .sidebar {
	width: 200px;
	height: 100%;
	background: #d4a405;
	padding: 30px 0px;
	position: fixed;
}

.wrapper .sidebar h2 {
	color: #fff;
	text-transform: uppercase;
	text-align: center;
	margin-bottom: 30px;
}

.wrapper .sidebar ul li {
	padding: 15px;
	border-bottom: 1px solid #d4a405;
	border-bottom: 1px solid rgba(0, 0, 0, 0.05);
	border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.wrapper .sidebar ul li a {
	color: #ffffff;
	display: block;
}

.wrapper .sidebar ul li a .fas {
	width: 25px;
}

.wrapper .sidebar ul li:hover {
	background-color: #886903;
}

.wrapper .sidebar ul li:hover a {
	color: #fff;
}

.wrapper .main_content {
	width: 100%;
	margin-left: 200px;
}

.wrapper .main_content .header {
	padding: 20px;
	background: #fff;
	color: #000000;
	border-bottom: 1px solid #e0e4e8;
}

.wrapper .main_content .info {
	margin: 20px;
	color: #717171;
	line-height: 25px;
}

.wrapper .main_content .info div {
	margin-bottom: 20px;
}

.header {
	font-size: 30px;
	font-family: MINHA;
}

.card {
	border: none;
}

#preco {
	color: green;
	font-weight: bold;
}

#buscar {
	text-decoration: none;
	color: rgb(45, 45, 45);
}

#destaque:hover {
	transform: translateY(-5px);
	transition: transform 0.3s ease;
}

#destaque {
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
	width: 16rem;
	margin-bottom: 25px;
	margin-left: 15px;
	margin-right: 80px;
}

img {
	max-width: 250px;
	max-height: 250px;
}

.dropdown-menu {
	background-color: #886903;
	color: black;
}

.tableA {
	background-color: #d4a405;
}
.btnn {
	background-color: #d4a405;
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
				</p>
				<li class="menu-item dropdown"><a href="#"
					class="btn dropdown-toggle" role="button" id="catalogoDropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						CATÁLOGO </a>
					<div class="dropdown-menu" aria-labelledby="catalogoDropdown">
					
						<%
       			BrinquedoDAO obj = new BrinquedoDAO();
                Brinquedo b = new Brinquedo();
       
				 ArrayList<Brinquedo> list = obj.todosCat();
					
				
				
				for (int num = 0 ; num < list.size(); num++) {
				 
					 
				%>
								
						<a class="dropdown-item"  href="ServletBrinquedo?cmd=lis&cat=<%=list.get(num).getCategoria()%>"><%=list.get(num).getCategoria()%></a>


						<%
					}
                     
					%>
						
						
						
					</div></li>
				<li><a href="ServletBrinquedo?cmd=login" class="btn"
					role="button" data-bs-toggle="button">ADMINISTRAÇÃO</a></li>
				</p>
				<li><a href="jsp/sobre.jsp" class="btn" role="button"
					data-bs-toggle="button">SOBRE NÓS</a></li>
				</p>
				<li><a href="ServletBrinquedo?cmd=listarUsu" class="btn" role="button"
					data-bs-toggle="button">USUÁRIO</a></li>
					</p>

			</ul>

		</div>
		<div class="main_content">
			<div class="header">
				<center>ADMINISTRAÇÃO DE USUÁRIOS</center>
			</div>
		<br>

			<center><td>   <form action="jsp/incluirUsu.jsp" method="post"
									style="display: inline;">
									<button class="btnn" type="submit"
										name="txtId" value="">
										<i class="bi bi-pencil-square"></i> Cadastrar Novos Usuários
									</button>
								</form></td></center>
								<br>

			

			<div class="col-md-12">
				<table style="width: 80vw;" class="table">
					<thead class="tableA">
						<th scope="col">Código</th>
						
						<th width="500">Nome</th>
						<th width="500">Senha</th>
						<th width="200">Ações</th>
						
						
					
					</thead>
					<%
					List<Usuario> lista = new ArrayList<Usuario>();
					int count = 0;
					lista = (ArrayList) request.getAttribute("brinquedoList");
					for (Usuario a : lista) {
					%>
					<tbody>
						<tr>
							<th><%=a.getIdUsuario()%></th>
							<td><%=a.getNome()%></td>
							<td><%=a.getSenha()%></td>
							
							<td><form action="ServletBrinquedo?cmd=atuUsu" method="post"
									style="display: inline;">
									<button class="btnn" type="submit"
										name="txtId" value="<%=a.getIdUsuario()%>">
										<i class="bi bi-pencil-square"></i> Editar
									</button>
								</form>
								<form action="ServletBrinquedo?cmd=excluirUsu" method="post"
									style="display: inline;">
									<button class="btnn" type="button"
									data-toggle="modal" data-target="#modalExemplo<%=a.getIdUsuario()%>">
									
										<i class="bi bi-pencil-square"></i> Excluir
									</button>

									<div class="modal fade" id="modalExemplo<%=a.getIdUsuario()%>" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel<%=a.getIdUsuario()%>"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel<%=a.getIdUsuario()%>">Confirmar Exclusão</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Fechar">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">Você realmene deseja excluir esse Usuário ?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Fechar</button>
													<button class="btn btn-primary" type="submit" name="txtId"
														value="<%=a.getIdUsuario()%>">
														<i class="bi bi-trash3"></i> Excluir
													</button>
												</div>
											</div>
										</div>
									</div>

								</form></td>
						</tr>
					</tbody>
					<%
					}
					%>

				</table>
			</div>
			<br>
			</main>
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