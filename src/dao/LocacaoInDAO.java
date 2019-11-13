package dao;

import java.sql.SQLException;
import java.util.List;

import model.Locacao;

public interface LocacaoInDAO {

	void Inserir(Locacao _objeto) throws SQLException;
	
	List<Locacao> listarTodos() throws SQLException;
	
	Boolean Excluir(int _id) throws SQLException;
	
	Boolean Atualizar(Locacao _objeto) throws SQLException;
	
	Locacao buscarPorId(int _id) throws SQLException;
}
