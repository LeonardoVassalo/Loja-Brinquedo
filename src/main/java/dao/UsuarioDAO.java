package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Brinquedo;
import model.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import util.ConnectorFactory;

public class UsuarioDAO {

	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Usuario usuario;
	
	 ArrayList<Usuario> list = new ArrayList<>();
	

	public UsuarioDAO() throws Exception {
		// chama a classe ConnectionFactory e estabele uma conex�o
		try {
			this.conn = ConnectorFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}
	
	
	public Usuario login(Usuario usuario) throws Exception {
	    try {
	        String SQL = "SELECT * FROM usuario WHERE nome=? and senha=?";
	        ps = conn.prepareStatement(SQL);
	        ps.setString(1, usuario.getNome());
	        ps.setString(2, usuario.getSenha());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            // Crie um objeto Usuario com os dados do ResultSet
	            Usuario loggedInUser = new Usuario();
	            loggedInUser.setNome(rs.getString("nome")); // Supondo que o campo id exista no seu modelo
	            loggedInUser.setSenha(rs.getString("senha"));
	            // Defina outros atributos do usu�rio, se necess�rio
	            return loggedInUser;
	        } else {
	            return null; // Retorna null se o usu�rio n�o for encontrado
	        }
	    } catch (SQLException sqle) {
	        throw new Exception(sqle);
	    } finally {
	        ConnectorFactory.closeConnection(conn, ps, rs);
	    }
	}
	
	// metodo de salvar

		public void salvar(Usuario usuario) throws Exception {

			if (usuario == null)
				throw new Exception("O valor passado nao pode ser nulo");
			try {
				String SQL = "INSERT INTO usuario  (nome, senha) values "
						+ "(?, ?)";
				ps = conn.prepareStatement(SQL);
				ps.setString(1, usuario.getNome());
				ps.setString(2, usuario.getSenha());
				
				ps.executeUpdate();
			} catch (SQLException sqle) {
				throw new Exception("Erro ao inserir dados " + sqle);
			} finally {
				ConnectorFactory.closeConnection(conn, ps);
			}
		}
		
		
		// Listar todos os alunos

		public List todosUsuarios() throws Exception {
			try {
				ps = conn.prepareStatement("SELECT * FROM usuario");
				rs = ps.executeQuery();
				List<Usuario> list = new ArrayList<Usuario>();
				while (rs.next()) {
					int idUsuario = rs.getInt("idUsuario");
					String nome = rs.getString("nome");
					String senha = rs.getString("senha");
					
					list.add(new Usuario(idUsuario, nome, senha));
				}
				return list;
			} catch (SQLException sqle) {
				throw new Exception(sqle);
			} finally {
				ConnectorFactory.closeConnection(conn, ps, rs);
			}
		}
		
		public ArrayList<Usuario> mostrartodos() {
			try {
				ArrayList<Usuario> mostrar = new ArrayList<>();
				String SQL = "SELECT  * FROM usuario";
				ps = conn.prepareStatement(SQL);
				rs = ps.executeQuery();
				if (rs.next()) {
					int idUsuario = rs.getInt("idUsuario");
					String nome = rs.getString("nome");
					String senha = rs.getString("senha");
					

					mostrar.add(new Usuario(idUsuario, nome, senha));
				}

				ConnectorFactory.closeConnection(conn, ps, rs);
				return mostrar;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}

		}
		
		public void excluir(Usuario usuario) throws Exception {
			if (usuario == null)
				throw new Exception("O valor passado nao pode ser nulo");
			try {
				String SQL = "DELETE FROM usuario WHERE idUsuario = ?";
				ps = conn.prepareStatement(SQL);
				ps.setInt(1, usuario.getIdUsuario());
				ps.executeUpdate();
			} catch (SQLException sqle) {
				throw new Exception("Erro ao excluir dados " + sqle);
			} finally {
				ConnectorFactory.closeConnection(conn, ps);
			}
		}
		
		public Usuario procurarUsuario(int idUsuarioo) throws Exception {

			try {
				String SQL = "SELECT * FROM usuario WHERE idUsuario  =?";
				ps = conn.prepareStatement(SQL);
				ps.setInt(1, idUsuarioo);
				rs = ps.executeQuery();
				if (rs.next()) {
					int idUsuario = rs.getInt("idUsuario");
					String nome = rs.getString("nome");
					String senha = rs.getString("senha");
					

					usuario = new Usuario (idUsuario, nome, senha);
				}
				return usuario;
			} catch (SQLException sqle) {
				throw new Exception(sqle);
			} finally {
				ConnectorFactory.closeConnection(conn, ps, rs);
			}
		}
		
		
		public void atualizar(Usuario usuario) throws Exception {
			if (usuario  == null)
				throw new Exception("O valor passado nao pode ser nulo");
			try {
				String SQL = "UPDATE usuario set nome=?, senha=? WHERE idUsuario=?";
				ps = conn.prepareStatement(SQL);
				ps.setString(1, usuario.getNome());
				ps.setString(2, usuario.getSenha());
			
				ps.setInt(3, usuario.getIdUsuario());
				ps.executeUpdate();
			} catch (SQLException sqle) {
				throw new Exception("Erro ao alterar dados " + sqle);
			} finally {
				ConnectorFactory.closeConnection(conn, ps);
			}
		}

		

	
}
