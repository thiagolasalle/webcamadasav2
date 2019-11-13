package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.ClienteDAO;
import model.Cliente;
import util.FabricaConexao;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private List<Cliente> clientes;
	private Boolean editando;
	private Cliente clienteAntesDaEdicao;
	
	public ClienteBean() {
		this.cliente = new Cliente();
		this.clientes = new ArrayList<Cliente>();
		this.editando = false;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.cliente = new Cliente();
	}
	
	public void Editar(Cliente _cliente) {
		this.clienteAntesDaEdicao = _cliente.clone();
		this.cliente = _cliente;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.cliente.restaurarCliente(this.clienteAntesDaEdicao);
		this.cliente = new Cliente();
		this.editando = false;
	}
	
	

	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		this.cliente = new Cliente();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ClienteDAO dao = new ClienteDAO(conexao);
			
			dao.Inserir(this.cliente);
			
			List<Cliente> listaClientes = dao.listarTodos();
			this.clientes = listaClientes;
			
			this.cliente = new Cliente();
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
			
			ClienteDAO dao = new ClienteDAO(conexao);
			
			this.clientes = dao.listarTodos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
