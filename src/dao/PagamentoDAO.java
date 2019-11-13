package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pagamento;

public class PagamentoDAO implements PagamentoInDAO {

	private Connection conexao = null;
	
	public PagamentoDAO(Connection _conn) {
		this.conexao = _conn;
	}
	
	@Override
	public void Inserir(Pagamento _objeto) throws SQLException {
		
		String SQL = "INSERT INTO apgamento (valor, forma_pagamento, locacao_id) VALUES (?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setFloat(1, _objeto.getValor());
		ps.setString(2, _objeto.getFormaPagamento());
		ps.setInt(3, _objeto.getLocacao().getId());
		
		ps.execute();

	}

	@Override
	public List<Pagamento> listarTodos() throws SQLException {
		
		List<Pagamento> pagamentos = new ArrayList<Pagamento>();
		ResultSet rs = null;
		
		String SQL = "SELECT id, valor, forma_pagamento FROM pagamento";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			Float valor = rs.getFloat(2);
			String forma_pagamento = rs.getString(3);
			
			Pagamento p = new Pagamento(id, valor, forma_pagamento, null);
			
			pagamentos.add(p);
		}
		
		return pagamentos;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
	
		String SQL = "DELETE FROM pagamento WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		return ps.execute();
	}

	@Override
	public Boolean Atualizar(Pagamento _objeto) throws SQLException {

		String SQL = "UPDATE pagamento SET valor = ?, forma_pagamento = ?, WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setFloat(1, _objeto.getValor());
		ps.setString(2, _objeto.getFormaPagamento());
				
		ps.setInt(3, _objeto.getId());
		
		return ps.execute();
	}

	@Override
	public Pagamento buscarPorId(int _id) throws SQLException {
		
		ResultSet rs = null;
		Pagamento p = null;
		
		String SQL = "SELECT id, valor, forma_pagamento FROM pagamento WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.executeQuery();
		
		if (rs.next()) {
			
			int id = rs.getInt(1);
			Float valor = rs.getFloat(2);
			String forma_pagamento = rs.getString(3);
			
			p = new Pagamento(id, valor, forma_pagamento, null);
		}
		
		return p;
	}


}
