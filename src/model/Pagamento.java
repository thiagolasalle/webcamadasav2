package model;

import java.io.Serializable;

public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private float valor;
	private String forma_pagamento;
	
	public Pagamento() {
	}
	
	public Pagamento(int id, float valor, String forma_pagamento) {
		super();
		this.id = id;
		this.valor = valor;
		this.forma_pagamento = forma_pagamento;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public String getFormaPagamento() {
		return forma_pagamento;
	}
	
	public void setFormaPagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
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
		Pagamento other = (Pagamento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", valor=" + valor + ", forma_pagamento=" + forma_pagamento + "]";
	}

	
}
