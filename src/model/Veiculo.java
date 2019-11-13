package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String marca;
	private String cor;
	private String placa;
	private String status;
	private List<Locacao> locacoes;
	
	public Veiculo() {
		this.locacoes = new ArrayList<Locacao>();
	}
	
	public Veiculo(int id, String marca, String cor, String placa, String status, List<Locacao> locacoes) {
		super();
		this.id = id;
		this.marca = marca;
		this.cor = cor;
		this.placa = placa;
		this.status = status;
		this.locacoes = locacoes;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}
	
	public void restaurarVeiculo(Veiculo _veiculo) {
		this.id = _veiculo.id;
		this.marca = _veiculo.marca;
		this.cor = _veiculo.cor;
		this.placa = _veiculo.placa;
		this.status = _veiculo.status;
	}	
	
	@Override
	public Veiculo clone() {
		return new Veiculo(this.id, this.marca, this.cor, this.placa, this.status, this.locacoes);
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
		Veiculo other = (Veiculo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", marca=" + marca + ", cor=" + cor + ", placa=" + placa + ", status=" + status + "]";
	}
}
