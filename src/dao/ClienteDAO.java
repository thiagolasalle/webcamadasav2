package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDAO implements ClienteInDAO {

	private Connection conexao = null;
	
	public ClienteDAO(Connection _conn) {
		this.conexao = _conn;
	}
	
	@Override
	public void Inserir(Cliente _objeto) throws SQLException {
		
		String SQL = "INSERT INTO cliente (nome, cpf, endereco, sexo, deficiencia, idade_de_risco) VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getNome());
		ps.setString(2, _objeto.getCpf());
		ps.setString(3, _objeto.getEndereco());
		ps.setString(4, _objeto.getSexo());
		ps.setBoolean(5, _objeto.getDeficiencia());
		ps.setBoolean(6, _objeto.getIdadeDeRisco());
				
		ps.execute();

	}

	@Override
	public List<Cliente> listarTodos() throws SQLException {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs = null;
		
		String SQL = "SELECT id, nome, cpf, endereco, sexo, deficiencia, idade_de_risco FROM cliente";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			String cpf = rs.getString(3);
			String endereco = rs.getString(4);
			String sexo = rs.getString(5);
			Boolean deficiencia = rs.getBoolean(6);
			Boolean idadederisco = rs.getBoolean(7);
									
			Cliente p = new Cliente(id, nome, cpf, endereco, sexo, deficiencia, idadederisco, null);
			
			clientes.add(p);
		}
		
		return clientes;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
	
		String SQL = "DELETE FROM cliente WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		return ps.execute();
	}

	@Override
	public Boolean Atualizar(Cliente _objeto) throws SQLException {

		String SQL = "UPDATE cliente SET nome = ?, cpf = ?, endereco = ?, sexo = ?, deficiencia = ?, idade_de_risco= ? WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getNome());
		ps.setString(2, _objeto.getCpf());
		ps.setString(3, _objeto.getEndereco());
		ps.setString(4, _objeto.getSexo());
		ps.setBoolean(5, _objeto.getDeficiencia());
		ps.setBoolean(6, _objeto.getIdadeDeRisco());
		
		ps.setInt(7, _objeto.getId());
		
		return ps.execute();
	}

	@Override
	public Cliente buscarPorId(int _id) throws SQLException {
		
		ResultSet rs = null;
		Cliente p = null;
		
		String SQL = "SELECT id, nome, cpf, endereco, sexo, deficiencia, idade_de_risco FROM cliente WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.executeQuery();
		
		if (rs.next()) {
			
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			String cpf = rs.getString(3);
			String endereco = rs.getString(4);
			String sexo = rs.getString(5);
			Boolean deficiencia = rs.getBoolean(6);
			Boolean idadederisco = rs.getBoolean(7);
			
			p = new Cliente(id, nome, cpf, endereco, sexo, deficiencia, idadederisco, null);
		}
		
		return p;
	}

}
