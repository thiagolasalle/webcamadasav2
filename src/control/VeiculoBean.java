package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.VeiculoDAO;
import model.Veiculo;
import util.FabricaConexao;

@ManagedBean
@ViewScoped
public class VeiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Veiculo veiculo;
	private List<Veiculo> veiculos;
	private Boolean editando;
	private Veiculo veiculoAntesDaEdicao;
	
	public VeiculoBean() {
		this.veiculo = new Veiculo();
		this.veiculos = new ArrayList<Veiculo>();
		this.editando = false;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
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
			this.veiculos = listaVeiculos;
			
			this.veiculo = new Veiculo();
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
			
			VeiculoDAO dao = new VeiculoDAO(conexao);
			
			this.veiculos = dao.listarTodos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
