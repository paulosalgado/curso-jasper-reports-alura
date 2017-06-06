package br.com.caelum.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public class Movimentacao {
	
	private Integer id;
	private Calendar data;
	private BigDecimal valor;
	private String descricao;
	private TipoMovimentacao tipoMovimentacao;
	
//	private Categoria categoria;
//	private Conta conta;
	
	public Movimentacao() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Calendar getData() {
		return data;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
//	public Categoria getCategoria() {
//		return categoria;
//	}
//	
//	public void setCategoria(Categoria categoria) {
//		this.categoria = categoria;
//	}
//	
//	public Conta getConta() {
//		return conta;
//	}
//	
//	public void setConta(Conta conta) {
//		this.conta = conta;
//	}
	
}
