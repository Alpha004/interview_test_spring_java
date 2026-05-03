package com.nttdata.alpaca.tech_test_spring.application.factory;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.application.strategy.ExcelReportGeneratorImpl;
import com.nttdata.alpaca.tech_test_spring.application.strategy.IReportGenerator;
import com.nttdata.alpaca.tech_test_spring.application.strategy.JsonReportGeneratorImpl;
import com.nttdata.alpaca.tech_test_spring.config.utils.ReportFormat;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReportGeneratorFactory {

	private final JsonReportGeneratorImpl jsonGenerator;
	private final ExcelReportGeneratorImpl excelGenerator;
	
	public IReportGenerator getGenerator(ReportFormat format) {
        return switch (format) {
            case JSON -> jsonGenerator;
            case EXCEL -> excelGenerator;
        };
    }
}
