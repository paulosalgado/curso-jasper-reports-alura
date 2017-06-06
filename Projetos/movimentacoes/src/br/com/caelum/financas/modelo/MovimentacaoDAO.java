package br.com.caelum.financas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MovimentacaoDAO {
	
	private Connection connection;
	
	public MovimentacaoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Movimentacao> todos() {
		try {
			
			String sql = "SELECT movimentacoes.id AS id, " 
					   		  + "movimentacoes.data AS data, " 
					   		  + "movimentacoes.descricao AS descricao, " 
					   		  + "movimentacoes.tipoMovimentacao AS tipo, " 
					   		  + "movimentacoes.valor AS valor " 
//					   		  + "categorias.id AS categoriaId, " 
//					   		  + "categorias.nome AS categoriaNome, " 
//					   		  + "contas.id AS id, " 
//					   		  + "contas.titular AS titular, " 
//					   		  + "contas.numero AS numero, " 
//					   		  + "contas.agencia AS agencia, " 
//					   		  + "contas.banco AS banco "
					   + "FROM movimentacoes "
//					   		  + "INNER JOIN categorias ON movimentacoes.categoria_id = categorias.id "
//					   		  + "INNER JOIN contas ON movimentacoes.conta_id = contas.id";
					   ;			
			
			PreparedStatement statement = this.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			List<Movimentacao> lista = new LinkedList<Movimentacao>();
			
			while (rs.next()) {
				
				Movimentacao movimentacao = new Movimentacao();
				movimentacao.setId(rs.getInt(1));
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate(2));
				movimentacao.setData(cal);
				
				movimentacao.setDescricao(rs.getString(3));
				
				TipoMovimentacao tipoMovimentacao = TipoMovimentacao.valueOf(rs.getString(4));
				movimentacao.setTipoMovimentacao(tipoMovimentacao);
				
				movimentacao.setValor(rs.getBigDecimal(5));
				
//				Categoria cat = new Categoria();
//				cat.setId(rs.getInt(6));
//				cat.setNome(rs.getString(7));
//				
//				movimentacao.setCategoria(cat);
//				
//				Conta conta = new Conta();
//				conta.setId(rs.getInt(8));
//				conta.setTitular(rs.getString(9));
//				conta.setNumero(rs.getString(10));
//				conta.setAgencia(rs.getString(11));
//				conta.setBanco(rs.getString(12));
//				
//				movimentacao.setConta(conta);
				
				lista.add(movimentacao);
			}
			
			return lista;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
