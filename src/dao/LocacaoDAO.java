package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Funcionario;
import model.Locacao;
import model.Pagamento;
import model.Veiculo;

public class LocacaoDAO implements LocacaoInDAO {

	private Connection conexao = null;
	
	public LocacaoDAO(Connection _conn) {
		this.conexao = _conn;
	}
	
	@Override
	public void Inserir(Locacao _objeto) throws SQLException {
		
		String SQL = "INSERT INTO locacao (data_locacao, data_entrega, status, funcionario_id, cliente_id, pagamento_id, veiculo_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getDataLocacao());
		ps.setString(2, _objeto.getDataEntrega());
		ps.setString(2, _objeto.getStatus());
		ps.setInt(3, _objeto.getFuncionario().getId());
		ps.setInt(4, _objeto.getCliente().getId());
		ps.setInt(5, _objeto.getPagamento().getId());
		ps.setInt(6, _objeto.getVeiculo().getId());
		
		ps.execute();

	}

	@Override
	public List<Locacao> listarTodos() throws SQLException {
		
		List<Locacao> locacoes = new ArrayList<Locacao>();
		ResultSet rs = null;
		
		String SQL = "SELECT id, data_locacao, data_entrega, status, funcionario_id, cliente_id, pagamento_id, veiculo_id FROM locacao";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			String data_locacao = rs.getString(2);
			String data_entrega = rs.getString(3);
			String status = rs.getString(4);
			int funcionario_id = rs.getInt(5);
			int cliente_id = rs.getInt(6);
			int pagamento_id = rs.getInt(7);
			int veiculo_id = rs.getInt(8);
			
			FuncionarioDAO daofun = new FuncionarioDAO(conexao);
			Funcionario funcionario = daofun.buscarPorId(funcionario_id);
			
			ClienteDAO daocli = new ClienteDAO(conexao);
			Cliente cliente = daocli.buscarPorId(cliente_id);

			PagamentoDAO daopag = new PagamentoDAO(conexao);
			Pagamento pagamento = daopag.buscarPorId(pagamento_id);
			
			VeiculoDAO daoveic = new VeiculoDAO(conexao);
			Veiculo veiculo = daoveic.buscarPorId(veiculo_id);
						
			Locacao p = new Locacao(id, data_locacao, data_entrega, status, funcionario, cliente, pagamento, veiculo);
			
			locacoes.add(p);
		}
		
		return locacoes;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
	
		String SQL = "DELETE FROM locacao WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		return ps.execute();
	}

	@Override
	public Boolean Atualizar(Locacao _objeto) throws SQLException {

		String SQL = "UPDATE locacao SET data_locacao = ?, data_entrega = ?, status = ? WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getDataLocacao());
		ps.setString(2, _objeto.getDataEntrega());
		ps.setString(3, _objeto.getStatus());
		
		ps.setInt(4, _objeto.getId());
		
		return ps.execute();
	}

	@Override
	public Locacao buscarPorId(int _id) throws SQLException {
		
		ResultSet rs = null;
		Locacao p = null;
		
		String SQL = "SELECT id, data_locacao, data_entrega, status, funcionario_id, cliente_id, pagamento_id, veiculo_id FROM locacao WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.executeQuery();
		
		if (rs.next()) {
			
			int id = rs.getInt(1);
			String data_locacao = rs.getString(2);
			String data_entrega = rs.getString(3);
			String status = rs.getString(4);
			int funcionario_id = rs.getInt(5);
			int cliente_id = rs.getInt(6);
			int pagamento_id = rs.getInt(7);
			int veiculo_id = rs.getInt(8);
			
			FuncionarioDAO daofun = new FuncionarioDAO(conexao);
			Funcionario funcionario = daofun.buscarPorId(funcionario_id);
			
			ClienteDAO daocli = new ClienteDAO(conexao);
			Cliente cliente = daocli.buscarPorId(cliente_id);

			PagamentoDAO daopag = new PagamentoDAO(conexao);
			Pagamento pagamento = daopag.buscarPorId(pagamento_id);
			
			VeiculoDAO daoveic = new VeiculoDAO(conexao);
			Veiculo veiculo = daoveic.buscarPorId(veiculo_id);
			
			p = new Locacao(id, data_locacao, data_entrega, status, funcionario, cliente, pagamento, veiculo);
			
			funcionario.getLocacoes().add(p);
			cliente.getLocacoes().add(p);
			pagamento.setLocacao(p);
			veiculo.getLocacoes().add(p);
		}
		
		return p;
	}
}