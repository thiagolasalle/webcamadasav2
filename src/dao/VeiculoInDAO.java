package dao;

import java.sql.SQLException;
import java.util.List;

import model.Veiculo;

public interface VeiculoInDAO {

	void Inserir(Veiculo _objeto) throws SQLException;
	
	List<Veiculo> listarTodos() throws SQLException;
	
	Boolean Excluir(int _id) throws SQLException;
	
	Boolean Atualizar(Veiculo _objeto) throws SQLException;
	
	Veiculo buscarPorId(int _id) throws SQLException;
}
