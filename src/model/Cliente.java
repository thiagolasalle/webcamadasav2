package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String cpf;
	private String endereco;
	private String sexo;
	private boolean deficiencia;
	private boolean idadederisco;
	private List<Locacao> locacoes;

	public Cliente() {
		this.locacoes = new ArrayList<Locacao>();
	}

	
	public Cliente(int id, String nome, String cpf, String endereco, String sexo, boolean deficiencia, boolean idadederisco, List<Locacao> locacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.sexo = sexo;
		this.deficiencia = deficiencia;
		this.idadederisco = idadederisco;
		this.locacoes = locacoes;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public boolean getDeficiencia() {
		return deficiencia;
	}
	
	public void setDeficiencia(boolean deficiencia) {
		this.deficiencia = deficiencia;
	}
	
	public boolean getIdadeDeRisco() {
		return idadederisco;
	}
	
	public void setIdadeDeRisco(boolean idadederisco) {
		this.idadederisco = idadederisco;
	}
	
	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}

	public void restaurarCliente(Cliente _cliente) {
		this.id = _cliente.id;
		this.nome = _cliente.nome;
		this.cpf = _cliente.cpf;
		this.endereco = _cliente.endereco;
		this.sexo = _cliente.sexo;
		this.deficiencia = _cliente.deficiencia;
		this.idadederisco = _cliente.idadederisco;
		this.locacoes = _cliente.locacoes;
	}	
	
	@Override
	public Cliente clone() {
		return new Cliente(this.id, this.nome, this.cpf, this.endereco, this.sexo, this.deficiencia, this.idadederisco, this.locacoes);
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
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome + "," + cpf + "," + endereco + "," + sexo + "," + deficiencia + "," + idadederisco;
	}
}
