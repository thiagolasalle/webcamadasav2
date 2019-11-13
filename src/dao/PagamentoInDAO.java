package dao;

import java.sql.SQLException;
import java.util.List;

import model.Pagamento;

public interface PagamentoInDAO {

	void Inserir(Pagamento _objeto) throws SQLException;
	
	List<Pagamento> listarTodos() throws SQLException;
	
	Boolean Excluir(int _id) throws SQLException;
	
	Boolean Atualizar(Pagamento _objeto) throws SQLException;
	
	Pagamento buscarPorId(int _id) throws SQLException;
}
