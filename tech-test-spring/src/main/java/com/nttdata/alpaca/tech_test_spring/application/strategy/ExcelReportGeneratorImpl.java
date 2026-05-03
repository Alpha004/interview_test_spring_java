package com.nttdata.alpaca.tech_test_spring.application.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ReportItem;

import reactor.core.publisher.Mono;

@Component
public class ExcelReportGeneratorImpl implements IReportGenerator {

	@Override
	public Mono<Object> generate(List<ReportItem> data) {
		// TODO Auto-generated method stub
		return null;
	}

}
