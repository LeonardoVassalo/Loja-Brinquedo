<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Brinquedo"%>
<%@ page import="dao.BrinquedoDAO"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href=""/>

    <style>

@import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');

*{
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

body{
   background-color: #f3f5f9;
}

.wrapper{
  display: flex;
  position: relative;
}

.wrapper .sidebar{
  width: 200px;
  height: 100%;
  background: #d4a405;
  padding: 30px 0px;
  position: fixed;
}

.wrapper .sidebar h2{
  color: #fff;
  text-transform: uppercase;
  text-align: center;
  margin-bottom: 30px;
}

.wrapper .sidebar ul li{
  padding: 15px;
  border-bottom: 1px solid #d4a405;
  border-bottom: 1px solid rgba(0,0,0,0.05);
  border-top: 1px solid rgba(255,255,255,0.05);
}    

.wrapper .sidebar ul li a{
  color: #ffffff;
  display: block;
}

.wrapper .sidebar ul li a .fas{
  width: 25px;
}

.wrapper .sidebar ul li:hover{
  background-color: #886903;
}
    
.wrapper .sidebar ul li:hover a{
  color: #fff;
}
 


.wrapper .main_content{
  width: 100%;
  margin-left: 200px;
}

.wrapper .main_content .header{
  padding: 20px;
  background: #fff;
  color: #000000;
  border-bottom: 1px solid #e0e4e8;
}

.wrapper .main_content .info{
  margin: 20px;
  color: #717171;
  line-height: 25px;
}

.wrapper .main_content .info div{
  margin-bottom: 20px;
}

.header{
    font-size: 30px;
    font-family: MINHA;

}
.dropdown-menu {
     background-color: #886903;
        color: black;
  }

    </style>
    
    <title>TOYSHELL</title>

</head>
<body>
    <div class="wrapper">
        <div class="sidebar">
            <img src="imagensSistema/toyshell.png" style="width:100%;"><hr>
            <ul>
            <form method="post" name="frmBrinquedo" action="../ServletBrinquedo">
  					<input type="hidden" name="cmd" value="destaque"/>			
                 <li><a href="../ServletBrinquedo?cmd=destaque" class="btn" role="button" data-bs-toggle="button">MENU</a></li></p>
             <li class="menu-item dropdown">
        <a href="#" class="btn dropdown-toggle" role="button" id="catalogoDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          CATÁLOGO
        </a>
        <div class="dropdown-menu" aria-labelledby="catalogoDropdown">
         <%
       			BrinquedoDAO obj = new BrinquedoDAO();
                Brinquedo b = new Brinquedo();
       
				 ArrayList<Brinquedo> list = obj.todosCat();
					
				
				
				for (int num = 0 ; num < list.size(); num++) {
				 
					 
				%>
								
						<a class="dropdown-item"  href="../ServletBrinquedo?cmd=lis&cat=<%=list.get(num).getCategoria()%>"><%=list.get(num).getCategoria()%></a>


						<%
					}
                     
					%>
        </div>
      </li>
              <li><a href="../ServletBrinquedo?cmd=login" class="btn" role="button" data-bs-toggle="button">ADMINISTRAÇÃO</a></li></p>
              <li><a href="../jsp/sobre.jsp" class="btn" role="button" data-bs-toggle="button">SOBRE NÓS</a></li></p>

</form>
            </ul> 

        </div>

        <div class="main_content">
            <div class="header">
				<center>SOBRE A EQUIPE</center>
			</div> 
            <div class="info">

                <div class="row row-cols-1 row-cols-md-3 g-4">
                    <div class="col">
                      <div class="card">
                        <img src="foto perfil.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                          <h5 class="card-title">Agatha Petruz Costa</h5>
                          <p class="card-text">RGM: 31525580</p>
                          <p class="card-text">Responsável por Front-End e Back-End.</p>
                        </div>
                      </div>
                    </div>
                    <div class="col">
                      <div class="card">
                        <img src="https://pps.whatsapp.net/v/t61.24694-24/341013879_1433161130771285_5019922844154314360_n.jpg?ccb=11-4&oh=01_AdTtZrWZPZuGmMhPqhjSgp-x6U_off3zMop4qitKY63dHA&oe=65209896&_nc_sid=000000&_nc_cat=107" class="card-img-top" alt="...">
                        <div class="card-body">
                          <h5 class="card-title">Leonardo Vassalo</h5>
                          <p class="card-text">RGM: 30925428</p>
                          <p class="card-text">Responsável por Back-End e Banco de dados</p>
                        </div>
                      </div>
                    </div>
                    <div class="col">
                      <div class="card">
                        <img src="https://pps.whatsapp.net/v/t61.24694-24/372842257_648236484038819_4268250512266066137_n.jpg?ccb=11-4&oh=01_AdSm9Ip3qqNgQ-0fWYBh_GVrFwDGhUdjZklzvk4fai_NBg&oe=6520AD72&_nc_sid=000000&_nc_cat=106" class="card-img-top" alt="...">
                        <div class="card-body">
                          <h5 class="card-title">Guilherme Pompeu Mascarenhas</h5>
                          <p class="card-text">RGM: 29364795</p>
                          <p class="card-text">Responsável por Banco de dados e Back-End</p>
                        </div>
                      </div>
                    </div>
                    <div class="col">
                      <div class="card">
                        <img src="https://pps.whatsapp.net/v/t61.24694-24/290177064_139569028706612_3439171714574222364_n.jpg?ccb=11-4&oh=01_AdRfFlMXFXMbCefunRpdpWnxverppxNWePSH8EGudPhXyQ&oe=6520A2A0&_nc_sid=000000&_nc_cat=105" class="card-img-top" alt="...">
                        <div class="card-body">
                          <h5 class="card-title">John Santos</h5>
                          <p class="card-text">RGM: 31331467</p>
                          <p class="card-text">Responsável por Documentação e Front-end</p>
                        </div>
                      </div>
                    </div>
                    <div class="col">
                        <div class="card">
                          <img src="https://pps.whatsapp.net/v/t61.24694-24/305904538_454432949779050_4432857660482169710_n.jpg?ccb=11-4&oh=01_AdSeOrCEyK4911HsITJVXZ-QxcOTeWrX5q4uazgdrE85fw&oe=6520AB5A&_nc_sid=000000&_nc_cat=106" class="card-img-top" alt="...">
                          <div class="card-body">
                            <h5 class="card-title">Felipe Castino </h5>
                            <p class="card-text">RGM: 31324584</p>
                            <p class="card-text">Responsável por </p>
                          </div>
                        </div>
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