package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import dao.LocacaoDAO;
import model.Locacao;
import util.FabricaConexao;

import dao.VeiculoDAO;
import model.Veiculo;

import dao.FuncionarioDAO;
import model.Funcionario;

import dao.ClienteDAO;
import model.Cliente;

@ManagedBean
@ViewScoped
public class LocacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Locacao locacao;
	private Boolean editando;
	private Locacao locacaoAntesDaEdicao;
	private Cliente cliente;
	private List<Veiculo> veiculosDisponiveis;
	private List<Funcionario> funcionariosDisponiveis;
	private List<Cliente> clientesDisponiveis;
	
	public LocacaoBean() {
		this.locacao = new Locacao();
		this.editando = false;
	}
	
	public Locacao getLocacao() {
		return locacao;
	}
	
	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	
	
	public Locacao getLocacaoAntesDaEdicao() {
		return locacaoAntesDaEdicao;
	}

	public void setLocacaoAntesDaEdicao(Locacao locacaoAntesDaEdicao) {
		this.locacaoAntesDaEdicao = locacaoAntesDaEdicao;
	}

	public List<Veiculo> getVeiculosDisponiveis() {
		return veiculosDisponiveis;
	}

	public void setVeiculosDisponiveis(List<Veiculo> veiculosDisponiveis) {
		this.veiculosDisponiveis = veiculosDisponiveis;
	}

	public List<Funcionario> getFuncionariosDisponiveis() {
		return funcionariosDisponiveis;
	}

	public void setFuncionariosDisponiveis(List<Funcionario> funcionariosDisponiveis) {
		this.funcionariosDisponiveis = funcionariosDisponiveis;
	}

	public List<Cliente> getClientesDisponiveis() {
		return clientesDisponiveis;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setClientesDisponiveis(List<Cliente> clientesDisponiveis) {
		clientesDisponiveis = clientesDisponiveis;
	}

	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.locacao = new Locacao();
	}
	
	public void Editar(Locacao _locacao) {
		this.locacaoAntesDaEdicao = _locacao.clone();
		this.locacao = _locacao;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.locacao.restaurarLocacao(this.locacaoAntesDaEdicao);
		this.locacao = new Locacao();
		this.editando = false;
	}
	
	//------------ wizard
	
	public String onFlowProcess(FlowEvent event) {
        System.out.println(this.cliente.toString());    
		return event.getNewStep();
    }
	

	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		this.locacao = new Locacao();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			LocacaoDAO dao = new LocacaoDAO(conexao);
			
			dao.Inserir(this.locacao);
			
			this.locacao = new Locacao();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ClienteDAO daoCliente = new ClienteDAO(conexao);
			
			this.clientesDisponiveis = daoCliente.listarTodos();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
