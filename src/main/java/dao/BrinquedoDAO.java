package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.sql.Connection;

import model.Brinquedo;
import util.ConnectorFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class BrinquedoDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Brinquedo brinquedo;
	
	 ArrayList<Brinquedo> list = new ArrayList<>();
	

	

	public BrinquedoDAO() throws Exception {
		// chama a classe ConnectionFactory e estabele uma conex�o
		try {
			this.conn = ConnectorFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}

	// metodo de salvar

	public void salvar(Brinquedo brinquedo) throws Exception {

		if (brinquedo == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "INSERT INTO CadBrinquedos (nome, descricao, categoria, marca, valor, detalhes, imagem) values "
					+ "(?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, brinquedo.getNome());
			ps.setString(2, brinquedo.getDescricao());
			ps.setString(3, brinquedo.getCategoria());
			ps.setString(4, brinquedo.getMarca());
			ps.setDouble(5, brinquedo.getValor());
			ps.setString(6, brinquedo.getDetalhes());
			ps.setString(7, brinquedo.imagem);

			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps);
		}
	}

	// m�todo de atualizar

	public void atualizar(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "UPDATE CadBrinquedos set nome=?, descricao=?, categoria=?, marca=?, "
					+ "valor=?, detalhes=?, imagem=? WHERE idBrinquedo=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, brinquedo.getNome());
			ps.setString(2, brinquedo.getDescricao());
			ps.setString(3, brinquedo.getCategoria());
			ps.setString(4, brinquedo.getMarca());
			ps.setDouble(5, brinquedo.getValor());
			ps.setString(6, brinquedo.getDetalhes());
			ps.setString(7, brinquedo.imagem);
			ps.setInt(8, brinquedo.getIdBrinquedo());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao alterar dados " + sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps);
		}
	}

	public void excluir(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "DELETE FROM CadBrinquedos WHERE idBrinquedo = ?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, brinquedo.getIdBrinquedo());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao excluir dados " + sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps);
		}
	}

	// procurar Aluno

	public Brinquedo procurarBrinquedo(int idBrinquedos) throws Exception {

		try {
			String SQL = "SELECT * FROM CadBrinquedos WHERE idBrinquedo  =?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, idBrinquedos);
			rs = ps.executeQuery();
			if (rs.next()) {
				int idBrinquedo = rs.getInt("idBrinquedo");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				Double valor = rs.getDouble("valor");
				String detalhes = rs.getString("detalhes");
				String imagem = rs.getString("imagem");

				brinquedo = new Brinquedo(idBrinquedo, nome, descricao, categoria, marca, valor, detalhes, imagem);
			}
			return brinquedo;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps, rs);
		}
	}

	// pesquisar por categoria "carrinhos"
	public List todosCarrinhos(String idCategoria) throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM CadBrinquedos WHERE categoria = ?");
			ps.setString(1, idCategoria);
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int idBrinquedo = rs.getInt("idBrinquedo");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				Double valor = rs.getDouble("valor");
				String detalhe = rs.getString("detalhes");
				String imagem = rs.getString("imagem");
				list.add(new Brinquedo(idBrinquedo, nome, descricao, categoria, marca, valor, detalhe, imagem));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps, rs);
		}
	}
	
	//pesquisar destaque
	public List brinquedosDestaque() throws Exception {
		try {
			ps = conn.prepareStatement(" SELECT * FROM CadBrinquedos WHERE valor between 400 and 800 ");
			
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int idBrinquedo = rs.getInt("idBrinquedo");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				Double valor = rs.getDouble("valor");
				String detalhe = rs.getString("detalhes");
				String imagem = rs.getString("imagem");
				list.add(new Brinquedo(idBrinquedo, nome, descricao, categoria, marca, valor, detalhe, imagem));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps, rs);
		}
	}
	
	
	// pesquisar por categoria "carrinhos"
	public List todosTabuleiros(String idTabuleiros) throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM CadBrinquedos WHERE categoria = ?");
			ps.setString(1, idTabuleiros = "Tabuleiros");
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int idBrinquedo = rs.getInt("idBrinquedo");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				Double valor = rs.getDouble("valor");
				String detalhe = rs.getString("detalhes");
				String imagem = rs.getString("imagem");
				list.add(new Brinquedo(idBrinquedo, nome, descricao, categoria, marca, valor, detalhe, imagem));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps, rs);
		}
	}

	// pesquisar por categoria "bonecos"
	public List todosBonecos(String idBonecos) throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM CadBrinquedos WHERE categoria = ?");
			ps.setString(1, idBonecos = "Bonecos");
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int idBrinquedo = rs.getInt("idBrinquedo");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				Double valor = rs.getDouble("valor");
				String detalhe = rs.getString("detalhes");
				String imagem = rs.getString("imagem");
				list.add(new Brinquedo(idBrinquedo, nome, descricao, categoria, marca, valor, detalhe, imagem));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps, rs);
		}
	}

	// Listar todos os Brinquedos

	public List todosBrinquedos() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM CadBrinquedos");
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int idBrinquedo = rs.getInt("idBrinquedo");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				Double valor = rs.getDouble("valor");
				String detalhe = rs.getString("detalhes");
				String imagem = rs.getString("imagem");
				list.add(new Brinquedo(idBrinquedo, nome, descricao, categoria, marca, valor, detalhe, imagem));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectorFactory.closeConnection(conn, ps, rs);
		}
	}

	public ArrayList<Brinquedo> mostrartodos() {
		try {
			ArrayList<Brinquedo> mostrar = new ArrayList<>();
			String SQL = "SELECT  * FROM CadBrinquedos";
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next()) {
				int idBrinquedo = rs.getInt("idBrinquedo");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				Double valor = rs.getDouble("valor");
				String detalhe = rs.getString("detalhe");
				String imagem = rs.getString("imagem");

				mostrar.add(new Brinquedo(idBrinquedo, nome, descricao, categoria, marca, valor, detalhe, imagem));
			}

			ConnectorFactory.closeConnection(conn, ps, rs);
			return mostrar;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	
	
	
	
	public ArrayList<Brinquedo> todosCat() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT distinct categoria from CadBrinquedos");
		
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Brinquedo obj = new Brinquedo();
				
				obj.setCategoria( rs.getString("categoria"));
		
				list.add(obj);
			}
			
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		}
		
		finally {
			ConnectorFactory.closeConnection(conn, ps, rs);
		}
		return  list;
	}

}
