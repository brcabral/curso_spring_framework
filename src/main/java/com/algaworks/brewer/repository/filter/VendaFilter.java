package com.algaworks.brewer.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algaworks.brewer.model.StatusVenda;

public class VendaFilter {
	private Long codigo;
	private StatusVenda statusVenda;
	private LocalDate dataCriacaoInicial;
	private LocalDate dataCriacaoFinal;
	private BigDecimal valorMinimo;
	private BigDecimal valorMaximo;
	private String nomeCliente;
	private String cpfCnpjCliente;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public StatusVenda getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(StatusVenda statusVenda) {
		this.statusVenda = statusVenda;
	}

	public LocalDate getDataCriacaoInicial() {
		return dataCriacaoInicial;
	}

	public void setDataCriacaoInicial(LocalDate dataCriacaoInicial) {
		this.dataCriacaoInicial = dataCriacaoInicial;
	}

	public LocalDate getDataCriacaoFinal() {
		return dataCriacaoFinal;
	}

	public void setDataCriacaoFinal(LocalDate dataCriacaoFinal) {
		this.dataCriacaoFinal = dataCriacaoFinal;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
}
