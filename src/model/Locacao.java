package model;

import java.io.Serializable;

public class Locacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String data_locacao;
	private String data_entrega;
	private String status;
	private Funcionario funcionario;
	private Cliente cliente;
	private Pagamento pagamento;
	private Veiculo veiculo;
	
	
	public Locacao(int id, String data_locacao, String data_entrega, String status, Funcionario funcionario, Cliente cliente, Pagamento pagamento, Veiculo veiculo) {
		super();
		this.id = id;
		this.data_locacao = data_locacao;
		this.data_entrega = data_entrega;
		this.status = status;
		this.funcionario = funcionario;
		this.cliente = cliente;
		this.pagamento = pagamento;
		this.veiculo = veiculo;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDataLocacao() {
		return data_locacao;
	}
	
	public void setDataLocacao(String data_locacao) {
		this.data_locacao = data_locacao;
	}
	
	public String getDataEntrega() {
		return data_entrega;
	}
	
	public void setDataEntrega(String data_entrega) {
		this.data_entrega = data_entrega;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncioanrio(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", data_locacao=" + data_locacao + ", data_entrega=" + data_entrega + ", status=" + status + "]";
	}
}
