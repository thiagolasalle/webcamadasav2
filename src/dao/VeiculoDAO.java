package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Veiculo;

public class VeiculoDAO implements VeiculoInDAO {

	private Connection conexao = null;
	
	public VeiculoDAO(Connection _conn) {
		this.conexao = _conn;
	}
	
	@Override
	public void Inserir(Veiculo _objeto) throws SQLException {
		
		String SQL = "INSERT INTO veiculo (marca, cor, placa, status) VALUES (?, ?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getMarca());
		ps.setString(2, _objeto.getCor());
		ps.setString(3, _objeto.getPlaca());
		ps.setString(4, _objeto.getStatus());
		
		ps.execute();

	}

	@Override
	public List<Veiculo> listarTodos() throws SQLException {
			
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		ResultSet rs = null;
		
		String SQL = "SELECT id, marca, cor, placa, status FROM veiculo";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			String marca = rs.getString(2);
			String cor = rs.getString(3);
			String placa = rs.getString(4);
			String status = rs.getString(5);
			
			Veiculo p = new Veiculo(id, marca, cor, placa, status, null);
			
			veiculos.add(p);
		}
		
		return veiculos;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
	
		String SQL = "DELETE FROM veiculo WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		return ps.execute();
	}

	@Override
	public Boolean Atualizar(Veiculo _objeto) throws SQLException {

		String SQL = "UPDATE veiculo SET marca = ?, cor = ?, placa = ?, status = ? WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getMarca());
		ps.setString(2, _objeto.getCor());
		ps.setString(3, _objeto.getPlaca());
		ps.setString(4, _objeto.getStatus());
				
		ps.setInt(5, _objeto.getId());
		
		return ps.execute();
	}

	@Override
	public Veiculo buscarPorId(int _id) throws SQLException {
		
		ResultSet rs = null;
		Veiculo p = null;
		
		String SQL = "SELECT id, marca, cor, placa, status FROM veiculo WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.executeQuery();
		
		if (rs.next()) {
			
			int id = rs.getInt(1);
			String marca = rs.getString(2);
			String cor = rs.getString(3);
			String placa = rs.getString(4);
			String status = rs.getString(5);
			
			p = new Veiculo(id, marca, cor, placa, status, null);
		}
		
		return p;
	}

}
