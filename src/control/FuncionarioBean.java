package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.FuncionarioDAO;
import dao.FuncionarioDAO;
import model.Funcionario;
import model.Funcionario;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private ListDataModel<Funcionario> funcionarios;
	private Boolean editando;
	private Funcionario funcionarioAntesDaEdicao;
	
	public FuncionarioBean() {
		this.funcionario = new Funcionario();
		this.funcionarios = new ListDataModel<Funcionario>();
		this.editando = false;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ListDataModel<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ListDataModel<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.funcionario = new Funcionario();
	}
	
	public void Editar(Funcionario _funcionario) {
		this.funcionarioAntesDaEdicao = _funcionario.clone();
		this.funcionario = _funcionario;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.funcionario.restaurarFuncionario(this.funcionarioAntesDaEdicao);
		this.funcionario = new Funcionario();
		this.editando = false;
	}
	
	

	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FuncionarioDAO dao = new FuncionarioDAO(conexao);
			
			dao.Atualizar(this.funcionario);
			
			JSFUtil.adicionarMensagemSucesso("Funcionario alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.funcionario = new Funcionario();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FuncionarioDAO dao = new FuncionarioDAO(conexao);
			
			dao.Inserir(this.funcionario);
			
			List<Funcionario> listaFuncionarios = dao.listarTodos();
			this.funcionarios = new ListDataModel<Funcionario>(listaFuncionarios);
			
			this.funcionario = new Funcionario();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PrepararExcluir() 
	{
		this.funcionario = this.funcionarios.getRowData();
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FuncionarioDAO dao = new FuncionarioDAO(conexao);
			
			dao.Excluir(this.funcionario.getId());
			
			ListDataModel<Funcionario> listaFuncionarios = new ListDataModel<>(dao.listarTodos());
			this.funcionarios = listaFuncionarios;
			
			this.funcionario = new Funcionario();
			
			JSFUtil.adicionarMensagemSucesso("Funcionario excluída com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FuncionarioDAO dao = new FuncionarioDAO(conexao);
			
			this.funcionarios = new ListDataModel<Funcionario>(dao.listarTodos());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
