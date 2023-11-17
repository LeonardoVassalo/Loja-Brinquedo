package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.JFileChooser;

import dao.BrinquedoDAO;
import dao.UsuarioDAO;
import model.Brinquedo;
import model.Usuario;

@MultipartConfig
@WebServlet("/ServletBrinquedo")
public class ServletBrinquedo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/* --- Função para salvar imagem no diretório do projeto, e inserir no banco de dados o nome do arquivo ---*/
	protected void salvarImagem(Part filePart, Brinquedo brinquedo) throws Exception{
		if (filePart != null) {
			try {
				String imageName = filePart.getSubmittedFileName();
				String UploadPath = "C:\\Users\\guilherme.pompeu\\Downloads\\LojaBrinquedo\\src\\main\\webapp\\Imagens/" + imageName;
				
				FileOutputStream fos = new FileOutputStream(UploadPath);
				InputStream is = filePart.getInputStream();

				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				
				fos.close();
				is.close();
				
				brinquedo.setImagem(imageName);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		BrinquedoDAO dao;
		UsuarioDAO daoUsu;
		

		Brinquedo brinquedo = new Brinquedo();
		
		Usuario usuario = new Usuario();
			
		
		try {
			
			if (cmd.equals("incluir")) {
				try {

					brinquedo.setNome(request.getParameter("txtNome"));
					brinquedo.setDescricao(request.getParameter("txtDescricao"));
					brinquedo.setCategoria(request.getParameter("cbmCategoria"));
					brinquedo.setMarca(request.getParameter("txtMarca"));
					brinquedo.setValor(Double.parseDouble(request.getParameter("txtValor")));
					brinquedo.setDetalhes(request.getParameter("txtDetalhes"));

					/* --- Chamando a função passando o campo da imagem e a model*/
					salvarImagem(request.getPart("image"), brinquedo);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}else if (cmd.equals("incluirUsu")){
				try {

					usuario.setNome(request.getParameter("txtNome"));
					usuario.setSenha(request.getParameter("txtSenha"));

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}else  if (cmd.equals("atualizar")) {
				brinquedo.setIdBrinquedo(Integer.parseInt(request.getParameter("txtId")));
				brinquedo.setNome(request.getParameter("txtNome"));
				brinquedo.setDescricao(request.getParameter("txtDesc"));
				brinquedo.setCategoria(request.getParameter("cmbCategoria"));
				brinquedo.setMarca(request.getParameter("txtMarca"));
				brinquedo.setValor(Double.parseDouble(request.getParameter("txtValor")));
				brinquedo.setDetalhes(request.getParameter("txtDetalhes"));

				Part id = request.getPart("image");

				/* --- Caso a imagem esteja vazia, continuar com a mesma definida no hidden com o valor do banco*/
				if (id.getSize() == 0) {
					brinquedo.setImagem(request.getParameter("image2"));
				} else {
					/* --- Caso contrário chamo a função passando o campo da imagem e a model*/
					brinquedo.setImagem(request.getParameter("image"));
					salvarImagem(request.getPart("image"), brinquedo);
				}

			
			}else  if (cmd.equals("atualizarUsu")) {
				usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtId")));
				usuario.setNome(request.getParameter("txtNome"));
				usuario.setSenha(request.getParameter("txtSenha"));
				
			
			}else if (cmd.equals("lis")) {
				
				brinquedo.setCategoria(request.getParameter("cat"));
				
			} else {

				brinquedo.setIdBrinquedo(Integer.parseInt(request.getParameter("txtId")));
				usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtId")));

			}
		} catch (Exception e) {
			// System.out.println("Erro na data");
			System.out.println(e.getMessage());
		}

		try {
			RequestDispatcher rd = null;
			
			dao = new BrinquedoDAO();
			daoUsu = new UsuarioDAO();
			if (cmd.equalsIgnoreCase("incluir")) {
				dao.salvar(brinquedo);
				rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");

			} else if (cmd.equalsIgnoreCase("listar")) {
				List brinquedoList = dao.todosBrinquedos();
				request.setAttribute("brinquedoList", brinquedoList);
				rd = request.getRequestDispatcher("jsp/mostrarBrinquedos.jsp");

			} else if (cmd.equalsIgnoreCase("lis")) {
				List brinquedoList = dao.todosCarrinhos(brinquedo.getCategoria());
				request.setAttribute("brinquedoList", brinquedoList);
			    brinquedo.setCategoria(request.getParameter("btnVer"));
				rd = request.getRequestDispatcher("jsp/mostrarCatalogo.jsp");

			} else if (cmd.equalsIgnoreCase("destaque")) {
				List brinquedoList = dao.brinquedosDestaque();							
				request.setAttribute("brinquedoList", brinquedoList);			
				rd = request.getRequestDispatcher("jsp/menuPrincipal.jsp");
				
			} else if (cmd.equalsIgnoreCase("atu")) {
				brinquedo = dao.procurarBrinquedo(brinquedo.getIdBrinquedo());
				HttpSession session = request.getSession(true);
				session.setAttribute("brinquedo", brinquedo);
				rd = request.getRequestDispatcher("jsp/atualizarBrinquedo.jsp");

			} else if (cmd.equalsIgnoreCase("ver")) {
				brinquedo = dao.procurarBrinquedo(brinquedo.getIdBrinquedo());
				HttpSession session = request.getSession(true);
				session.setAttribute("brinquedo", brinquedo);
				rd = request.getRequestDispatcher("jsp/verBrinquedo.jsp");
				
			} else if (cmd.equalsIgnoreCase("atualizar")) {
				dao.atualizar(brinquedo);
				rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
				
			} else if (cmd.equalsIgnoreCase("excluir")) {
				dao.excluir(brinquedo);
				rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
				
			}else if (cmd.equalsIgnoreCase("login")) {		
				rd = request.getRequestDispatcher("jsp/login.jsp");
				      
				}
			
			else if (cmd.equalsIgnoreCase("loginAutenticar")) {
				
				usuario.setNome(request.getParameter("txtnome"));
			    usuario.setSenha(request.getParameter("txtSenha"));
				 UsuarioDAO daon = new UsuarioDAO();
					 Usuario usu = daon.login(usuario);
					
					 if (usu != null) {
					        // As credenciais validas, voce pode redirecionar ou executar outras acoes
					        
						 	String nome = usu.getNome();
					        String senha = usu.getSenha();

					        // Redirecionar para a p�gina desejada
					       
					         RequestDispatcher rda =  request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
					         rda.forward(request, response);
					       
					       
					    } else {
					    	request.setAttribute("erro", "Usuario e senha incorretos");
					    	RequestDispatcher rda =  request.getRequestDispatcher("ServletBrinquedo?cmd=login");
					         rda.forward(request, response);
					       
					    } 
				
			
				      
				} else if (cmd.equalsIgnoreCase("incluirUsu")) {
					daoUsu.salvar(usuario);
					rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listarUsu");
					
					
				} else if (cmd.equalsIgnoreCase("listarUsu")) {
					List brinquedoList = daoUsu.todosUsuarios();
					request.setAttribute("brinquedoList", brinquedoList);
					rd = request.getRequestDispatcher("jsp/mostrarUsu.jsp");

				}else if (cmd.equalsIgnoreCase("excluirUsu")) {
					daoUsu.excluir(usuario);
					rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listarUsu");
				}  else if (cmd.equalsIgnoreCase("atuUsu")) {
					usuario = daoUsu.procurarUsuario(usuario.getIdUsuario());
					HttpSession session = request.getSession(true);
					session.setAttribute("usuario", usuario);
					rd = request.getRequestDispatcher("jsp/atualizarUsuario.jsp");

				}else if (cmd.equalsIgnoreCase("atualizarUsu")) {
					daoUsu.atualizar(usuario);
					rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listarUsu");
				} 
			
			
			
			
			
			rd.forward(request, response);
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
