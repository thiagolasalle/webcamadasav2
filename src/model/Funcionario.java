package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String sexo;
	private String matricula;
	private String senha;
	private List<Locacao> locacoes;
	
	public Funcionario() {
		this.locacoes = new ArrayList<Locacao>();
	}
	
	
	public Funcionario(int id, String nome, String matricula, String sexo, String senha, List<Locacao> locacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.sexo = sexo;
		this.senha = senha;
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
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}
	
	public void restaurarFuncionario(Funcionario _funcionario) {
		this.id = _funcionario.id;
		this.nome = _funcionario.nome;
		this.sexo = _funcionario.sexo;
		this.matricula = _funcionario.matricula;
		this.senha = _funcionario.senha;
	}	
	
	@Override
	public Funcionario clone() {
		return new Funcionario(this.id, this.nome, this.sexo, this.matricula, this.senha, this.locacoes);
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
		Funcionario other = (Funcionario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", sexo=" + sexo + ", senha=" + senha + "]";
	}
}
