package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;

public class FuncionarioDAO implements FuncionarioInDAO {

	private Connection conexao = null;
	
	public FuncionarioDAO(Connection _conn) {
		this.conexao = _conn;
	}
	
	@Override
	public void Inserir(Funcionario _objeto) throws SQLException {
		
		String SQL = "INSERT INTO funcionario (nome, matricula, sexo, senha) VALUES (?, ?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getNome());
		ps.setString(2, _objeto.getMatricula());
		ps.setString(3, _objeto.getSexo());
		ps.setString(4, _objeto.getSenha());
		
		ps.execute();

	}

	@Override
	public List<Funcionario> listarTodos() throws SQLException {
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		ResultSet rs = null;
		
		String SQL = "SELECT id, nome, matricula, sexo, senha FROM funcionario";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			String matricula = rs.getString(3);
			String sexo = rs.getString(4);
			String senha = rs.getString(5);
						
			Funcionario p = new Funcionario(id, nome, matricula, sexo, senha, null);
			
			funcionarios.add(p);
		}
		
		return funcionarios;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
	
		String SQL = "DELETE FROM funcionario WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		return ps.execute();
	}

	@Override
	public Boolean Atualizar(Funcionario _objeto) throws SQLException {

		String SQL = "UPDATE funcionario SET nome = ?, email = ?, tel = ?, senha = ? WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, _objeto.getNome());
		ps.setString(2, _objeto.getMatricula());
		ps.setString(3, _objeto.getSexo());
		ps.setString(4, _objeto.getSenha());
		
		ps.setInt(5, _objeto.getId());
		
		return ps.execute();
	}

	@Override
	public Funcionario buscarPorId(int _id) throws SQLException {
		
		ResultSet rs = null;
		Funcionario p = null;
		
		String SQL = "SELECT id, nome, email, tel, senha FROM pessoa WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.executeQuery();
		
		if (rs.next()) {
			
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			String matricula = rs.getString(3);
			String sexo = rs.getString(4);
			String senha = rs.getString(5);
			
			p = new Funcionario(id, nome, matricula, sexo, senha, null);
		}
		
		return p;
	}

}
