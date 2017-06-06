package br.com.caelum.relatorio.teste;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.relatorio.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class TesteRelatorio {
	
	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {
		
		Connection connection = new ConnectionFactory().getConnection();
		
//		JasperCompileManager.compileReportToFile("gasto_por_cliente.jrxml");
//		JasperCompileManager.compileReportToFile("gasto_por_cliente_subreport1.jrxml");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		JasperPrint jasperPrint = 
				JasperFillManager.fillReport("gasto_por_cliente.jasper", params, connection);
		
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("gasto_por_cliente.pdf"));
		
		exporter.exportReport();
		
		connection.close();
	}
	
}
