package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.VeiculoDAO;
import dao.VeiculoDAO;
import model.Veiculo;
import model.Veiculo;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class VeiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Veiculo veiculo;
	private ListDataModel<Veiculo> veiculos;
	private Boolean editando;
	private Veiculo veiculoAntesDaEdicao;
	
	public VeiculoBean() {
		this.veiculo = new Veiculo();
		this.veiculos = new ListDataModel<Veiculo>();
		this.editando = false;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public ListDataModel<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(ListDataModel<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.veiculo = new Veiculo();
	}
	
	public void Editar(Veiculo _veiculo) {
		this.veiculoAntesDaEdicao = _veiculo.clone();
		this.veiculo = _veiculo;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.veiculo.restaurarVeiculo(this.veiculoAntesDaEdicao);
		this.veiculo = new Veiculo();
		this.editando = false;
	}
	
	

	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			VeiculoDAO dao = new VeiculoDAO(conexao);
			
			dao.Atualizar(this.veiculo);
			
			JSFUtil.adicionarMensagemSucesso("Veiculo alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.veiculo = new Veiculo();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			VeiculoDAO dao = new VeiculoDAO(conexao);
			
			dao.Inserir(this.veiculo);
			
			List<Veiculo> listaVeiculos = dao.listarTodos();
			this.veiculos = new ListDataModel<Veiculo>(listaVeiculos);
			
			this.veiculo = new Veiculo();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PrepararExcluir() 
	{
		this.veiculo = this.veiculos.getRowData();
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			VeiculoDAO dao = new VeiculoDAO(conexao);
			
			dao.Excluir(this.veiculo.getId());
			
			ListDataModel<Veiculo> listaVeiculos = new ListDataModel<>(dao.listarTodos());
			this.veiculos = listaVeiculos;
			
			this.veiculo = new Veiculo();
			
			JSFUtil.adicionarMensagemSucesso("Veiculo excluída com sucesso!");
			
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
			
			VeiculoDAO dao = new VeiculoDAO(conexao);
			
			this.veiculos = new ListDataModel<Veiculo>(dao.listarTodos());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
