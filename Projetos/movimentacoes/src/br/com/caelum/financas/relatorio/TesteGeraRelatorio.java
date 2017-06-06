package br.com.caelum.financas.relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import br.com.caelum.financas.ConnectionFactory;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.MovimentacaoDAO;

public class TesteGeraRelatorio {

	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {
		
//		JasperCompileManager.compileReportToFile("financas.jrxml"); 
		
		Connection conexao = new ConnectionFactory().getConnection();
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Movimentacao> movimentacoes = new MovimentacaoDAO(conexao).todos();
		
		List<MovimentacaoRelatorio> listaRelatorio = new LinkedList<MovimentacaoRelatorio>();
		
		for (Movimentacao movimentacao : movimentacoes) {
			listaRelatorio.add(new MovimentacaoRelatorio(movimentacao));
		}
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(listaRelatorio);
		
		JasperPrint jasperPrint = 
				JasperFillManager.fillReport("financas.jasper", params, dataSource);
		
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("financas.pdf"));
		
		exporter.exportReport();
		
		conexao.close();
	}
}
