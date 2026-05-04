package com.nttdata.alpaca.tech_test_spring.application.strategy;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ReportItem;

import reactor.core.publisher.Mono;

@Component
public class ExcelReportGeneratorImpl implements IReportGenerator {

	@Override
	public Mono<Object> generate(List<ReportItem> data) {
		return Mono.fromCallable(() -> {
			try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				Sheet sheet = workbook.createSheet("Report");
				
				Row headerRow = sheet.createRow(0);
				String[] headers = {"Fecha", "Cliente", "Numero Cuenta", "Tipo", "Saldo Inicial","Estado", "Valor Movimiento", "Tipo Movimiento", "Saldo Disponible"};
				for (int i = 0; i < headers.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(headers[i]);
				}
				
				int rowIdx = 1;
				for (ReportItem item : data) {
					Row row = sheet.createRow(rowIdx++);
					row.createCell(0).setCellValue(item.getFecha() != null ? item.getFecha().toString() : "");
					row.createCell(1).setCellValue(item.getCliente());
					row.createCell(2).setCellValue(item.getNumeroCuenta());
					row.createCell(3).setCellValue(item.getTipo());
					row.createCell(4).setCellValue(item.getSaldoInicial() != null ? item.getSaldoInicial() : 0.0);
					row.createCell(5).setCellValue(item.getEstado().toString());
					row.createCell(6).setCellValue(item.getValorMovimiento() != null ? item.getValorMovimiento() : 0.0);
					row.createCell(7).setCellValue(item.getTipoMovimiento());
					row.createCell(8).setCellValue(item.getSaldoDisponible() != null ? item.getSaldoDisponible() : 0.0);
				}
				
				workbook.write(out);
				return (Object) out.toByteArray();
			} catch (Exception e) {
				throw new RuntimeException("Error generating Excel report", e);
			}
		});
	}

	@Override
	public boolean supports(String format) {
		return "excel".equalsIgnoreCase(format);
	}

}
