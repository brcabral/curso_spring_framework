package com.algaworks.brewer.repository.filter;

import com.algaworks.brewer.model.TipoPessoa;

public class ClienteFilter {
	private String nome;
	private String cpfCnpj;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Object getCpfCnpjSemFormatacao() {
		return TipoPessoa.removerFormatacao(this.cpfCnpj);
	}
}
