package dao;

import java.sql.SQLException;
import java.util.List;

import model.Funcionario;

public interface FuncionarioInDAO {

	void Inserir(Funcionario _objeto) throws SQLException;
	
	List<Funcionario> listarTodos() throws SQLException;
	
	Boolean Excluir(int _id) throws SQLException;
	
	Boolean Atualizar(Funcionario _objeto) throws SQLException;
	
	Funcionario buscarPorId(int _id) throws SQLException;
}
